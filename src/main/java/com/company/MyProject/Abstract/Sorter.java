package com.company.MyProject.Abstract;

import com.company.MyProject.Annotation.IsSorted;

import java.util.ArrayList;
import java.util.Arrays;

public class Sorter {


    static class MyARR {
        private int[] rightPart;
        private int[] leftPart;


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

    @IsSorted
    public static int[] bubbleSortFromFirstToLast(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 1; j < arr.length; j++) {
                    if (arr[j] < arr[j - 1]) {
                        replaceElement(arr, j - 1, j);
                    }

                }
            }
            return arr;
        }
    }

    @IsSorted
    public static int[] bubbleSortFromLastToFirst(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = arr.length - 1; j > 0; j--) {
                    if (arr[j] < arr[j - 1]) {
                        replaceElement(arr, j, j - 1);

                    }

                }
            }
            return arr;
        }
    }

    @IsSorted
    public static int[] shakerSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            int i = 1;
            int j = 0;
            int offset = 0;
            int middle = arr.length / 2;


            while (offset != middle) {
                if (i < arr.length) {
                    if (arr[i - offset] < arr[i - offset - 1]) {
                        replaceElement(arr, i - offset, i - 1 - offset);
                    }
                    i++;
                    j++;
                } else {
                    if (arr[j - offset] < arr[j - offset - 1]) {
                        replaceElement(arr, j - offset, j - offset - 1);
                    }
                    j--;
                    i--;
                }
                offset++;
            }
            return arr;

        }
    }

    @IsSorted
    public static int[] divByTwoSort(int[] arr, int begin, int end) {
        if (arr.length < 2) {
            return arr;
        } else {
            int middle = begin + (end - begin) / 2;
            int base = arr[middle];

            int i = begin;
            int j = end - 1;

            while (i <= j) {
                while (arr[i] < base) {
                    i++;
                }

                while (arr[j] > base) {
                    j--;
                }

                if (i <= j) {
                    replaceElement(arr, i, j);
                    i++;
                    j--;
                }
            }
            if (begin < j) {
                divByTwoSort(arr, begin, j);
            }

            if (end > i) {
                divByTwoSort(arr, i, end);
            }


            return arr;

        }


    }

    @IsSorted
    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {

            MyARR object = divArrByTwoPart(arr);

            int[] one = object.getRightPart();
            int[] two = object.getLeftPart();


            mergeSort(one);
            mergeSort(two);


            int i = 0;
            int j = 0;
            int k = 0;
            while (i < one.length && j < two.length) {
                if (one[i] < two[j]) {
                    arr[k] = one[i];
                    i++;
                } else {

                    arr[k] = two[j];
                    j++;

                }
                k++;

            }

            while (i < one.length) {
                arr[k] = one[i];
                k++;
                i++;
            }

            while (j < two.length) {
                arr[k] = two[j];
                k++;
                j++;
            }
            return arr;
        }

    }

    @IsSorted
    public static void SortByArraysSort(int[] arr) {
        Arrays.sort(arr);
    }

    private static MyARR divArrByTwoPart(int[] arr) {
        int middle = arr.length / 2;
        if (arr.length % 2 == 1) {
            middle++;
        }
        int[] one = new int[middle];
        int[] two = new int[arr.length - middle];


        for (int i = 0; i < middle; i++) {
            one[i] = arr[i];
        }

        for (int i = 1; i <= two.length; i++) {
            two[i - 1] = arr[arr.length - i];
        }

        Sorter.MyARR result = new Sorter.MyARR(one, two);

        return result;
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void print(ArrayList arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.printf(arr.get(i) + ", ");
        }
        System.out.println();
    }

    private static void replaceElement(int[] arr, int i, int j) {
        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;

    }


}
