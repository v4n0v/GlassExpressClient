package ru.glassexpress.command_request;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.ServerVocabulary;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.CarMark;
import ru.glassexpress.objects.Composite;

import java.util.ArrayList;
import java.util.List;

class Operator{
    void insert(){
        System.out.println("insert");
    }
    void update(){
        System.out.println("update");
    }
    void select(){


    }
    void delete(){
        System.out.println("delete");
    }
}
