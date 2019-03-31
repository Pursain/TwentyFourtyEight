package com.example.huanghg.twentyfourtyeight;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class GameState {
    private MainActivity callback;
    private ArrayList<ArrayList<Box>> boxMatrix;
    private boolean gameover;
    private Random rand;
    private int moveCount;
    private Box lastInsertedBox;
    private ArrayList<Integer> insertionOrderList;
    private int gameSize;



    enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    interface GameStateFunctions {
        void toast(String msg);

        void movedComplete();
    }

    private <T> void shuffleList(List<T> list) {
        for(int i = 1; i < list.size(); ++i){
            int j = rand.nextInt(i);
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    public GameState(MainActivity callback, int gameSize) {
        Log.d(TAG, "GameState: starts");
        this.callback = callback;
        this.gameSize = gameSize;

        //make the 4x4, fill with -1 boxes
        boxMatrix = new ArrayList<ArrayList<Box>>();
        for (int i = 0; i < gameSize; ++i) {
            boxMatrix.add(new ArrayList<Box>());
            for (int j = 0; j < gameSize; ++j) {
                boxMatrix.get(i).add(new Box());
            }
        }

        //make the random insertion order list
        insertionOrderList = new ArrayList<Integer>();
        for(int i = 0; i < gameSize*gameSize; ++i) insertionOrderList.add(i);

        gameover = true;
        rand = new Random();
        Log.d(TAG, "GameState: ends");
    }

    public void startGame() {
        callback.toast("Game has started");
        gameover = false;
        moveCount = 0;
        insertRandom();
    }

    public void restartGame() {

    }

    public void moveCommand(Direction dir) {
        if (gameover) {
            callback.toast("That game is over");
            return;
        }
        moveCount++;
        boolean moved = false;
        switch (dir) {
            case UP:
                moved = condenseUp();
                break;
            case DOWN:
                moved = condenseDown();
                break;
            case LEFT:
                moved = condenseLeft();
                break;
            case RIGHT:
                moved = condenseRight();
                break;
        }
        if (moved) {
            insertRandom();
        }
        if (!containsPossibleMove()) {
            gameover = true;
            callback.toast("That game is over");
            return;
        }
        callback.movedComplete();
    }

    public String boardAsString() {
        String out = "";
        for (int i = 0; i < gameSize; ++i) {
            for (int j = 0; j < gameSize; ++j) {
                out += Integer.toString(boxMatrix.get(i).get(j).getValue()) + "|";
            }
            out += "\n";
        }
        return out;
    }

    public ArrayList<ArrayList<Box>> boardAsMatrix() {
        return boxMatrix;
    }

    private boolean containsZero() {
        for (ArrayList<Box> boxArray : boxMatrix) {
            for (Box box : boxArray) {
                if (box.getValue() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsPossibleMove() {
        //check for zeros
        if (containsZero()) {
            return true;
        }

        //check right neighbor
        for (int i = 0; i < gameSize; ++i) {
            for (int j = 0; j < gameSize-1; ++j) {
                if (boxMatrix.get(i).get(j).getValue() == boxMatrix.get(i).get(j + 1).getValue()) {
                    return true;
                }
            }
        }

        //check below neighbor
        for (int i = 0; i < gameSize-1; ++i) {
            for (int j = 0; j < gameSize; ++j) {
                if (boxMatrix.get(i).get(j).getValue() == boxMatrix.get(i + 1).get(j).getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void insertRandom() {
        int rand_x = 0;
        int rand_y = 0;
        int counter = 0;

        shuffleList(insertionOrderList);
        for(int number : insertionOrderList){
            rand_x = number/gameSize;
            rand_y = number%gameSize;
            ++counter;
            if (boxMatrix.get(rand_x).get(rand_y).getValue() == 0) {
                break;
            }
        }
        boxMatrix.get(rand_x).get(rand_y).setInsertValue();
        lastInsertedBox = boxMatrix.get(rand_x).get(rand_y);
        Log.d(TAG, "insertRandom: times tried: " + counter);
    }

    public Box getLastInsertedBox() {
        return lastInsertedBox;
    }

    private boolean condenseUp() {
        boolean moved_flag = false;
        for (int row = 0; row < gameSize; ++row) {
            for (int col = 0; col < gameSize; ++col) {
                if (boxMatrix.get(row).get(col).getValue() != 0) {
                    for (int i = row; i != 0; --i) {
                        Box current = boxMatrix.get(i).get(col);
                        Box above = boxMatrix.get(i - 1).get(col);
                        if (above.getValue() != 0) {
                            //condition to merge
                            if (above.getLastSwapValue() != moveCount && current.getLastSwapValue() != moveCount
                                    && above.getValue() == current.getValue()) {
                                moved_flag = true;
                                current.clearValue();
                                above.doubleValue();
                                above.setLastSwapValue(moveCount);
                                continue;
                            } else {
                                break;
                            }
                        } else {
                            moved_flag = true;
                            //swap
                            boxMatrix.get(i).set(col, above);
                            boxMatrix.get(i - 1).set(col, current);
                        }
                    }
                }
            }
        }
        return moved_flag;
    }

    private boolean condenseDown() {
        boolean moved_flag = false;
        for (int row = gameSize-1; row >= 0; --row) {
            for (int col = 0; col < gameSize; ++col) {
                if (boxMatrix.get(row).get(col).getValue() != 0) {
                    for (int i = row; i != gameSize-1; ++i) {
                        Box current = boxMatrix.get(i).get(col);
                        Box below = boxMatrix.get(i + 1).get(col);
                        if (below.getValue() != 0) {
                            //condition to merge
                            if (below.getLastSwapValue() != moveCount && current.getLastSwapValue() != moveCount
                                    && below.getValue() == current.getValue()) {
                                moved_flag = true;
                                current.clearValue();
                                below.doubleValue();
                                below.setLastSwapValue(moveCount);
                                continue;
                            } else {
                                break;
                            }
                        } else {
                            moved_flag = true;
                            boxMatrix.get(i).set(col, below);
                            boxMatrix.get(i + 1).set(col, current);
                        }
                    }
                }
            }
        }
        return moved_flag;
    }

    private boolean condenseLeft() {
        boolean moved_flag = false;
        for (int row = 0; row < gameSize; ++row) {
            for (int col = 0; col < gameSize; ++col) {
                if (boxMatrix.get(row).get(col).getValue() != 0) {
                    for (int i = col; i != 0; --i) {
                        Box current = boxMatrix.get(row).get(i);
                        Box previous = boxMatrix.get(row).get(i - 1);
                        if (previous.getValue() != 0) {
                            //condition to merge
                            if (previous.getLastSwapValue() != moveCount && current.getLastSwapValue() != moveCount
                                    && previous.getValue() == current.getValue()) {
                                moved_flag = true;
                                current.clearValue();
                                previous.doubleValue();
                                previous.setLastSwapValue(moveCount);
                                continue;
                            } else {
                                break;
                            }
                        } else {
                            moved_flag = true;
                            boxMatrix.get(row).set(i, previous);
                            boxMatrix.get(row).set(i - 1, current);
                        }
                    }
                }
            }
        }
        return moved_flag;
    }

    private boolean condenseRight() {
        boolean moved_flag = false;
        for (int row = 0; row < gameSize; ++row) {
            for (int col = gameSize-1; col >= 0; --col) {
                if (boxMatrix.get(row).get(col).getValue() != 0) {
                    for (int i = col; i != gameSize-1; ++i) {
                        Box current = boxMatrix.get(row).get(i);
                        Box next = boxMatrix.get(row).get(i + 1);
                        if (next.getValue() != 0) {
                            //condition to merge
                            if (next.getLastSwapValue() != moveCount && current.getLastSwapValue() != moveCount
                                    && next.getValue() == current.getValue()) {
                                moved_flag = true;
                                current.clearValue();
                                next.doubleValue();
                                next.setLastSwapValue(moveCount);
                                continue;
                            } else {
                                break;
                            }
                        } else {
                            moved_flag = true;
                            boxMatrix.get(row).set(i, next);
                            boxMatrix.get(row).set(i + 1, current);
                        }
                    }
                }
            }
        }
        return moved_flag;
    }

}