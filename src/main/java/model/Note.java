package model;

public class Note {
    static Integer count;
    private Integer id;
    private String title;
    private String text;

    public Note() {
        if (count != null) {
            count = 0;
        } else count++;
        setId();
    }

    public Note(String title) {
        this();
        this.title = title;
    }

    public Note(String title, String text) {
        this();
        this.title = title;
        this.text = text;
    }

    public Note(Integer id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    void setId() {
        if (count.equals(null)) {
            id = 0;
        } else id += 1;
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
}
