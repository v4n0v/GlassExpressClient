package ru.glassexpress.core.security;

import javafx.util.StringConverter;
import ru.glassexpress.Log2File;
import ru.glassexpress.library.AlertWindow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class SQLConnectionManager implements LoginManager {
    private static final String DATABASE = "user_client.db";
    //private String PATH = "";
    //    String PATH = System.getProperty("user.dir");
    private static SQLConnectionManager sqlConnectionManager;

    private SQLConnectionManager() {
    }

    public static SQLConnectionManager getInstance() {
        if (sqlConnectionManager == null) sqlConnectionManager = new SQLConnectionManager();
        return sqlConnectionManager;
    }

    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    File base;

    // подгружаем БД
    @Override
    public void init() {
//         String dir = System.getProperty("user.dir");
//        File aaa = new File("sss.txt");
//
//        if (!isDBExist()){
//            File db = new File(DATABASE);
//            try {
//                Log2File.writeLog("База отсутствует, создаю новую");
//                db.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        base = new File(DATABASE);
//        if (!base.exists()){
//            try {
//                base.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }



        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" +DATABASE);
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS  users (" +
                    "id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "login STRING," +
                    "passHash INTEGER," +
                    "user_key STRING);"

            );
            Log2File.writeLog("соединение с базой " + DATABASE + " установлено");

        } catch (ClassNotFoundException | SQLException e) {
            Log2File.writeLog("ошибка соединения с базой "+DATABASE+ "|| "+e.getMessage());
            AlertWindow.errorMessage("Ошибка соединения с базой: ");
            throw new RuntimeException(e);
        }
    }

    boolean isDBExist(){
        File[] fList;
        File file = new File (System.getProperty("user.dir"));
        File folder = new File(file.getParent());
//        File folder = new File (System.getProperty("user.dir"));
        fList = folder.listFiles();
        boolean isExist=false;
        for (File f: fList) {
            if (f.getName().equals(DATABASE))
                isExist=true;
        }
        return isExist;
    }

    // проверяем логин\пароль,
    @Override
    public boolean isLoginAndPassCorrect(String login, int pass) {

        try {
            ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND passHash=? ;");
            ps.setString(1, login);
            ps.setInt(2, pass);


            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isLoginBusy(String login) {

        try {
            ps = connection.prepareStatement("SELECT * FROM users WHERE login=? ;");
            ps.setString(1, login);


            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public String getKey(String login, int pass) {
        String s = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND passHash=? ;");
            ps.setString(1, login);
            ps.setInt(2, pass);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("row" + rs.getRow() + "\n" +
                        "id=" + rs.getInt("id_user") +
                        "\nlogin=" + rs.getString("login") +
                        "\nkey" + rs.getString("user_key"));

                rs.getString("user_key");
                return rs.getString("user_key");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return null;
    }

    // лобавляем пользователя
    @Override
    public void addNewUser(String login, String pass, String key) {

        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (login, passHash, user_key) VALUES (?, ?, ?) ");
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, key);
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    // закрываем соединение
    @Override
    public void dispose() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
