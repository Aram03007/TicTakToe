package com.example.narek.tictaktoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    private int[][] preferredMoves = {
            {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
            {0, 1}, {1, 0}, {1, 2}, {2, 1}};
    private boolean isFirstPersonTurn = true;
    ChekWinner chekWinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CrossView crossView = (CrossView) findViewById(R.id.crossView);
        assert crossView != null;
        Button button2 = (Button) findViewById(R.id.button2);
        assert button2 != null;
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetBoard();
                crossView.updateBoard(board);

            }
        });
        final RadioButton twoplayers = (RadioButton) findViewById(R.id.radioButton2);
//        crossView.updateBoard(board);
        crossView.setOnSellTapListener(new CrossView.OnSellTapListener() {
            @Override
            public void onSellTapped(int row, int col) {
                assert twoplayers != null;
                if (twoplayers.isChecked()) {
                    if (isFirstPersonTurn) {
                        board[row][col] = 1;


                    } else {
                        board[row][col] = -1;

                    }

                    isFirstPersonTurn = !isFirstPersonTurn;
                    crossView.updateBoard(board);

                    chekWinner = gameOver(board);

                    if (chekWinner == ChekWinner.X_WIN) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Game Over")
                                .setMessage("X Player Win")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                })
                                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetBoard();
                                        crossView.updateBoard(board);


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    } else if (chekWinner == ChekWinner.O_WIN) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Game Over")
                                .setMessage("O Player Win")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                })
                                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetBoard();
                                        crossView.updateBoard(board);


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    } else if (chekWinner == ChekWinner.DRAW) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Game Over")
                                .setMessage("Draw")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                })
                                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetBoard();
                                        crossView.updateBoard(board);


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                } else {
                    board[row][col] = 1;
                    crossView.updateBoard(board);
                    chekWinner = gameOver(board);


                    int[] tmp = move();
                    if (tmp != null) {
                        board[tmp[0]][tmp[1]] = -1;
                        crossView.updateBoard(board);

                    }

                    if (chekWinner == ChekWinner.X_WIN) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Game Over")
                                .setMessage("X Player Win")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                })
                                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetBoard();
                                        crossView.updateBoard(board);


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    } else if (chekWinner == ChekWinner.O_WIN) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Game Over")
                                .setMessage("O Player Win")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                })
                                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetBoard();
                                        crossView.updateBoard(board);


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    } else if (chekWinner == ChekWinner.DRAW) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Game Over")
                                .setMessage("Draw")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                })
                                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetBoard();
                                        crossView.updateBoard(board);


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

                }


            }
        });
    }

    private ChekWinner gameOver(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            ChekWinner chekWinner = chekColWinner(board, i);
            if (chekWinner != null) {
                return chekWinner;

            }
        }
        for (int i = 0; i < board.length; i++) {
            ChekWinner chekWinner = chekRowWinner(board, i);
            if (chekWinner != null) {
                return chekWinner;

            }
        }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) {
            return ChekWinner.X_WIN;

        }
        if (board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] == 1) {
            return ChekWinner.X_WIN;

        }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == -1) {
            return ChekWinner.O_WIN;

        }
        if (board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] == -1) {
            return ChekWinner.O_WIN;

        }

        if (isFull()) {
            return ChekWinner.DRAW;
        }

        return null;


    }

    private ChekWinner chekRowWinner(int[][] board, int rowIndex) {
        if (board[0][rowIndex] == board[1][rowIndex] && board[0][rowIndex] == board[2][rowIndex] && board[0][rowIndex] == 1) {
            return ChekWinner.X_WIN;
        } else if (board[0][rowIndex] == board[1][rowIndex] && board[0][rowIndex] == board[2][rowIndex] && board[0][rowIndex] == -1) {
            return ChekWinner.O_WIN;
        }
        return null;
    }

    private ChekWinner chekColWinner(int[][] board, int colIndex) {
        if (board[colIndex][0] == board[colIndex][1] && board[colIndex][0] == board[colIndex][2] && board[colIndex][0] == 1) {
            return ChekWinner.X_WIN;
        } else if (board[colIndex][0] == board[colIndex][1] && board[colIndex][0] == board[colIndex][2] && board[colIndex][0] == -1) {
            return ChekWinner.O_WIN;
        }
        return null;
    }


    private boolean isFull() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }

            }
        }
        return true;

    }


    private void resetBoard() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int[] move() {
        for (int[] move : preferredMoves) {
            if (content(move[0],move[1]) == Seed.EMPTY) {
                return move;
            }
        }
        return null;
    }

    private Seed content(int rowndex, int colIndex ) {
        if (board[rowndex][colIndex] == 1) {
            return Seed.CROSS;
        }else if (board[rowndex][colIndex] == -1) {
            return Seed.NOUGHT;
        }

        return Seed.EMPTY;
    }

    enum Seed {
        CROSS, EMPTY, NOUGHT
    }
}
