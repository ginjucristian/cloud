package com.cloud_lab.tema3_cloud.core.infrastructure;

import com.cloud_lab.tema3_cloud.core.infrastructure.entity.NoteEntity;
import com.cloud_lab.tema3_cloud.core.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<NoteEntity, Integer> {
    List<NoteEntity> findAllByUser(UserEntity user);
}
