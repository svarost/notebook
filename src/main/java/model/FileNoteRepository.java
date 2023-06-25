package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileNoteRepository implements NoteRepository {
    private String noteDirectory = "notes";
    private NoteMapper mapper = new NoteMapper();

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
        StringBuilder text = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
            fr.close();
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
        String noteText = mapper.map(note);
        try {
            Files.write(filePath, noteText.getBytes(), StandardOpenOption.CREATE);
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
    public Note getNote(int noteID) {
        List<Note> noteList = getNoteList();
        for (Note note : noteList) {
            if (note.getId().equals(noteID)) {
                return note;
            }
        }
        return null;
    }

    @Override
    public void deleteNote(int noteID) {
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

    @Override
    public void updateNote(Note note) {
        Note originNote = getNote(note.getId());
        String originFileName = originNote.getTitle() + ".txt";
        Path originPath = Path.of(noteDirectory, originFileName);
        if (!originNote.getTitle().equals(note.getTitle())) {
            Path filePath = Path.of(noteDirectory, note.getTitle() + ".txt");
            try {
                Files.move(originPath, filePath);

            } catch (IOException e) {
                System.out.println("Не удалось переименовать заметку.");
            }
        }
        String text = mapper.map(note);

        try {
            FileWriter fw = new FileWriter(String.valueOf(Path.of(noteDirectory, note.getTitle() + ".txt")));
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении заметки.");
            e.printStackTrace();
        }
    }

    @Override
    public int setNoteID() {
        List<Note> noteList = getNoteList();
        int maxID = noteList.get(0).getId();
        for (Note note : noteList) {
            if (note.getId() > maxID) {
                maxID = note.getId();
            }
        }
        return maxID + 1;
    }
}
