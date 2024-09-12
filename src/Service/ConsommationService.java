package Service;

import Repository.ConsommationRepository;

public class ConsommationService {

    private UserService userService;
    private ConsommationRepository consommationRepository;

    public ConsommationService(UserService userService, ConsommationRepository consommationRepository) {
        this.userService = userService;
        this.consommationRepository = consommationRepository;
    }



}
