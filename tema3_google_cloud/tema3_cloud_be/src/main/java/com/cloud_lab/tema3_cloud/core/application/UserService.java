package com.cloud_lab.tema3_cloud.core.application;

import com.cloud_lab.tema3_cloud.core.application.dto.UserDto;
import com.cloud_lab.tema3_cloud.core.domain.infrastructure.IUserRepository;
import com.cloud_lab.tema3_cloud.core.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public List<User> listAllUsers() {
        return this.userRepository.getAll();
    }

    public User getUserById(Integer id) {
        return this.userRepository.getById(id);
    }

    public User getByEmail(String email) {
        return this.userRepository.getByEmail(email);
    }

    public User registerUser(UserDto userDto) {
        String password = DigestUtils.md5DigestAsHex(userDto.password.getBytes(StandardCharsets.UTF_8));

        return this.userRepository.insert(
                new User(0, userDto.first_name, userDto.last_name, userDto.email, password)
        );
    }

    public boolean removeUser(Integer userId) {
       return this.userRepository.delete(userId);
    }

    public User updateUserData(UserDto userDto) {
        return this.userRepository.update(
                new User(0, userDto.first_name, userDto.last_name, userDto.email, userDto.password)
        );
    }
}
