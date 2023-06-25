package controllers;

import model.NoteRepository;
import model.Note;

import java.util.List;

public class NoteController {
    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    public void saveNote(Note note) {
        repository.creatNote(note);
    }

    public Note getNote(int noteID) {
        return repository.getNote(noteID);
    }

    public List<Note> getListNote() {
        return repository.getNoteList();
    }

    public void deleteNote(int noteID) {
        repository.deleteNote(noteID);
    }

    public void updateNote(Note note) {
        repository.updateNote(note);
    }

    public int setNoteID() {
        return repository.setNoteID();
    }
}
