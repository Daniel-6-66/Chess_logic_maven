import org.example.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    public void testInitializeBoard() {
        ChessPiece[][] board = chessBoard.getBoard();
        assertNotNull(board[0][0]);
        assertNotNull(board[0][1]);
        // Другие проверки для фигур и пустых клеток
    }

    @Test
    public void testMovePieceValidMove() {
        // Предположим, что фигуры уже находятся на доске.
        // Проверка корректного хода фигуры
        boolean isMoved = chessBoard.movePiece(1, 0, 2, 0);
        assertTrue(isMoved);
        // Проверьте состояние доски после хода
        // Другие аналогичные тесты для различных сценариев хода фигур
    }

    @Test
    public void testIsCheck() {
        // Установка конкретной позиции на доске, чтобы проверить шах
        chessBoard.getBoard()[3][3] = new King(Color.WHITE);
        chessBoard.getBoard()[1][5] = new Rook(Color.BLACK);
        boolean isCheck = chessBoard.isCheck(Color.WHITE);
        assertTrue(isCheck);
    }



    // Тесты для других методов, таких как makeMoveFromConsole, convertLetterToIndex и других, можно добавить здесь

}
