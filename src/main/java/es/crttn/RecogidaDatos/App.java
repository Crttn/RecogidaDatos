package es.crttn.RecogidaDatos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.crttn.RecogidaDatos.Person;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App extends Application {

    private TextField nameText;
    private TextField lastNamesText;
    private TextField ageText;

    @Override
    public void start(Stage stage) {

        Label nameLabel = new Label();
        nameLabel.setText("Nombre: ");
        nameText = new TextField();

        Label lastNamesLabel = new Label();
        lastNamesLabel.setText("Apelldios: ");
        lastNamesText = new TextField();

        Label ageLabel = new Label();
        ageLabel.setText("Edad: ");
        ageText = new TextField();


        Button saveButton = new Button();
        saveButton.setText("Guardar");
        saveButton.setDefaultButton(true);
        saveButton.setOnAction(this::onActionSaveUser);

        Button clearButton = new Button();
        clearButton.setText("Limpiar");
        clearButton.setOnAction(this::setOnActionClearButtons);


        // Creating HBox for name input
        HBox nameBox = new HBox(5, nameLabel, nameText);
        nameBox.setAlignment(Pos.CENTER);

        // Creating HBox for last names input
        HBox lastNamesBox = new HBox(5, lastNamesLabel, lastNamesText);
        lastNamesBox.setAlignment(Pos.CENTER);

        // Creating HBox for age input
        HBox ageBox = new HBox(5, ageLabel, ageText);
        ageBox.setAlignment(Pos.CENTER);

        // Creating HBox for buttons
        HBox buttonsBox = new HBox(10, saveButton, clearButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(nameBox, lastNamesBox, ageBox, buttonsBox);

        Scene scene = new Scene(root, 640, 480);

        stage.setTitle("RecogidaDatos");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionSaveUser(ActionEvent e) {
        try {
            Person p = new Person();
            p.setName(nameText.getText());
            p.setLastName(lastNamesText.getText());
            p.setEdad(Integer.valueOf(ageText.getText()));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            String json = gson.toJson(p);

            File jsonFile = new File("datos.json");
            try {
                Files.writeString(jsonFile.toPath(), json);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setOnActionClearButtons(ActionEvent e) {
        nameText.clear();
        lastNamesText.clear();
        ageText.clear();
    }
}
