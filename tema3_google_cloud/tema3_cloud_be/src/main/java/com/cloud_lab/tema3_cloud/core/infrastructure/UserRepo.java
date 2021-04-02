package com.cloud_lab.tema3_cloud.core.infrastructure;

import com.cloud_lab.tema3_cloud.core.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
