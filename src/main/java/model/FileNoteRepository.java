package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileNoteRepository implements NoteRepository {
    private String noteDirectory = "notes";
    private NoteMapper mapper;

    public FileNoteRepository() {
        creatNotesDirectory();
    }

    private void creatNotesDirectory() {
        File directory = new File(noteDirectory);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Папка для сохранения заметок создана.");
            } else {
                System.out.println("Не удалось создать папку для хранения заметок.");
            }
        }
    }

    private File[] getNoteFiles() {
        File directory = new File(noteDirectory);
        if (directory.exists()) {
            return directory.listFiles((dir, name) -> name.endsWith(".txt"));
        }
        return null;
    }

    private Note fileToNote(File file) {
        StringBuilder text = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mapper.map(text.toString());
    }

    @Override
    public void creatNote(Note note) {
        String fileName = note.getTitle() + ".txt";
        Path filePath = Path.of(noteDirectory, fileName);
        try {
//            Files.write(filePath, note.getText().getBytes(StandardCharsets.UTF_8))
            Files.write(filePath, note.getText().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении заметки.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> getNoteList() {
        File[] noteFiles = getNoteFiles();
        if (noteFiles == null || noteFiles.length == 0) {
            System.out.println("Заметки не найдены.");
            return null;
        } else {
            List<Note> noteList = new ArrayList<>();
            for (File file : noteFiles) {
                noteList.add(fileToNote(file));
            }
            return noteList;
        }
    }

    @Override
    public Note getNote(Integer noteID) {
        List<Note> noteList = getNoteList();
        for (Note note : noteList) {
            if (note.getId().equals(noteID)) {
                return note;
            }
        }
        return null;
    }

    @Override
    public void deleteNote(Integer noteID) {
        List<Note> noteList = getNoteList();
        for (Note note : noteList) {
            if (note.getId().equals(noteID)) {
                String fileName = note.getTitle() + ".txt";
                Path filePath = Path.of(noteDirectory, fileName);

                if (Files.exists(filePath)) {
                    try {
                        Files.delete(filePath);
                        System.out.println("Заметка удалена.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при удалении заметки.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Заметка не найдена.");
                }
            }
        }
    }
}
