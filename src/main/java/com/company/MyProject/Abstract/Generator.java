package com.company.MyProject.Abstract;


public abstract class Generator {


    public static int[] generateSortedArr(int size) {
        int[] result = new int[size];
        int start = (int)(Math.random()*10);
        result[0] = start;
        for (int i = 1; i < size; i++) {
            int next = (int)(Math.random()*100);
            while(next < start){
              next+=(int)(Math.random()*30);
            }
            result[i] = next;
            start = next;

        }
        return result;

    }

    public static int[] generateWithRand(int size) {
        int[] result = new int[size];
        int start = (int)(Math.random()*10);
        result[0] = start;
        for (int i = 1; i < size; i++) {
            int next = (int)(Math.random()*100);
            while(next < start){
                next+=(int)(Math.random()*30);
            }
            result[i] = next;
            start = next;
        }

        result[size - 1] = (int)(Math.random()*100);
        return result;
    }

    public static int [] generateUpDown( int size){
        int [] result = new int[size];
        int end = (int)(Math.random()*10);
        result[size - 1] = end;
        for (int i = size - 1; i >=0; i--) {
            int previous = (int)(Math.random()*100);
            while(previous < end){
                previous+=(int)(Math.random()*30);
            }
            result[i] = previous;
            end = previous;

        }
        return result;
    }

    public static int [] generateRand( int size){
        int [] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int)(Math.random()*100);
        }
        return result;
    }

}
