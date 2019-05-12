package app;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;

public class XCell extends ListCell<Game> {
    private HBox hbox = new HBox();
    private Label label = new Label("(empty)");
    private Pane pane = new Pane();
    private Button button = new Button("Launch");
    private Game lastItem;

    public XCell() {
        super();
        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> {
            try {
                Utility.runExeWithAdmin(lastItem.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    protected void updateItem(Game item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            label.setText(item != null ? item.getName() : "<null>");
            setGraphic(hbox);
        }
    }
}