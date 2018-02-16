package ru.glassexpress;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class ClientManager {
    public ObservableList<String> getCarModel(){
        String[] models = {"Mercedes", "Ford", "BMW", "Renault", "Audi", "Nissan", "Opel", "Kia", "Volvo" };
        ObservableList<String> carModels = FXCollections.observableArrayList(Arrays.asList(models));
        return carModels;
    }
}
