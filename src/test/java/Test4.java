import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Test4 {

    @Test
    void testMakeMove() {
        Board board = new Board(6, 7);

        // Ellenőrizzük, hogy az ures tábla érvényes lépést enged-e
        assertTrue(board.isValidMove(0));

    }

    @Test
    void testCheckWinHorizontal() {
        Board board = new Board(6, 7);

        // Hozuk létre egy horizontális nyerési helyetet
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');

        assertTrue(board.checkWin('X')); // Az 'X' játékos nyer
    }

    @Test
    void testCheckWinVertical() {
        Board board = new Board(6, 7);

        // Hozuk létre egy vertikális nyerési helyzetet
        board.makeMove(0, 'X');
        board.makeMove(0, 'X');
        board.makeMove(0, 'X');
        board.makeMove(0, 'X');

        assertTrue(board.checkWin('X')); // Az 'X' játékos nyer
    }

    @Test
    void testCheckWinDiagonal() {
        Board board = new Board(6, 7);

        // Hozuk létre egy átlós nyerési helyzetet
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(2, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(3, 'O');
        board.makeMove(3, 'O');
        board.makeMove(3, 'X');

        assertTrue(board.checkWin('X')); // Az 'X' játékos nyer
    }

    @Test
    void testDisplay() {
        Board board = new Board(6, 7);

        // Tábla megjelenítésének ellenőrzés
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.display();
    }

    @Test
    void testSaveAndLoadState() {
        Board board = new Board(6, 7);
        board.makeMove(0, 'X');
        board.saveStateToFile();

        Board loadedBoard = Board.loadStateFromFile();
        assertNotNull(loadedBoard);
        assertArrayEquals(board.getGrid(), loadedBoard.getGrid()); // Ellenőrizzük, hogy a mentett állapot visszatöltése működik
    }
}
