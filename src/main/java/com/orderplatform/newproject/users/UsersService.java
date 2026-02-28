package com.orderplatform.newproject.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void delete(Long id){
        // check if user exist
       if(!userRepository.existsById(id)) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
       }

        userRepository.deleteById(id);
    }

    public User update(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow();

        if(dto.name() != null) {
            user.setName(dto.name());
        }

        if(dto.email() != null) {
            user.setName(dto.email());
        }

        return userRepository.save(user);
    }
}
