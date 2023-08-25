package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String str = "CREATE TABLE IF NOT EXISTS `myDB`.`users` (`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NULL, " +
                "`lastname` VARCHAR(100) NULL, `age` INT NULL,     PRIMARY KEY (`id`))";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String str = "DROP TABLE IF EXISTS users";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String str = "INSERT INTO users (name, lastName, age ) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(str)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String str = "DELETE FROM users WHERE id =" + id;
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String str = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try (Statement statement = new Util().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(str);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() { //TRUNCATE  user
        String str = "TRUNCATE  users";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
