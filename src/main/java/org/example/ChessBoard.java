package org.example;

import java.util.Scanner;

public class ChessBoard {
    private final int rows = 8;
    private final int columns = 8;
    private ChessPiece[][] board;

    public ChessPiece[][] getBoard() {
        return board;
    }

    public ChessBoard() {
        board = new ChessPiece[rows][columns];
        initializeBoard();
    }
    public ChessPiece getPiece(int x , int y){
        return board[x][y];
    }


    private void initializeBoard() {

        board[0][0] = new Rook(Color.BLACK);
        board[0][1] = new Knight(Color.BLACK);
        board[0][2] = new Bishop(Color.BLACK);
        board[0][3] = new Queen(Color.BLACK);
        board[0][4] = new King(Color.BLACK);
        board[0][5] = new Bishop(Color.BLACK);
        board[0][6] = new Knight(Color.BLACK);
        board[0][7] = new Rook(Color.BLACK);


        for (int i = 0; i < columns; i++) {
            board[1][i] = new Pawn(Color.BLACK);
        }


        for (int i = 0; i < columns; i++) {
            board[6][i] = new Pawn(Color.WHITE);
        }


        board[7][0] = new Rook(Color.WHITE);
        board[7][1] = new Knight(Color.WHITE);
        board[7][2] = new Bishop(Color.WHITE);
        board[7][3] = new Queen(Color.WHITE);
        board[7][4] = new King(Color.WHITE);
        board[7][5] = new Bishop(Color.WHITE);
        board[7][6] = new Knight(Color.WHITE);
        board[7][7] = new Rook(Color.WHITE);
    }


    public void printBoard() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == null) {
                    System.out.print("- "); // Если клетка пустая
                } else {
                    // Предполагается, что у фигур есть метод для вывода своего обозначения (например, R для ладьи, K для короля и т.д.)
                    System.out.print(board[i][j].getPieceSymbol() + " ");
                }
            }
            System.out.println(i + 1); // Номер строки
        }
        // Буквенные обозначения столбцов (от A до H)
        System.out.println("A B C D E F G H");
    }

    public void makeMoveFromConsole(boolean isWhiteTurn) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальные координаты (например, A2): ");
        String startCoords = scanner.nextLine();
        int startX = Character.getNumericValue(startCoords.charAt(1))-1;
        int startY = convertLetterToIndex(startCoords.charAt(0));
        //System.out.println("start " + startX + " " + startY);

        System.out.println("Введите конечные координаты (например, B4): ");
        String endCoords = scanner.nextLine();
        int endX = Character.getNumericValue(endCoords.charAt(1))-1;
        int endY = convertLetterToIndex(endCoords.charAt(0));
        //System.out.println("end " + endX + " " + endY);
        //System.out.println(board[startX][startY].GetColor() == Color.WHITE + " " + board[startX][startY].getPieceSymbol());
        if (board[startX][startY] == null || board[startX][startY].GetColor() == Color.WHITE != isWhiteTurn || (startY == endY && startX == endX)) {
            System.out.println("Вы выбрали пустую клетку или фигуру противоположного цвета. Попробуйте снова.");
            return;
        }

        if (movePiece(startX, startY, endX, endY)) {
            System.out.println("Ход выполнен: " + startCoords + " -> " + endCoords);
        } else {
            System.out.println("Недопустимый ход! Попробуйте снова.");
        }
    }


    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (board[startX][startY] != null && board[startX][startY].isValidMove(startX, startY, endX, endY, this)) {
            //System.out.println("*");
            board[endX][endY] = board[startX][startY];
            board[startX][startY] = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean isCheck(Color color) {
        int kingX = -1, kingY = -1;

        // Находим координаты короля
        outerloop:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] instanceof King && board[i][j].GetColor() == color) {
                    kingX = i;
                    kingY = j;
                    break outerloop;
                }
            }
        }
        if (kingX == -1 && kingY == -1){
            return false;
        }

        // Проверяем, может ли фигура атаковать клетку короля
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != null && board[i][j].GetColor() != color &&
                        board[i][j].isValidMove(i, j, kingX, kingY , this)) {
                    System.out.println(board[i][j].getPieceSymbol()+" "+board[i][j].isValidMove(i, j, kingX, kingY , this));
                    return true;
                }
            }
        }

        return false;
    }

    public void checkHandler(Color color) {
        if (isCheck(color)) {
            String colorf = !(color == Color.WHITE) ? "белого" : "черного";
            System.out.println("Король " + colorf + " цвета находится под шахом!");
        } else {
            String colorf = color == Color.WHITE ? "белого" : "черного";
            System.out.println("Король " + colorf + " цвета в безопасности.");
        }
    }

    public boolean canPieceDefendFromCheck(ChessPiece piece, int fromX, int fromY, Color playerColor) {
        int kingX = -1, kingY = -1;
// Находим координаты короля
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] instanceof King && board[i][j].GetColor() == playerColor) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }

        // Проверяем, может ли выбранная фигура перемещением защитить короля
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (piece.isValidMove(fromX, fromY, x, y, this)) {
                    ChessPiece savedPiece = board[x][y];
                    board[x][y] = piece;
                    board[fromX][fromY] = null;

                    boolean stillInCheck = isCheck(playerColor);

        // Возвращаем фигуры на исходные позиции
                    board[fromX][fromY] = piece;
                    board[x][y] = savedPiece;

                    if (!stillInCheck) {
                        return true; // Фигура может устранить шах
                    }
                }
            }
        }
        return false; // Ни один ход не устраняет шах
    }

    public boolean isCheckmate(Color kingColor) {
        ChessPiece[][] currentBoard = getBoard();
        boolean kingFound = false;

        for (ChessPiece[] row : currentBoard) {
            for (ChessPiece piece : row) {
                if (piece instanceof King && piece.GetColor() == kingColor) {
                    kingFound = true;
                    break;
                }
            }
            if (kingFound) {
                break;
            }
        }

        return !kingFound;
    }









    // Метод для преобразования буквенной координаты в индекс массива
    private int convertLetterToIndex(char letter) {
        return letter - 'A';
    }
}
