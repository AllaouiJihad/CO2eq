package Repository;

import Entity.User;
import Interfaces.RepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements RepositoryInterface<User> {

    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> create(User user) {
        String query = "INSERT INTO users (name,age) VALUES (?,?)";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,user.getName());
            statement.setInt(2,user.getAge());
            ResultSet resultSet = statement.executeQuery();

            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Optional<User> update(User user) {
        String sql = "UPDATE users SET name = ?, age = ? WHERE id = ? RETURNING *";
       try ( PreparedStatement statement = connection.prepareStatement(sql)){
           statement.setString(1,user.getName());
           statement.setInt(2,user.getAge());
           statement.setInt(3,user.getId());
           ResultSet resultSet = statement.executeQuery();
           if (resultSet.next()) {
               return Optional.of(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age")));
           }

       }
       catch (SQLException e) {
           e.printStackTrace();
       }

        return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement= connection.prepareStatement(query);
             ResultSet resultSet =preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id =resultSet.getInt("id");
                String name =resultSet.getString("name");
                int age= resultSet.getInt("age");

                User user = new User(id, name, age);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
