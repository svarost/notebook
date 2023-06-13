package model;

import java.util.List;

public interface NoteRepository {
    List<String> getNoteTitleList();
    Note getNote(String noteID);
    void editNote(String NoteID);
    void deleteNote(String note);
}
