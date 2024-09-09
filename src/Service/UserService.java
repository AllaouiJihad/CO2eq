package Service;

import Entity.User;
import Repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> createUser(User user) {
        return userRepository.create(user);
    }

    public Optional<User> updateUser(User user) {
        return userRepository.update(user);
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }
    public List<User> getAll(){
        return userRepository.getAll();
    }

    public boolean deleteUser(int id) {
        return userRepository.delete(id);
    }
}
