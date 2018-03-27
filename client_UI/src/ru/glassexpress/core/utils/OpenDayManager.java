package ru.glassexpress.core.utils;

import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.objects.IdElement;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenDayManager {
    private GetListOperator operator;
    private UserObject user;

    public OpenDayManager(GetListOperator operator, UserObject user) {
        this.operator = operator;
        this.user = user;
    }

    public boolean isDayAlreadyOpened() {

        int id = user.getSalonId();
//        int id = user.getId();
        long lastDay = operator.getLastOpenedDay(new IdElement(id));
        Date date = new Date();
        if (lastDay == 0) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(Resources.DATE_PATTERN_SIMPLE);
        formatter.setLenient(false);
        String value1 = formatter.format(date);
        String value2 = formatter.format(lastDay);
        System.out.println("день1 = " + value1 + " день2 = " + value2);

        if (!value1.equals(value2)) {
            return false;
        }

        return true;
    }

    private long getLastOpenedDay() {
//        return 0;
        return System.currentTimeMillis();
    }

}
