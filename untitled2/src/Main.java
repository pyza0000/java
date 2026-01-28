import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // Ścieżki do obrazów w resources
    private final String[] paths = {
            "/img/img1.jpg",
            "/img/img2.jpg",
            "/img/img3.jpg",
            "/img/img4.jpg"
    };

    private int index = 0; // aktualny obraz

    @Override
    public void start(Stage stage) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true); // zachowuje proporcje
        imageView.setSmooth(true);

        Button btn = new Button("Wyświetl");
        btn.setOnAction(e -> {
            // wczytaj i pokaż aktualny
            Image img = new Image(getClass().getResourceAsStream(paths[index]));
            imageView.setImage(img);

            // przesuń indeks cyklicznie 0..3
            index = (index + 1) % paths.length;
        });

        VBox root = new VBox(12, imageView, btn);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        stage.setTitle("Prosta przeglądarka obrazów");
        stage.setScene(new Scene(root, 420, 520));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
