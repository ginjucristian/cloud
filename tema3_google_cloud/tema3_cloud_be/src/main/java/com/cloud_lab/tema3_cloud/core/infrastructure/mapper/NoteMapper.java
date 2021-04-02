package com.cloud_lab.tema3_cloud.core.infrastructure.mapper;

import com.cloud_lab.tema3_cloud.core.domain.model.Note;
import com.cloud_lab.tema3_cloud.core.infrastructure.UserRepo;
import com.cloud_lab.tema3_cloud.core.infrastructure.entity.NoteEntity;

public class NoteMapper {
    public static Note fromEntity(NoteEntity noteEntity) {
        return new Note(noteEntity.id, noteEntity.noteTitle, noteEntity.noteDescription, noteEntity.user.id);
    }

    public static NoteEntity toEntity(Note note, UserRepo repo) {
        NoteEntity result = new NoteEntity();
        result.id = note.getId();
        result.noteDescription = note.getDescription();
        result.noteTitle = note.getTitle();
        result.user = repo.findById(note.getUserId()).get();

        return result;
    }
}
