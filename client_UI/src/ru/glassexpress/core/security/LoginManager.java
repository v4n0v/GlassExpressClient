package ru.glassexpress.core.security;

public interface LoginManager {

    void init();

    boolean isLoginAndPassCorrect(String login, int pass) ;

    void addNewUser(String login, String mail, int passHash);
    void dispose();
}
