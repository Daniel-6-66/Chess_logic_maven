package org.example;

public class Pawn extends ChessPiece {

    private boolean first_run = true;

    public boolean isFirst_run() {
        return first_run;
    }

    public void setFirst_run(boolean first_run) {
        this.first_run = first_run;
    }

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY , ChessBoard board) {
        int direction = GetColor() == Color.WHITE ? -1 : 1;
        int dx = endX - startX;
        int dy = endY - startY;

        // Пешка может ходить вперед на одну клетку или на две клетки из начальной позиции
        // Может бить по диагонали только на одну клетку вперед

        if ((dx == direction && dy == 0) ){
            if (isOpponentPiece(endX, endY, board) != null){
                return false;
            } else {
                return true;
            }
        } else {
            if (dx == 2 * direction && dy == 0 && ((startX == 1 && !(GetColor() == Color.WHITE)) || (startX == 6 && (GetColor() == Color.WHITE)))) {
                first_run = false;
                return true;
            } else {
                if (dx == direction && Math.abs(dy) == 1) {
                    // Проверяем, может ли пешка совершить ход по диагонали на одну клетку (бить фигуру)
                    // Метод возвращает true, если конечная позиция занята фигурой противоположного цвета
                    return isOpponentPiece(endX,endY,board) != null ;//isOpponentPiece(endX, endY, board);
                }
            }
        }

        return false;
    }

    private Class isOpponentPiece(int x, int y , ChessBoard board) {
        ChessPiece piece = board.getBoard()[x][y];
        // Проверяем, не является ли клетка, в которую хочет сделать ход пешка, занятой фигурой противника
        if (piece != null && piece.GetColor() != this.GetColor()){
            return piece.getClass();
        } else {
            return null;
        }
    }

    public String getImage(Color pieceColor) {
        if (pieceColor == Color.WHITE) {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece1.png";
        } else {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece7.png";
        }
    }


    public Name getPieceSymbol(){
        return Name.P;
    }
}

