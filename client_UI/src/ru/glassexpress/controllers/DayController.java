package ru.glassexpress.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.Resources;

import java.text.SimpleDateFormat;


public class DayController extends BaseController {


    public Label adminLabel;
    public Label dataLabel;
    public Button skipButton;

    private UserObject administrator;
    private long date;


    @Override
    public void init() {
        Log2File.writeLog("Инициализация окна составления рабочего графика");
        initPermissions();
        adminLabel.setText(administrator.getName() + " " + administrator.getLastName());
        date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Resources.DATE_PATTERN);
        String title = dateFormat.format(date);
        dataLabel.setText(title);

        // получаем из базы последний день, открытый этим администратором

    }

    public void beginMakingMoney() {
        startApplication();
    }

    private void initPermissions() {
        boolean isVisible = false;
        if (administrator.getPermission() == 1) {
            isVisible = true;
        }
        skipButton.setVisible(isVisible);
    }


    public void setAdministrator(UserObject administrator) {
        this.administrator = administrator;
    }

    private void startApplication() {
        mainController.reconnect();
    }


    // проверка дней теущего и последнего с сервера
    static boolean isDayAlreadyOpened(long currentDateMillis, long dbDateMillis) {

        if (currentDateMillis == 0 || dbDateMillis == 0) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(Resources.DATE_PATTERN_SIMPLE);
        formatter.setLenient(false);
        String value1 = formatter.format(currentDateMillis);
        String value2 = formatter.format(dbDateMillis);

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

    public long getLastOpenedDay() {
        return 0;
    }
}
