package controllers;

import model.Note;
import model.NoteRepository;

import java.util.List;

public class NoteController {
    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    public void saveNote(Note note) {
        repository.creatNote(note);
    }

    public Note getNote(Integer noteID) {
        return repository.getNote(noteID);
    }

    public List<Note> getListNote() {
        return repository.getNoteList();
    }
}
