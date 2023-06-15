package model;

public class NoteMapper {
    public String map(Note note) {
        return String.format("%s\t,%s\t", note.getId(), note.getText());
    }

    public Note map(String line) {
        String[] lines = line.split("\t");
        return new Note(Integer.valueOf(lines[0]), lines[1], lines[2]);
    }
}
