package bg.xo.game;

import bg.xo.lang.Language;
import bg.xo.popup.MyAlert;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameApp extends GridPane {

    private final Tile[][] board;
    private final List<Combo> combos;
    private final GameClient gameClient;
    private final String yourName, opName;

    private boolean yourTurn, playable = false;
    private Alert results_alert;
    private int playerID;

    public int parties_won, parties_lost, drawCount;
    public int cpt = 0;

    public GameApp(Socket gameSocket, String name, String opName) {
        this.yourName = name;
        this.opName = opName;
        combos = new ArrayList<>();
        gameClient = new GameClient(gameSocket);
        gameClient.handShake();
        board = new Tile[3][3];
        setAlignment(Pos.CENTER);
        createGUI();
    }

    private void createGUI() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Tile tile = new Tile(this, x, y);
                GridPane.setHalignment(tile, HPos.CENTER);
                GridPane.setFillHeight(tile, true);
                add(tile, x, y);
                board[x][y] = tile;
            }
        }

        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }

        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }

        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));
    }

    public void drawLine(Tile[] tiles, boolean youWon) {
        Platform.runLater(() -> {
            Timeline timeLine = new Timeline();

            for (int i = 0; i < 3; i++) {
                timeLine.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new KeyValue(tiles[i].getLabel().textFillProperty(), Color.GREEN)));
            }
            timeLine.setAutoReverse(true);
            timeLine.setCycleCount(4);
            timeLine.play();
            timeLine.setOnFinished(e -> {
                startNewGame(youWon);
                showResults();
            });
        });
    }

    public void startNewGame(boolean youWon) {
        cpt = 0;
        Platform.runLater(() -> {
            for (Tile[] sub : board) {
                for (Tile tile : sub) {
                    tile.reset();
                }
            }
            if (!youWon) {
                waitForYourTurn();
            }
            yourTurn = youWon;
            playable = true;
        });
    }

    public void waitForYourTurn() {
        Thread t = new Thread(() -> {
            int[] coor = gameClient.receive();
            Platform.runLater(() -> board[coor[0]][coor[1]].play());
        });
        t.start();
    }

    public synchronized void closeGameApp() {
        gameClient.closeConn();
        Platform.runLater(() -> getChildren().clear());
    }

    public void sendCoor(int x, int y) {
        gameClient.sendCoor(x, y);
    }

    public void showResults() {
        if (results_alert != null && results_alert.isShowing())
            return;
        Platform.runLater(() -> {
            results_alert = new MyAlert(Alert.AlertType.INFORMATION, Language.GR_H);
            String text = yourName + " : " + parties_won + "\n";
            text += opName + " : " + parties_lost + "\n";
            text += Language.DRAWS.getValue() + drawCount;
            results_alert.setContentText(text);
            results_alert.show();
        });
    }

    public boolean NothingHappened() {
        for (Combo c : combos) {
            if (!c.isComplete()) continue;
            playable = false;
            boolean youWon = c.getTiles()[0].getValue().equals("X");
            if (youWon)
                parties_won++;
            else
                parties_lost++;
            drawLine(c.getTiles(), youWon);
            return false;
        }
        if (cpt == 9) {
            startNewGame(drawCount % 2 == (playerID - 1));
            drawCount++;
            showResults();
            return false;
        }
        return true;
    }

    static class Combo {
        private final Tile[] tiles;

        public Combo(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            return !tiles[0].getValue().isEmpty() && tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[1].getValue().equals(tiles[2].getValue());
        }

        public Tile[] getTiles() {
            return tiles;
        }
    }

    class GameClient {

        private Socket gameSocket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

        public GameClient(Socket gameSocket) {
            try {
                this.gameSocket = gameSocket;
                gameSocket.setSoTimeout(0);
                dataOut = new DataOutputStream(this.gameSocket.getOutputStream());
                dataIn = new DataInputStream(this.gameSocket.getInputStream());
            } catch (IOException ignore) {
            }
        }

        public void closeConn() {
            try {
                dataOut.close();
                dataIn.close();
                gameSocket.close();
            } catch (IOException ignore) {
            }
        }

        public void handShake() {
            try {
                playerID = dataIn.readInt();
                if (playerID == 1) {
                    yourTurn = true;
                    Thread t = new Thread(() -> {
                        try {
                            playable = dataIn.readBoolean();
                        } catch (IOException ignore) {
                        }
                    });
                    t.start();
                } else {
                    yourTurn = false;
                    playable = true;
                    waitForYourTurn();
                }
            } catch (IOException ignore) {
            }
        }

        public void sendCoor(int x, int y) {
            try {
                dataOut.writeInt(x);
                dataOut.writeInt(y);
                dataOut.flush();
            } catch (IOException ignore) {
            }
        }

        public int[] receive() {
            int[] coor = new int[2];
            try {
                coor[0] = dataIn.readInt();
                coor[1] = dataIn.readInt();
            } catch (IOException ignore) {
            }
            return coor;
        }
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public boolean isPlayable() {
        return playable;
    }

}
