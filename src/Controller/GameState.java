package Controller;

import Model.Deck;
import Model.Player;
import View.View;

import java.io.*;

public class GameState implements Serializable {
    private final Deck board;
    private final Player player1_r;
    private final Player player2_y;
    private final Player PlayerPlaying;

    public GameState(Deck board, Player player1_r, Player player2_y, Player PlayerPlaying) {
        this.board = board;
        this.player1_r = player1_r;
        this.player2_y = player2_y;
        this.PlayerPlaying = PlayerPlaying;
    }

    public Deck getBoard() {
        return board;
    }

    public Player getPlayer1_r() {
        return player1_r;
    }

    public Player getPlayer2_y() {
        return player2_y;
    }

    public Player getPlayerPlaying() {
        return PlayerPlaying;
    }
}