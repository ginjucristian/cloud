package com.cloud_lab.tema3_cloud.controller;

import com.cloud_lab.tema3_cloud.core.application.NoteService;
import com.cloud_lab.tema3_cloud.core.application.dto.NoteDto;
import com.cloud_lab.tema3_cloud.core.domain.model.Note;
import com.cloud_lab.tema3_cloud.utils.ResponseWrapper;
import com.cloud_lab.tema3_cloud.utils.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotesController {
    @Autowired
    NoteService noteService;

    @GetMapping("/api/v1/notes/{userId}")
    public ResponseWrapper<List<Note>> getNotesForUser(@PathVariable Integer userId) {
        return new ResponseWrapper<>(noteService.listUserNotes(userId), "200");
    }

    @PostMapping("/api/v1/note")
    public ResponseWrapper<Note> createNote(@RequestBody NoteDto noteDto) {
        return new ResponseWrapper<>(noteService.saveNote(noteDto), "200");
    }

    @PutMapping("/api/v1/note")
    public ResponseWrapper<Note> updateNote(@RequestBody NoteDto noteDto) {
        return new ResponseWrapper<>(noteService.editNote(noteDto), "200");
    }

    @DeleteMapping("/api/v1/note/{userId}/{noteId}")
    public ResponseWrapper<StatusResponse> updateNote(@PathVariable Integer userId, @PathVariable Integer noteId) {
        StatusResponse statusResponse = new StatusResponse(noteService.deleteNote(noteId));

        return new ResponseWrapper<>(statusResponse, "200");
    }
}
