package Service;

import Entity.User;
import Repository.UserRepository;

import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> createUser(User user){
       return userRepository.create(user);
    }
}
