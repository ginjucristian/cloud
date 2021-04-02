package com.cloud_lab.tema3_cloud.core.domain.infrastructure;

import com.cloud_lab.tema3_cloud.core.domain.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();

    User getById(int id);

    User getByEmail(String email);

    User insert(User users);

    boolean delete(Integer id);

    User update(User user);
}
