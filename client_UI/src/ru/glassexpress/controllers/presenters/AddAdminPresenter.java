package ru.glassexpress.controllers.presenters;

import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.security.Keygen;
import ru.glassexpress.library.AlertWindow;

import java.security.NoSuchAlgorithmException;

public class AddAdminPresenter {
    private AddAdminView view;
    private String key;
    private DataMap dataMap;
    public AddAdminPresenter(AddAdminView view) {
        this.view = view;
        dataMap=DataMap.getInstance();
    }

    public void addUser(String login, String pass1, String pass2) {
        pass1 = pass1.replace(" ", "");
        pass2 = pass2.replace(" ", "");
        login = login.replace(" ", "");
        if (StringValidator.isLoginCorrect(login) && StringValidator.isPassCorrect(pass1) && StringValidator.isPassCorrect(pass2)
                && login.length() > 2 && pass1.length() > 2 && pass2.length() > 2) {
            // проверяем равенство паролей
            if (pass1.equals(pass2)) {
                // проверяем занят ли логин
                GetListOperator getListOperator = new GetListOperator(dataMap.getUser().getKey());
                if (getListOperator.isUserFree(login)) {
                    Log2File.writeLog("Полизователь свободен");
                    try {
                        key = Keygen.generate();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        AlertWindow.errorMessage("Ошибка генерации ключа");
                    }
                    view.initAddEmpLayout(login, pass1, key);
                    view.closeView();
                } else {
                   view.showError("Пользователь с таким логим уже зарегистрирован");
                }

            } else {
                view.showError("Вы ввели разные пароли");
            }
        } else {
            view.showError("Допустимы только буквы и цифры английского алфавита.\nДлина не менее 3х символов");
        }
    }
}
