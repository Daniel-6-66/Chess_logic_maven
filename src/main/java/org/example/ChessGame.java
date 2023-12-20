package org.example;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();


        Color color = Color.WHITE;

        boolean first_w = false;
        boolean first_b = false;

        while (true) {
            board.printBoard(); // Вывод текущего состояния доски
            boolean validMove = false;

            while (!validMove) {
                System.out.println("Ход " + (color == Color.WHITE ? "белых" : "черных"));
                //System.out.println(color == Color.WHITE);
                board.makeMoveFromConsole(!(color == Color.WHITE));
                if(color == Color.WHITE && first_b){
                    color = Color.BLACK;
                }
                if(!(color == Color.WHITE) && first_w){
                    color = Color.WHITE;
                }
                // Проверяем наличие шаха после хода
                boolean isCheck = board.isCheck(color );
                if (isCheck) {
                    if (color == Color.WHITE && first_w) {
                        System.out.println("Мат! Победили черные!");
                        return;
                    }
                    if (!(color == Color.WHITE) && first_b) {
                        System.out.println("Мат! Победили белые!");
                        return;
                    }
                    if (color == Color.WHITE) {
                        first_w = true;
                    } else {
                        first_b = true;
                    }
                    board.checkHandler(color); // Обработка ситуации шаха
                    if (color == Color.WHITE){
                        color = Color.BLACK;
                    } else {
                        color = Color.WHITE;
                    }
                } else {
                    if (color == Color.WHITE) {
                        first_w = false;
                    } else {
                        first_b = false;
                    }
                }

                validMove = !isCheck; // Ход допустим, если нет шаха
            }

            // Переключаем очередь хода
            if (color == Color.WHITE){
                color = Color.BLACK;
            } else {
                color = Color.WHITE;
            }
        }
    }
}

