package com.orderplatform.newproject.service;
import com.orderplatform.newproject.domain.User;
import com.orderplatform.newproject.exception.UserAlreadyExistException;
import com.orderplatform.newproject.exception.UserNotFoundException;
import com.orderplatform.newproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public User create(String email, String password) {
        //check if user exist
        if(userRepository.existsByEmail(email)) {
            throw  new UserAlreadyExistException(email);
        }

        User user = User.create(email, password);

        userRepository.save(user);

        return  user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return  user;
    }

    public boolean existByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }
}
