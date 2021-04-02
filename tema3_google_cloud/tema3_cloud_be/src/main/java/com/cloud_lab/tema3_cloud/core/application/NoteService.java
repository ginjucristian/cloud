package com.cloud_lab.tema3_cloud.core.application;

import com.cloud_lab.tema3_cloud.core.application.dto.NoteDto;
import com.cloud_lab.tema3_cloud.core.domain.infrastructure.INoteRepository;
import com.cloud_lab.tema3_cloud.core.domain.infrastructure.IUserRepository;
import com.cloud_lab.tema3_cloud.core.domain.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    INoteRepository noteRepository;

    @Autowired
    IUserRepository userRepository;

    public List<Note> listUserNotes(int userId) {
        return noteRepository.getNotesForUser(
                userRepository.getById(userId)
        );
    }

    public Note saveNote(NoteDto noteDto) {
        return noteRepository.insert(
                new Note(noteDto.id, noteDto.note_title, noteDto.note_description, noteDto.user_id)
        );
    }

    public boolean deleteNote(int noteId) {
        return noteRepository.delete(noteId);
    }

    public Note editNote(NoteDto noteDto) {
        return noteRepository.update(
                new Note(noteDto.id, noteDto.note_title, noteDto.note_description, noteDto.user_id)
        );
    }
}
