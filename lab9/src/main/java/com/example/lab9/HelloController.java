package com.example.lab9;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {

    @FXML
    private ImageView imageView;

    private final String[] images = {
            "C:/Users/pyza1/IdeaProjects/lab9/img1.jpg",
            "C:/Users/pyza1/IdeaProjects/lab9/img2.jpg",
            "C:/Users/pyza1/IdeaProjects/lab9/img3.jpg",
            "C:/Users/pyza1/IdeaProjects/lab9/img4.jpg",
    };

    private int index = 0;

    @FXML
    private void handleShow() {
        String path = images[index];

        Image img = new Image("file:/" + path.replace("\\", "/"));
        imageView.setImage(img);

        index = (index + 1) % images.length;
    }

}
//dodaj etykiete