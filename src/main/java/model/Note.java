package model;

public class Note {
    private final int id;
    private String title;
    private String text;


    public Note(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Название: " + title + '\n' + text;
    }
}
