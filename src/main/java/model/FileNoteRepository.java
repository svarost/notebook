package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNoteRepository implements NoteRepository{
    private java.lang.String noteDirectory = "notes";

    public FileNoteRepository() {
        creatNotesDirectory();
    }

    private void creatNotesDirectory() {
        File directory = new File(noteDirectory);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("����� ��� ���������� ������� �������.");
            } else {
                System.out.println("�� ������� ������� ����� ��� �������� �������.");
            }
        }
    }


    @Override
    public List<String> getNoteTitleList() {
        File [] noteFiles = getNoteFiles();
        if (noteFiles == null || noteFiles.length == 0) {
            System.out.println("������� �� �������.");
            return null;
        } else {
            List<String> noteList = new ArrayList<>();
            for (File file : noteFiles) {
                noteList.add(file.getName());
            }
            return  noteList;
        }
    }

    @Override
    public String getNote(String noteName) {
        return null;
    }

    @Override
    public void editNote(String NoteID) {

    }

    @Override
    public void deleteNote(String note) {

    }


    public void displayNotes() {
        File [] noteFiles = getNoteFiles();
        if (noteFiles == null || noteFiles.length == 0) {
            System.out.println("������� �� �������.");
            return;
        }

        System.out.println("������ �������:");
        for (File file : noteFiles) {
            System.out.println(file.getName());
        }
    }

//    @Override
//    public void displayNote(java.lang.String noteName) {
//        File [] noteFiles = getNoteFiles();
//        if (noteFiles == null || noteFiles.length == 0) {
//            System.out.println("������� �� �������.");
//            return;
//        }
//
//        for (File file : noteFiles) {
//            if (file.getName().equals(noteName)) {
//
//            }
//        }
//    }


    private File[] getNoteFiles() {
        File directory = new File(noteDirectory);
        if (directory.exists()) {
            return directory.listFiles((dir, name) -> name.endsWith(".txt"));
        }
        return null;
    }


}
