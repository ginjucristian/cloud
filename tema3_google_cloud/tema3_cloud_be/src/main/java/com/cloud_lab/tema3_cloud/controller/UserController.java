package com.cloud_lab.tema3_cloud.controller;

import com.cloud_lab.tema3_cloud.core.application.UserService;
import com.cloud_lab.tema3_cloud.core.application.dto.UserDto;
import com.cloud_lab.tema3_cloud.core.domain.model.User;
import com.cloud_lab.tema3_cloud.utils.ResponseWrapper;
import com.cloud_lab.tema3_cloud.utils.StatusResponse;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index() {
        return "best app 2021";
    }

    @GetMapping("/api/v1/users")
    public ResponseWrapper<List<User>> getAll() {
        return new ResponseWrapper<List<User>>(
                userService.listAllUsers(),
                "200"
        );
    }

    @GetMapping("/api/v1/user/{id}")
    public ResponseWrapper<User> getById(@PathVariable Integer id) {
        return new ResponseWrapper<User>(
                userService.getUserById(id),
                "200"
        );
    }

    @GetMapping("/api/v1/user")
    public ResponseWrapper<User> getByEmail(@RequestParam String email) {
        return new ResponseWrapper<User>(
                userService.getByEmail(email),
                "200"
        );
    }

    @PostMapping("/api/v1/user")
    public ResponseWrapper<User> insertOne(@RequestBody UserDto body) {
        return new ResponseWrapper<User>(
                userService.registerUser(body),
                "200"
        );
    }

    @PutMapping("/api/v1/user")
    public ResponseWrapper<User> update(@RequestBody UserDto body) {
        return new ResponseWrapper<User>(
                userService.updateUserData(body),
                "200"
        );
    }

    @DeleteMapping("/api/v1/user/{id}")
    public ResponseWrapper<StatusResponse> deleteOne(@PathVariable Integer id) {
        StatusResponse result = new StatusResponse(userService.removeUser(id));
        return new ResponseWrapper<StatusResponse>(
                result,
                "200"
        );
    }
}
