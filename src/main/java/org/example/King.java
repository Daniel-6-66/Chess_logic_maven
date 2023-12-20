package org.example;


public class King extends ChessPiece {

    private boolean first_run = false;
    public King(Color color) {
        super(color);
    }


    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);

        // Король может ходить на одну клетку в любом направлении
        if (dx <= 1 && dy <= 1) {
            // Проверяем наличие дружественных пешек вокруг короля
            if (board.getBoard()[endX][endY] == null){
                return true;
            }
            if (board.getBoard()[endX][endY].GetColor() == GetColor()){
                return false;
            }
            return true; // Ход короля допустим
        }
        return false; // Ход короля за пределами одной клетки
    }



    @Override
    public String getImage(Color pieceColor) {
        if (pieceColor == Color.WHITE) {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece6.png";
        } else {
            return "C:\\Users\\BoSS JR\\OneDrive\\Рабочий стол\\Учёба\\Chess_logic_maven\\images\\piece12.png";
        }
    }


    public Name getPieceSymbol(){
        return Name.K;
    }
}