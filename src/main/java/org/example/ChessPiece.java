package org.example;
public abstract class ChessPiece {
    private Color color;

    public ChessPiece(Color color) {
        this.color = color;
    }

    public Color GetColor() {
        return color;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY , ChessBoard board);

    public abstract Name getPieceSymbol();

    public abstract String getImage(Color color);
}