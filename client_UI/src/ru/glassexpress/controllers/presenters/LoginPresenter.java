package ru.glassexpress.controllers.presenters;

import javafx.collections.ObservableList;
import ru.glassexpress.controllers.MainController;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.core.objects.DateObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.utils.OpenDayManager;
import ru.glassexpress.library.AlertWindow;

public class LoginPresenter {
    private LoginView view;
    private DataMap dataMap;

    public LoginPresenter(LoginView view) {
        this.view = view;
        dataMap = DataMap.getInstance();
    }

    public void login(String login, String pass, MainController mainController) {
        if (!login.isEmpty() || !pass.isEmpty()) {
            // проверяем на корректность введенных символов
            if (StringValidator.isLoginCorrect(login) && StringValidator.isPassCorrect(pass)) {

                GetListOperator operator = new GetListOperator(null);
                // получаем ключ
                IdTitleObj keyObj = operator.getUserByLoginPass(new IdTitleObj(pass.hashCode(), login));

                if (keyObj != null && keyObj.getTitle() != null && !keyObj.getTitle().equals("")) {
                    operator.setKey(keyObj.getTitle());
                    UserObject user = operator.getUserByKey();
                    // UserObject user  = operator.getUserByLoginPass(new IdTitleObj(pass.hashCode(), login));
                    if (user != null) {
                        user.setKey(keyObj.getTitle());
                        dataMap.setUser(user);
                        mainController.setUser(user);
                        mainController.setOperator(operator);
                        mainController.reconnect();
                        // получаем из базы последний день, открытый этим администратором
                        // если у пользовалетя права root, тогда уточняем какую точку продаж открыть
                        if (user.getPermission() == 1) {
                            view.showSelectSalonLayout();
                        } else {

                            OpenDayManager openDayManager = new OpenDayManager(operator, user);

                            // проверка, был ли уже открыт день и заполнены сотрудники на этой точке продаж
                            if (!openDayManager.isDayAlreadyOpened()) {
                                view.initGoodMorningLayout(user);
                            } else {
                                DateObject day = openDayManager.getCurrentDay();
                                Composite empComposite = (Composite) JsonController.getInstance().convertJsonToObject(day.getEmployeesJson());
                                BaseObjectAdapter adapter = BaseObjectAdapter.getInsance();
                                ObservableList<UserObject> empList = adapter.baseObjToUserObjList(empComposite.getComponents());
                                dataMap.setCurrentEmployeesList(empList);
                                mainController.initPermission();
                                System.out.println("день уже начат, открываю приложение");
                            }

                        }
                        view.closeView();
                    } else {
                        view.showError("Пользователя не получены. Сервер не отвечает.");

                    }
                } else {

                    view.showError("Неверный логин\\пароль");
                }
            } else {
                view.showError("Допустимы только буквы и цифры английского алфавита");
            }
        } else {
            view.showError("Заполните поля");

        }
    }
}
