package game;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private final int x, y;
    private final Text text = new Text();
    private final Handler handler;

    public Tile(Handler handler, int x, int y) {
        this.x = x;
        this.y = y;
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        text.setFont(Font.font(72));
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
        this.handler = handler;
        setOnMouseClicked(event -> {
            if (!text.getText().isEmpty() || event.getButton() != MouseButton.PRIMARY || !handler.getGame().isPlayable()
                    || !handler.getGame().isYourTurn())
                return;
            play();
        });
    }

    public void play() {
        if (this.handler.getGame().isYourTurn()) {
            text.setText("X");
            handler.getGame().setYourTurn(false);
            handler.getGame().getCSC().sendCoor(x, y);
            if (NothingHappened(handler.getGame().getCombo())) {
                handler.getGame().waitForYourTurn();
            }
        } else {
            text.setText("O");
            if (NothingHappened(handler.getGame().getCombo()))
                handler.getGame().setYourTurn(true);
        }
    }

    public boolean NothingHappened(List<Combo> combos) {
        for (Combo c : combos) {
            if (c.isComplete()) {
                handler.getGame().setPlayable(false);
                boolean youWon = c.getTiles()[0].getValue().equals("X");
                if (youWon)
                    handler.getGame().parties_won++;
                else
                    handler.getGame().parties_lost++;
                handler.getGame().drawLine(c.getTiles(), youWon);
                return false;
            }
        }
        int cpt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!handler.getGame().getBoard()[i][j].isEmpty())
                    cpt++;
            }
        }
        if (cpt == 9) {
            handler.getGame().startNewGame(
                    handler.getGame().getDrawCount() % 2 == (handler.getGame().getPlayerID() - 1));
            handler.getGame().drawCount++;
            handler.getGame().showResults();
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return text.getText().isEmpty();
    }

    public String getValue() {
        return text.getText();
    }

    public Text getText() {
        return text;
    }

}
