package ru.glassexpress.controllers;

import ru.glassexpress.core.data.Log2File;

public class OrderConfirmController extends BaseController {
    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна подтверждения заказа");
    }
}
