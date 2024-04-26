package notpritam;

import notpritam.controller.GameController;
import notpritam.exception.InvalidMoveException;
import notpritam.models.game.Game;
import notpritam.models.game.GameState;
import notpritam.models.player.*;
import org.abhinavgpt.models.player.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        GameController gameControllerInstance = new GameController();

        int gameDimension = 3;
        List<Player> gamePlayers = List.of(new Player("Abhinav", new Symbol('X'), PlayerType.HUMAN), new Bot("Bot", new Symbol('O'), PlayerType.BOT, BotDificultyLevel.EASY));

        Game currentGame = gameControllerInstance.startGame(gameDimension, gamePlayers);

        try {
            while (GameState.IN_PROGRESS.equals(currentGame.getState())) {
                gameControllerInstance.printBoard(currentGame);
                gameControllerInstance.makeMove(currentGame);
            }
        }
        catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }

        if (!GameState.ENDED.equals(gameControllerInstance.checkState(currentGame))) {
            currentGame.setState(GameState.DRAW);
            System.out.println("Game DRAW");
        } else {
            gameControllerInstance.printBoard(currentGame);
            System.out.println("Player " + gameControllerInstance.getWinner(currentGame).getName() + " is the winner");
        }
    }
}