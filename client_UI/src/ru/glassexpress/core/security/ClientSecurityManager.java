package ru.glassexpress.core.security;

import ru.glassexpress.GEClient;
import ru.glassexpress.controllers.MainController;
import ru.glassexpress.library.AlertWindow;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class ClientSecurityManager {
    {
        sqlLoginManager=new SQLLoginManager();
    }



    private final Logger logger = Logger.getLogger("Filebox.ClientManager");
    SQLLoginManager sqlLoginManager;


 //   public State state = State.NOT_CONNECTED;
    private final static String IP = "localhost";
    private String errorMsg;
    private boolean isAuthorized;
    private final static int PORT = 8189;
    private GEClient mainApp;

    private List<File> listOutFiles;
    private String login;
    private String password;
    private String loginReg;
    private String mailReg;
    private String pass1Reg;

    private MainController mainController;



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setRegistrationInfo(String loginReg, String mailReg, String pass1Reg) {
        this.pass1Reg = pass1Reg;
        this.mailReg = mailReg;
        this.loginReg = loginReg;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isUserValid(String login, String pass) {

        sqlLoginManager.isLoginAndPassCorrect(login, pass.hashCode());
        AlertWindow.infoMessage("Скоро, уже почти");

        return true;

    }


}
