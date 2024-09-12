package Service;

import Entity.Consommation;
import Entity.User;
import Repository.ConsommationRepository;

import java.util.Optional;

public class ConsommationService {

    private UserService userService;
    private ConsommationRepository consommationRepository;

    public ConsommationService(UserService userService, ConsommationRepository consommationRepository) {
        this.userService = userService;
        this.consommationRepository = consommationRepository;
    }

    public Optional<Consommation> addConsommation(Consommation consommation,int userID){
        Optional<User> user = userService.findUserById(userID);
        if (user.isEmpty()){
            return Optional.empty();
        }
        consommation.setUser(user.get());
        return consommationRepository.addConsommation(consommation);
    }





//    public double consomationTotal(User user) {
//        ConsomationRepository conR = new ConsomationRepository();
//        List<Consomation> consomationList = conR.getCOnsomtionOfUser(user.getId());
//        double totalImpact = consomationList
//                .stream()
//                .mapToDouble(Consomation::calculerImpact)
//                .sum();
//        System.out.println("Total impact pour l'utilisateur " + user.getId() + ": " + totalImpact);
//        return totalImpact;
//    }
//
//
//
//    public void filterByConsuption() {
//        List<User> allUsers = this.findAll();
//        List<User> filteredUsers = allUsers
//                .stream()
//                .filter(e -> consomationTotal(e) > 310000)
//                .collect(Collectors.toList());
//        System.out.println("Utilisateurs avec une consommation totale > 31000 :");
//        for (User user : filteredUsers) {
//            System.out.println(user);
//        }
//
//    }



}
