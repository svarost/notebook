package model;

import java.util.List;

public interface NoteRepository {
    void creatNote(Note note);
    List<Note> getNoteList();
    Note getNote(Integer noteID);
    void deleteNote(Integer NoteID);
}
