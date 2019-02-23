package com.company.MyProject.Controller;

import com.company.MyProject.Abstract.Drawer;
import com.company.MyProject.Abstract.Sorter;
import com.company.MyProject.Annotation.IsSorted;
import com.company.MyProject.POJO.ArraysType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public enum Controller {
    INSTANSE;

    private static Sorter sorter = new Sorter();
    private static ArrayList<String> nameOfMethods = new ArrayList<>();
    private static ArrayList<String> nameOfFields = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> sizes = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<Double>>> timeForAll = new ArrayList<>();

    public static void sortAllArraysAndDrawe() {
        fillMethodsArr();
        int size = (int) (Math.random() * 11);
        ArraysType arrays = new ArraysType(size);// create basic arrays;
        for (Field field : arrays.getClass().getDeclaredFields()) {// start of cycle by fields
            nameOfFields.add(field.getName()); // add name of field to fields array
            System.out.println("Time for " + field.getName() + ": ");
            field.setAccessible(true);
            ArrayList<Integer> sizesForCurrentMethod = new ArrayList<>();
            ArrayList<ArrayList<Double>> listOfTimeForCurSize = new ArrayList<>();
            for (int i = 0; i < nameOfMethods.size(); i++) {// start of cycle by sizes
                System.out.println("Size of current arr: " + arrays.getArr(field.getName()).length);

                try {
                    int[] current_arr = (int[]) field.get(arrays);
                    sizesForCurrentMethod.add(current_arr.length);
                    ArrayList myTime = sortArrByAllMethods(current_arr);
                    listOfTimeForCurSize.add(myTime);// add list of time for all methods for current size;
                    Sorter.print(myTime);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                size = arrays.makeArrBigger(size, field.getName());
            }
            timeForAll.add(listOfTimeForCurSize);
            sizes.add(sizesForCurrentMethod);
            System.out.println();

        }
        Drawer.draweGraphic(nameOfMethods,nameOfFields,timeForAll, sizes);


    }

    public static ArrayList sortArrByAllMethods(int[] arr) {
        ArrayList time_arr = new ArrayList();

        for (Method method : sorter.getClass().getDeclaredMethods()) {
            int[] temporary = arr.clone();
            if (method.isAnnotationPresent(IsSorted.class)) {// если присудствует аннотация
                long start_time = System.nanoTime();// померяли время перед началом работы
                if (method.getParameterCount() == 3) {// нужно ли три параметра
                    try {
                        method.invoke(method, temporary, 0, temporary.length);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        method.invoke(method, temporary);// исполняем нужный нам метод


                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                long currenttime = ((System.nanoTime() - start_time)); // считаем конечное время
                double result = (double) currenttime / 1000000;
                time_arr.add(result);// добавляем полученое время в масив конкретного метода и конкретного массива

            }

        }
        return time_arr;
    }

    private static void fillMethodsArr() {
        for (Method method : sorter.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(IsSorted.class)) {
                nameOfMethods.add(method.getName());
            }
        }
    }

}
