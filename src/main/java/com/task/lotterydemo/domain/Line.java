package com.task.lotterydemo.domain;

public class Line {

    private int number_1;
    private int number_2;
    private int number_3;
    private int result;

    public Line() {
    }

    public Line(int number_1, int number_2, int number_3) {
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.number_3 = number_3;
    }

    public int getNumber_1() {
        return number_1;
    }

    public void setNumber_1(int number_1) {
        this.number_1 = number_1;
    }

    public int getNumber_2() {
        return number_2;
    }

    public void setNumber_2(int number_2) {
        this.number_2 = number_2;
    }

    public int getNumber_3() {
        return number_3;
    }

    public void setNumber_3(int number_3) {
        this.number_3 = number_3;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
