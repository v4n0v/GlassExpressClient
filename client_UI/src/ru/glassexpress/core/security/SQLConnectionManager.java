package ru.glassexpress.core.security;

import java.sql.*;

public class SQLConnectionManager implements LoginManager {
   static SQLConnectionManager sqlConnectionManager;

    private SQLConnectionManager() {
    }

    public static SQLConnectionManager getInstance() {
        if (sqlConnectionManager==null) sqlConnectionManager= new SQLConnectionManager();
        return sqlConnectionManager;
    }

    private Connection connection;
    private Statement statement;
    PreparedStatement ps;
    // подгружаем БД
    @Override
    public void init() {


        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:user_client.db");
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS  users (" +
                    "id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "login STRING," +
                    "pass INTEGER," +
                    "user_key STRING);"
            );


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
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
        String s=null;
        try {
            ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND passHash=? ;");
            ps.setString(1, login);
            ps.setInt(2, pass);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("row"+rs.getRow()+"\n" +
                        "id="+rs.getInt("id_user")+
                "\nlogin="+rs.getString("login")+
                "\nkey"+rs.getString("user_key"));

                rs.getString("user_key");
              return   rs.getString("user_key");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    return null;
    }

    // лобавляем пользователя
    @Override
    public void addNewUser(String login, String pass, String  key) {

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
