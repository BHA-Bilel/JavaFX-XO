package bg.xo.game;

import bg.xo.MainApp;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {

    private final int x, y;
    private final Label text;
    private final GameApp gameApp;

    public Tile(GameApp gameApp, int x, int y) {
        this.gameApp = gameApp;
        this.x = x;
        this.y = y;
        Region border = new Region();
        border.setStyle("-fx-border-color: black");
        border.prefWidthProperty().bind(gameApp.heightProperty().divide(3));
        border.prefHeightProperty().bind(gameApp.heightProperty().divide(3));
        text = new Label();
        text.styleProperty().bind(Bindings.concat("-fx-font-size: ", MainApp.fontProperty.multiply(3).asString()));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
        setOnMouseClicked(event -> {
            if (!text.getText().isEmpty() || event.getButton() != MouseButton.PRIMARY || !gameApp.isPlayable()
                    || !gameApp.isYourTurn())
                return;
            play();
        });
    }

    public void play() {
        gameApp.cpt++;
        if (this.gameApp.isYourTurn()) {
            text.setText("X");
            gameApp.setYourTurn(false);
            gameApp.sendCoor(x, y);
            if (gameApp.NothingHappened())
                gameApp.waitForYourTurn();
        } else {
            text.setText("O");
            if (gameApp.NothingHappened())
                gameApp.setYourTurn(true);
        }
    }

    public String getValue() {
        return text.getText();
    }

    public Label getLabel() {
        return text;
    }

    public void reset() {
        text.setText("");
    }
}
