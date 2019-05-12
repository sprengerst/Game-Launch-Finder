package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    private static final String DIRECTORY_PATH = "E:\\VR-Part";

    private static final ArrayList<String> ignoreNames = new ArrayList<>(Arrays.asList("LAUNCHER", "LAUNCHER_x64", "UnityCrashHandler64"));

    @Override
    public void start(Stage primaryStage) {

        ObservableList<Game> gameList = FXCollections.observableArrayList();

        File[] files = new File(DIRECTORY_PATH).listFiles();

        for (File subDir : files) {
            File[] subFiles = subDir.listFiles();
            System.out.println("Check subDir: " + subDir);

            if (subDir.isDirectory()) {
                boolean exeFound = false;
                for (File subFile : subFiles) {
                    if(!subFile.isDirectory()) {
                        String extension = FilenameUtils.getExtension(subFile.getAbsolutePath());
                        String filename = FilenameUtils.removeExtension(subFile.getName());

                        System.out.println("Check: " + filename + " . " + extension);

                        if (extension.equals("exe") && !ignoreNames.contains(filename)) {
                            exeFound = true;
                            System.out.println("FOUND EXE: " + filename);
                            gameList.add(new Game(subFile.getAbsolutePath(), filename));
                        }
                    }
                }

                if (!exeFound) {

                    // checking subdir
                    for (File subFile : subFiles) {
                        if(subFile.isDirectory()) {

                            File[] subSubFiles = subFile.listFiles();

                            for (File subSubFile : subSubFiles) {
                                if(!subSubFile.isDirectory()) {
                                    String extension = FilenameUtils.getExtension(subSubFile.getAbsolutePath());
                                    String filename = FilenameUtils.removeExtension(subSubFile.getName());

                                    System.out.println("Check: " + filename + " . " + extension);

                                    if (extension.equals("exe") && !ignoreNames.contains(filename)) {
                                        System.out.println("FOUND EXE: " + filename);
                                        gameList.add(new Game(subFile.getAbsolutePath(), filename));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        ListView<Game> lv = new ListView<>(gameList);
        lv.setCellFactory(param -> new XCell());

        StackPane root = new StackPane();
        primaryStage.setTitle("Game Launch Finder");
        root.getChildren().add(lv);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
