package org.example;

public class Bishop extends ChessPiece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);

        if (dx == dy) {
            int xDir = (endX > startX) ? 1 : -1;
            int yDir = (endY > startY) ? 1 : -1;

            int x = startX + xDir;
            int y = startY + yDir;

            // Проверка наличия фигуры на пути движения слона
            while (x != endX && y != endY) {
                if (board.getBoard()[x][y] != null) {
                    // Если на пути встречается фигура, проверяем, является ли она дружественной
                    return false; // Недопустимый ход, на пути есть дружественная фигура

                }
                x += xDir;
                y += yDir;
            }

            // Проверка наличия фигуры на конечной позиции, если она есть, проверяем на дружественность
            if (board.getBoard()[endX][endY] != null && board.getBoard()[endX][endY].GetColor() == this.GetColor()) {
                return false; // Недопустимый ход, конечная позиция занята дружественной фигурой
            }

            return true; // Ход диагональю возможен
        }
        return false; // Ход не по диагонали
    }
    @Override
    public String getImage(Color pieceColor) {
        if (pieceColor == Color.WHITE) {
            return"C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece4.png";
        } else {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece10.png";
        }

    }

    public Name getPieceSymbol() {
        return Name.B;
    }
}
