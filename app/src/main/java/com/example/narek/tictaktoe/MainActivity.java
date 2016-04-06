package com.example.narek.tictaktoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GameLogic logic;
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
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        logic = new GameLogic();


        assert radioGroup != null;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.eazy) {
                    logic.setLevel(Level.EAZY);
                }else if (id == R.id.normal) {
                    logic.setLevel(Level.NORMAL);


                }else if (id == R.id.hard) {
                    logic.setLevel(Level.HARD);


                }
            }
        });





        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetBoard();
                crossView.updateBoard(logic.getBoard());

            }
        });
        final Switch twoplayers = (Switch) findViewById(R.id.switch1);
        crossView.setOnSellTapListener(new CrossView.OnSellTapListener() {
            @Override
            public void onSellTapped(int row, int col) {
                assert twoplayers != null;
                if (twoplayers.isChecked()) {
                    if (isFirstPersonTurn && logic.getBoard()[row][col] != 1 && logic.getBoard()[row][col] != -1) {

                        logic.getBoard()[row][col] = 1;
                        isFirstPersonTurn = !isFirstPersonTurn;




                    } else if(logic.getBoard()[row][col] != 1 && logic.getBoard()[row][col] != -1){

                        logic.getBoard()[row][col] = -1;
                        isFirstPersonTurn = !isFirstPersonTurn;



                    }


                    crossView.updateBoard(logic.getBoard());

                    chekWinner = gameOver(logic.getBoard());

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
                                        crossView.updateBoard(logic.getBoard());


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
                                        crossView.updateBoard(logic.getBoard());


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
                                        crossView.updateBoard(logic.getBoard());


                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                } else {

                    if (logic.getBoard()[row][col] != 1 && logic.getBoard()[row][col] != -1) {

                        logic.getBoard()[row][col] = 1;
                        crossView.updateBoard(logic.getBoard());
                        chekWinner = gameOver(logic.getBoard());

                        if (chekWinner == null) {

                            int[] tmp = logic.move();
                            logic.getBoard()[tmp[0]][tmp[1]] = -1;
                            chekWinner = gameOver(logic.getBoard());

                        }
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
                                        crossView.updateBoard(logic.getBoard());


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
                                        crossView.updateBoard(logic.getBoard());


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
                                        crossView.updateBoard(logic.getBoard());


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
        for (int i = 0; i < logic.getBoard()[0].length; i++) {
            for (int j = 0; j < logic.getBoard().length; j++) {
                if (logic.getBoard()[i][j] == 0) {
                    return false;
                }

            }
        }
        return true;

    }


    private void resetBoard() {
        for (int i = 0; i < logic.getBoard()[0].length; i++) {
            for (int j = 0; j < logic.getBoard().length; j++) {
                logic.getBoard()[i][j] = 0;
            }
        }
    }



}
