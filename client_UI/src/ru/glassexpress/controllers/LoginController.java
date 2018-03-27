package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.*;
//import ru.glassexpress.core.security.ClientSecurityManager;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.utils.OpenDayManager;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.library.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class LoginController extends BaseController {


    @FXML
    TextField fieldLogin;

    @FXML
    PasswordField fieldPass;

    @FXML
    VBox rootElement;
   // ClientSecurityManager securityManager;


    public void login() {
     //   securityManager = new ClientSecurityManager(mainController.getUser());
        String login = fieldLogin.getText();
        String pass = fieldPass.getText();
        System.out.println(login + " " + pass);

        if (!login.isEmpty() || !pass.isEmpty()) {
            if (StringValidator.isLoginCorrect(login) && StringValidator.isPassCorrect(pass)) {
//                securityManager.setLogin(login);
//                securityManager.setPassword(pass);
//
//                // проверяем наличие пользователя
//                // if (securityManager.isUserValid(login, pass)){
//                // получаем ключ
//                String key = securityManager.getKey(login, pass);
//                if (key != null) {
//                    // устанавливаем соединение, закрываем окно
//                    mainController.getUser().setKey(key);
                    GetListOperator operator = new GetListOperator(null);

                    IdTitleObj keyObj = operator.getUserByLoginPass(new IdTitleObj(pass.hashCode(), login));

                    if (keyObj!=null&&keyObj.getTitle()!=null&&!keyObj.getTitle().equals("")) {
                       operator.setKey(keyObj.getTitle());
                       UserObject user = operator.getUserByKey();
                        // UserObject user  = operator.getUserByLoginPass(new IdTitleObj(pass.hashCode(), login));
                        if (user != null) {
                            user.setKey(keyObj.getTitle());
                            mainController.setUser(user);
                            mainController.setOperator(operator);
                            mainController.reconnect();
                            // получаем из базы последний день, открытый этим администратором
                            int id = user.getId();


                            if (user.getPermission()==1){
                               mainApp.showSelectSalonLayout();
                            } else {

                                OpenDayManager openDayManager = new OpenDayManager(operator, user);
                                if (openDayManager.isDayAlreadyOpened()){
                                    mainApp.initGoodMorningLayout(user);
                                } else {
                                    DateObject day = openDayManager.getCurrentDay();
                                    Composite empComposite = (Composite) JsonController.getInstance().convertJsonToObject(day.getEmployeesJson());
                                    BaseObjectAdapter adapter=BaseObjectAdapter.getInsance();
                                    ObservableList<UserObject> empList = adapter.baseObjToUserObjList(empComposite.getComponents());
                                    dataMap.setCurrentEmployeesList(empList);
                                    mainController.initPermission();
                                    System.out.println("день уже начат, открываю приложение");
                                }

//                                Date lastDay = operator.getLastOpenedDay(new IdElement(id));
//                                Date date = new Date();
//
//                                //todo босс на несколько точек работает, нужно как-то актуализировать точку!
//                                // если адиминстратор уже заполнял форму дня, тогда открываем приложенеие,
//                                // иначе заполнять форму, добавлять сотрудников дня
//                                if (!isDayAlreadyOpened(date, lastDay)){
//                                    System.out.println("день не начат, создаю новый");
//                                    mainApp.initGoodMorningLayout(user);
//                                } else {
//                                    mainController.initPermission();
//                                    System.out.println("день уже начат, открываю приложение");
//                                }

                            }


                            close();
                        } else {
                            AlertWindow.errorMessage("Пользователя не получены. Сервер не отвечает.");

                        }


                } else {
                    AlertWindow.errorMessage("Неверный логин\\пароль");
                }

            } else {
                AlertWindow.errorMessage("Допустимы только буквы и цифры английского алфавита");
            }
        } else {
            AlertWindow.errorMessage("Заполните поля");
        }
    }


    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна аутентификации");
    }

    private static boolean isDayAlreadyOpened(Date currentDateMillis, Date dbDateMillis) {

        if (currentDateMillis == null || dbDateMillis == null) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(Resources.DATE_PATTERN_SIMPLE);
        formatter.setLenient(false);
        String value1 = formatter.format(currentDateMillis);
        String value2 = formatter.format(dbDateMillis);
        System.out.println("день1 = "+value1+" день2 = "+value2);
        if (!value1.equals(value2)){

            return false;

        }
//        try {
//            formatter.parse(value);
//        } catch (ParseException e) {
//            return false;
//        }

        return true;
    }

    private long getLastOpenedDay() {
//        return 0;
        return System.currentTimeMillis();
    }

}
