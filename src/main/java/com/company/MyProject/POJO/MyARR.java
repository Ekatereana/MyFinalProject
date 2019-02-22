package com.company.MyProject.POJO;

public class MyARR {
    private int [] rightPart;
    private int [] leftPart;


    public MyARR(int[] rightPart, int[] leftPart) {
        this.rightPart = rightPart;
        this.leftPart = leftPart;
    }

    public int[] getRightPart() {
        return rightPart;
    }

    public int[] getLeftPart() {
        return leftPart;
    }
}
