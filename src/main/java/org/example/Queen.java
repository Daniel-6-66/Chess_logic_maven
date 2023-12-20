package org.example;

public class Queen extends ChessPiece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        boolean isVerticalMove = startX == endX && startY != endY;
        boolean isHorizontalMove = startX != endX && startY == endY;

        if ((startX == endX || startY == endY) || (dx == dy)) {
            // Проверка хода по вертикали, горизонтали или по диагонали
            int xDir = (endX > startX) ? 1 : ((endX < startX) ? -1 : 0);
            int yDir = (endY > startY) ? 1 : ((endY < startY) ? -1 : 0);

            int x = startX + xDir;
            int y = startY + yDir;

            // Проверка наличия фигуры на пути движения ферзя
            while ((isVerticalMove && y != endY) || (isHorizontalMove && x != endX) || (dx == dy && x != endX && y != endY)) {
                if (board.getBoard()[x][y] != null) {
                    // Если на пути есть фигура
                    return false; // Недопустимый ход, на пути есть фигура
                }

                if (isVerticalMove) {
                    y += yDir;
                } else if (isHorizontalMove) {
                    x += xDir;
                } else {
                    x += xDir;
                    y += yDir;
                }
            }

            // Проверка наличия фигуры на конечной позиции
            if (board.getBoard()[endX][endY] != null && board.getBoard()[endX][endY].GetColor() == this.GetColor()) {
                return false; // Недопустимый ход, на конечной позиции есть дружественная фигура
            }
            return true; // Ход вертикально, горизонтально или по диагонали возможен
        }
        return false; // Ход не вертикально, горизонтально или по диагонали
    }

    @Override
    public String getImage(Color pieceColor) {

        if (pieceColor == Color.WHITE) {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece5.png";
        } else {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece11.png";
        }
    }

    public Name getPieceSymbol() {
        return Name.Q;
    }
}


