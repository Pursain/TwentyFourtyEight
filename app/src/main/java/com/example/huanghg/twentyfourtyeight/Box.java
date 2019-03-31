package com.example.huanghg.twentyfourtyeight;

public class Box {
    private int value;
    private int lastSwapValue;

    public Box(){
        this.value = 0;
        this.lastSwapValue = -1;
    }

    public Box(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getLastSwapValue() {
        return lastSwapValue;
    }

    public void setLastSwapValue(int lastSwapValue) {
        this.lastSwapValue = lastSwapValue;
    }

    public void doubleValue(){
        value*=2;
    }

    public void clearValue(){
        value = 0;
    }

    public void setInsertValue(){
        value = 2;
    }
}
