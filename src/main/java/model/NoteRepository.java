package model;

import java.util.List;

public interface NoteRepository {
    void creatNote(Note note);

    List<Note> getNoteList();

    Note getNote(int noteID);

    void deleteNote(int NoteID);

    void updateNote(Note note);

    int setNoteID();
}
