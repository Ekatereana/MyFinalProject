package com.company.MyProject.POJO;

import com.company.MyProject.Abstract.Generator;

import java.util.ArrayList;

public class ARRAYS {
    private ArrayList<int[]> sortedArr = new ArrayList<>();
    private ArrayList<int[]> withRandEndEl = new ArrayList<>();
    private ArrayList<int[]> upsideDown = new ArrayList<>();
    private ArrayList<int[]> rand = new ArrayList<>();

    public ArrayList<int[]> getSortedArr() {
        return sortedArr;
    }

    public ArrayList<int[]> getWithRandEndEl() {
        return withRandEndEl;
    }

    public ArrayList<int[]> getUpsideDown() {
        return upsideDown;
    }

    public ArrayList<int[]> getRand() {
        return rand;
    }

    public ARRAYS(int size) {
        for (int i = 1; i <= size; i++) {
            sortedArr.add(Generator.generateSortedArr(i));
            rand.add(Generator.generateRand(i));
            upsideDown.add(Generator.generateUpDown(i));
            withRandEndEl.add(Generator.generateWithRand(i));


        }
    }

}
