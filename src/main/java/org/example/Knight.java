package org.example;

public class Knight extends ChessPiece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);

        // Проверка возможности хода конем
        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
            // Проверка наличия фигуры на конечной позиции и проверка на дружественную пешку
            if (board.getBoard()[endX][endY] == null || !(board.getBoard()[endX][endY] instanceof Pawn && board.getBoard()[endX][endY].GetColor() == this.GetColor())) {
                return true; // Ход возможен, нет дружественной пешки на конечной позиции
            }
        }
        return false; // В случае невозможности хода возвращаем false
    }

    @Override
    public String getImage(Color pieceColor) {

        if (pieceColor == Color.WHITE) {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece2.png";
        } else {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece8.png";
        }
    }

    public Name getPieceSymbol() {
        return Name.H;
    }
}

