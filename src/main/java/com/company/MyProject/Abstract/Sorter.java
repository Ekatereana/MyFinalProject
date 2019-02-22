package com.company.MyProject.Abstract;

import com.company.MyProject.Annotation.IsNOTSorter;
import com.company.MyProject.Annotation.IsSPECIAL;
import com.company.MyProject.POJO.MyARR;

import java.util.ArrayList;
import java.util.Arrays;

public class  Sorter {


    public static int[] bubbleSortFromFirstToLast(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 1; j < arr.length; j++) {
                    if (arr[j] < arr[j - 1]) {
                        int a = arr[j];
                        int b = arr[j - 1];
                        arr[j] = b;
                        arr[j - 1] = a;
                    }

                }
            }
            return arr;
        }
    }


    public static int[] bubbleSortFromLastToFirst(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = arr.length - 1; j > 0; j--) {
                    if (arr[j] < arr[j - 1]) {
                        int a = arr[j];
                        int b = arr[j - 1];
                        arr[j] = b;
                        arr[j - 1] = a;
                    }

                }
            }
            return arr;
        }
    }

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
                        int a = arr[i - offset];
                        int b = arr[i - offset - 1];
                        arr[i - offset] = b;
                        arr[i - offset - 1] = a;
                    }
                    i++;
                    j++;
                } else {
                    if (arr[j - offset] < arr[j - offset - 1]) {
                        int a = arr[j - offset];
                        int b = arr[j - offset - 1];
                        arr[j - offset] = b;
                        arr[j - offset - 1] = a;
                    }
                    j--;
                    i--;
                }
                offset++;
            }
            return arr;

        }
    }

    @IsSPECIAL
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
                    int temporary = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temporary;
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

    @IsNOTSorter
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

        MyARR result = new MyARR(one, two);

        return result;
    }

    public static void SortByArraysSort(int []arr){
        Arrays.sort(arr);
    }

    @IsNOTSorter
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    @IsNOTSorter
    public static void print(ArrayList arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.printf( arr.get(i) + ", ");
        }
        System.out.println();
    }

}
