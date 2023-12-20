package org.example;

public class Rook extends ChessPiece {
    public Rook(Color color) {
        super(color);
    }

    private boolean first_run = false;

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // Ход ладьи по вертикали или горизонтали
        boolean isVerticalMove = startX == endX && startY != endY;
        boolean isHorizontalMove = startX != endX && startY == endY;

        if (isVerticalMove || isHorizontalMove) {
            int xDir = 0, yDir = 0;
            if (isVerticalMove) {
                yDir = (endY > startY) ? 1 : -1;
            } else {
                xDir = (endX > startX) ? 1 : -1;
            }

            int x = startX + xDir;
            int y = startY + yDir;

            // Проверка наличия фигуры на пути движения ладьи
            while ((isVerticalMove && y != endY) || (isHorizontalMove && x != endX)) {
                if (board.getBoard()[x][y] != null) {
                    // Если на пути есть фигура
                    return false; // Недопустимый ход, на пути есть фигура
                }

                if (isVerticalMove) {
                    y += yDir;
                } else {
                    x += xDir;
                }
            }

            // Проверка наличия фигуры на конечной позиции
            if (board.getBoard()[endX][endY] != null && board.getBoard()[endX][endY].GetColor() == this.GetColor()) {
                return false; // Недопустимый ход, на конечной позиции есть дружественная фигура
            }
            return true; // Ход вертикально или горизонтально возможен
        }
        return false; // Ход не по вертикали и не по горизонтали
    }

    @Override
    public String getImage(Color pieceColor) {
        if (pieceColor == Color.WHITE) {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece3.png";
        } else {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece9.png";
        }
    }

    public Name getPieceSymbol() {
        return Name.R;
    }
}
