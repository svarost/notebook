import controllers.NoteController;
import model.FileNoteRepository;
import model.NoteRepository;
import view.NoteView;

public class Main {
    public static void main(String[] args) {
        NoteRepository repository = new FileNoteRepository();
        NoteController controller = new NoteController(repository);
        NoteView view = new NoteView(controller);
        view.run();
    }
}
