package ru.glassexpress.core.security;

import java.sql.*;

public class SQLLoginManager implements LoginManager {

    private Connection connection;
    private Statement statement;

    // подгружаем БД
    @Override
    public void init() {


        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS  users (" +
                    "id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "login STRING," +
                    "pass INTEGER," +
                    "key STRING);"
            );


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // проверяем логин\пароль,
    @Override
    public boolean isLoginAndPassCorrect(String login, int pass) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND passHash=? ;")) {

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
    // лобавляем пользователя
    @Override
    public void addNewUser(String login, String mail,  int passHash) {

        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (login , mail, passHash, space) VALUES (?, ?, ?, ?) ");
            ps.setString(1, login);
            ps.setString(2, mail);
      //   ps.setString(3, pass);
            ps.setInt(3, passHash);
            ps.setInt(4, 10);
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // проверим, не занят ли логин
    public boolean isLoginBusy(String login) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login=?")) {
            //      ps.setString(1, mail);
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
