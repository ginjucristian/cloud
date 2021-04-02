package com.cloud_lab.tema3_cloud.core.domain.infrastructure;

import com.cloud_lab.tema3_cloud.core.domain.model.Note;
import com.cloud_lab.tema3_cloud.core.domain.model.User;

import java.util.List;

public interface INoteRepository {
    List<Note> getAll();

    Note getById(int id);

    List<Note> getNotesForUser(User user);

    Note insert(Note note);

    boolean delete(Integer id);

    Note update(Note note);
}
