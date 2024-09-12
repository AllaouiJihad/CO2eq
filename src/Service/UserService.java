package Service;

import Entity.Consommation;
import Entity.User;
import Repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<User> findInactiveUsersForPeriod(LocalDate startDate, LocalDate endDate) {
        List<User> allUsers = userRepository.getAll();

        return allUsers.stream()
                .filter(user -> isUserInactiveForPeriod(user, startDate, endDate))
                .collect(Collectors.toList());
    }

//    public List<User> usersInnactive(LocalDate startDate , LocalDate endDate){
//        List<User> allUsers = userRepository.getAll();
//
//        List<User> inactive = allUsers
//                .stream().
//                filter()
//    }
    ;


    private boolean isUserInactiveForPeriod(User user, LocalDate startDate, LocalDate endDate) {
        List<Consommation> userConsommations = user.getConsommations();

        if (userConsommations.isEmpty()) {
            return true; // L'utilisateur est inactif s'il n'a jamais enregistré de consommation
        }

        // Vérifier s'il existe une consommation qui chevauche la période spécifiée
        return userConsommations.stream()
                .noneMatch(consommation ->
                        (consommation.getStartDate().isBefore(endDate) || consommation.getStartDate().isEqual(endDate)) &&
                                (consommation.getEndDate().isAfter(startDate) || consommation.getEndDate().isEqual(startDate))
                );
    }
}
