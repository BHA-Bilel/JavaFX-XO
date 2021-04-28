package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GameApp extends GridPane {

    private final Handler handler;
    public int parties_won, parties_lost, drawCount;
    private boolean yourTurn, playable = false;
    private Tile[][] board;
    private final List<Combo> combos = new ArrayList<>();
    private final Line line = new Line();

    private final GameClient gameClient;
    private int playerID;
    private final String yourName, opName;

    public GameApp(Socket gameSocket, String name, String opName) {
        this.yourName = name;
        this.opName = opName;
        gameClient = new GameClient(gameSocket);
        gameClient.handShake();
        setPrefSize(600, 600);
        handler = new Handler(this);
        board = new Tile[3][3];
        setAlignment(Pos.CENTER);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Tile tile = new Tile(handler, x, y);
                GridPane.setHalignment(tile, HPos.CENTER);
                add(tile, x, y);
                board[x][y] = tile;
            }
        }

        // horizontal
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }

        // vertical
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }

        // diagonals
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));
    }

    public void drawLine(Tile[] tiles, boolean youWon) {
        Platform.runLater(() -> {
            Timeline timeLine = new Timeline();

            for (int i = 0; i < 3; i++) {
                timeLine.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new KeyValue(tiles[i].getText().fillProperty(), Color.GREEN)));
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
        Platform.runLater(() -> {
            getChildren().clear();
            combos.clear();
            board = new Tile[3][3];
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    Tile tile = new Tile(handler, x, y);
                    GridPane.setHalignment(tile, HPos.CENTER);
                    add(tile, x, y);
                    board[x][y] = tile;
                }
            }
            // horizontal
            for (int y = 0; y < 3; y++) {
                combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
            }

            // vertical
            for (int x = 0; x < 3; x++) {
                combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
            }

            // diagonals
            combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
            combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

            if (!youWon) {
                waitForYourTurn();
            }
            setYourTurn(youWon);
            setPlayable(true);
        });
    }

    public void waitForYourTurn() {
        Thread t = new Thread(() -> {
            int[] coor = gameClient.receive();
            board[coor[0]][coor[1]].play();
        });
        t.start();
    }

    public synchronized void closeGameApp() {
        gameClient.closeConn();
        Platform.runLater(() -> getChildren().clear());
    }

    public void showResults() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game");
            alert.setHeaderText("Results");
            String text = yourName + " : " + parties_won + "\n";
            text += opName + " : " + parties_lost + "\n";
            text += "Draws : " + drawCount;
            alert.setContentText(text);
            alert.show();
        });
    }

    // CLIENT CONNECTION
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

    // GETTERS SETTERS

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public List<Combo> getCombo() {
        return combos;
    }

    public GameClient getCSC() {
        return gameClient;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getPlayerID() {
        return playerID;
    }
}
