package com.orderplatform.newproject.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public User create(@RequestBody UserDto dto ){
        return usersService.create(dto.name(), dto.email());
    }

    @GetMapping
    public List<User> getAll() {
        return usersService.getAll();
    }
}
