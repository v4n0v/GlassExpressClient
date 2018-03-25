package ru.glassexpress.controllers;

import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.BaseObject;

public class DayController extends BaseController {


    @Override
    public void init() {
        Log2File.writeLog("Инициализация окна составления рабочего графика");
    }
}
