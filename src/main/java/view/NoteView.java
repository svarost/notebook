package view;

import controllers.NoteController;
import model.Note;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NoteView {
    private final NoteController noteController;

    public NoteView(NoteController noteController) {
        this.noteController = noteController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = promt("\nВведите команду: ").toUpperCase();
            try {
                com = Commands.valueOf(command);
                if (com == Commands.EXIT) {
                    return;
                }
                switch (com) {
                    case LIST -> listNote();
                    case READ -> readNote();
                    case CREATE -> createNote();
                    case DELETE -> deleteNote();
                    case UPDATE -> updateNote();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Введена недопустимия команда.\n");
            }
        }
    }

    private void createNote() {
        String title = promt("Введите название заметки: ");
        title = title.isEmpty() ? "null" : title;
        String text = promt("Введите текст заметки: ");
        text = text.isEmpty() ? "null" : text;
        noteController.saveNote(new Note(noteController.setNoteID(), title, text));
    }

    private void readNote() {
        int noteID = Integer.parseInt(promt("Введите ID заметки: "));
        System.out.println(noteController.getNote(noteID));
    }

    private void listNote() {
        List<Note> listNote = noteController.getListNote();
        Collections.sort(listNote, (note1, note2) -> note1.getId() - note2.getId());
        System.out.println("Note ID \t Note name");
        for (Note note : listNote) {
            System.out.printf("%s \t\t\t %s\n", note.getId(), note.getTitle());
        }
    }

    private void updateNote() {
        int noteID = Integer.parseInt(promt("Для редактирования заметки, введите ее ID: "));
        Note note = noteController.getNote(noteID);
        String title = promt("Введите название заметки: ");
        if (!title.isEmpty()) note.setTitle(title);
        String text = promt("Введите текст заметки: ");
        if (!text.isEmpty()) note.setText(text);
        noteController.updateNote(note);
    }

    private void deleteNote() {
        int noteID = Integer.parseInt(promt("Для удаления заметки, введите ее ID: "));
        noteController.deleteNote(noteID);
    }

    private String promt(String s) {
        Scanner in = new Scanner(System.in);
        System.out.println(s);
        return in.nextLine();
    }

    private void initNote() {
        List<Note> lstNote = noteController.getListNote();
    }
}
