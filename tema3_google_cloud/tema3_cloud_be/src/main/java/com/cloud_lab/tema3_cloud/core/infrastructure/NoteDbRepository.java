package com.cloud_lab.tema3_cloud.core.infrastructure;

import com.cloud_lab.tema3_cloud.core.domain.infrastructure.INoteRepository;
import com.cloud_lab.tema3_cloud.core.domain.model.Note;
import com.cloud_lab.tema3_cloud.core.domain.model.User;
import com.cloud_lab.tema3_cloud.core.infrastructure.entity.NoteEntity;
import com.cloud_lab.tema3_cloud.core.infrastructure.mapper.NoteMapper;
import com.cloud_lab.tema3_cloud.core.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteDbRepository implements INoteRepository {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Note> getAll() {
        List<Note> result = new ArrayList<>();

        noteRepo.findAll().forEach(noteEntity -> {
            result.add(NoteMapper.fromEntity(noteEntity));
        });

        return result;
    }

    @Override
    public Note getById(int id) {
        return NoteMapper.fromEntity(
                noteRepo.findById(id).get()
        );
    }

    @Override
    public List<Note> getNotesForUser(User user) {
        List<Note> result = new ArrayList<>();

        noteRepo.findAllByUser(UserMapper.toEntity(user)).forEach(noteEntity -> {
            result.add(NoteMapper.fromEntity(noteEntity));
        });

        return result;
    }

    @Override
    public Note insert(Note note) {
        NoteEntity inserted = noteRepo.saveAndFlush(
                NoteMapper.toEntity(note, userRepo)
        );

        return NoteMapper.fromEntity(inserted);
    }

    @Override
    public boolean delete(Integer id) {
        NoteEntity noteEntity = noteRepo.getOne(id);

        noteRepo.deleteById(id);

        return true;
    }

    @Override
    public Note update(Note note) {
        NoteEntity updated = noteRepo.saveAndFlush(
                NoteMapper.toEntity(note, userRepo)
        );

        return NoteMapper.fromEntity(updated);
    }
}
