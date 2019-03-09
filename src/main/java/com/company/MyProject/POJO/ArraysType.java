package com.company.MyProject.POJO;

import com.company.MyProject.Abstract.Generator;

public class ArraysType {
    private int[] sortedArr;
    private int[] withRandEndEl;
    private int[] upsideDown;
    private int[] rand;

    public int[] getArr(String name) {
        if (name.equalsIgnoreCase("sortedArr")) {
            return sortedArr;
        } else {
            if (name.equalsIgnoreCase("withRandEndEl")) {
                return withRandEndEl;
            } else {
                if (name.equalsIgnoreCase("upsideDown")) {
                    return upsideDown;
                } else {
                    return rand;

                }
            }
        }
    }

    public int makeArrBigger(int size, String name) {
        int new_size = size + 1;
        if (name.equalsIgnoreCase("sortedArr")) {
            this.sortedArr = Generator.generateSortedArr(new_size);
        }
        else {
            if (name.equalsIgnoreCase("withRandEndEl")) {
                this.withRandEndEl = Generator.generateWithRand(new_size);
            } else {
                if (name.equalsIgnoreCase("upsideDown")) {
                    this.upsideDown = Generator.generateUpDown(new_size);
                } else {
                    this.rand = Generator.generateRand(new_size);

                }
            }
        }
        return new_size;
    }


    public ArraysType(int size) {
        sortedArr = Generator.generateSortedArr(size);
        rand = Generator.generateRand(size);
        upsideDown = Generator.generateUpDown(size);
        withRandEndEl = Generator.generateWithRand(size);

    }

}
