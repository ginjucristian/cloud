package com.cloud_lab.tema3_cloud.core.infrastructure;

import com.cloud_lab.tema3_cloud.core.domain.infrastructure.IUserRepository;
import com.cloud_lab.tema3_cloud.core.domain.model.User;
import com.cloud_lab.tema3_cloud.core.infrastructure.entity.UserEntity;
import com.cloud_lab.tema3_cloud.core.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseRepository implements IUserRepository {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAll() {

        List<User> userList = new ArrayList<>();
        List<UserEntity> userEntities = userRepo.findAll();

        for (UserEntity userEntity : userEntities) {
            userList.add(UserMapper.fromEntity(userEntity));
        }

        return userList;
    }

    @Override
    public User getById(int id) {
        return UserMapper.fromEntity(userRepo.findById(id).get());
    }

    @Override
    public User getByEmail(String email) {
        return UserMapper.fromEntity(userRepo.findByEmail(email));
    }

    @Override
    public User insert(User user) {
        UserEntity inserted = userRepo.saveAndFlush(UserMapper.toEntity(user));
        userRepo.flush();
        return UserMapper.fromEntity(inserted);
    }

    @Override
    public boolean delete(Integer id) {
        UserEntity user = userRepo.getOne(id);

        userRepo.deleteById(id);
        return true;
    }

    @Override
    public User update(User user) {
        return UserMapper.fromEntity(
                userRepo.save(UserMapper.toEntity(user))
        );
    }
}
