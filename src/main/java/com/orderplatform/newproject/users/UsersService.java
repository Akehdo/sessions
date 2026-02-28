package com.orderplatform.newproject.users;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name, String email){
        User user = new User();

        user.setName(name);
        user.setEmail(email);

        return userRepository.save(user);
    }

    public List<User>  getAll() {
        return userRepository.findAll();
    }
}
