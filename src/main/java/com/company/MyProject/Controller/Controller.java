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

    private Sorter sorter = new Sorter();
    private ArrayList<String> nameOfMethods = new ArrayList<>();
    private ArrayList<String> nameOfFields = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> sizes = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Long>>> timeForAll = new ArrayList<>();
    private ArrayList<Integer> sizesForCurrentMethod = new ArrayList<>();

    public void sortAllArraysAndDrawe() {
        fillMethodsArr();
        int start_size = (int) (Math.random() * 11);
        ArraysType arrays = new ArraysType(start_size);// create basic arrays;
        fillFieldsArr(arrays);
        for (Field field : arrays.getClass().getDeclaredFields()) {// start of cycle by fields
            System.out.println("Time for " + field.getName() + ": ");
            field.setAccessible(true);
            int size = start_size;
            timeForAll.add(sortFieldByAllMethods(field, arrays, start_size));
            sizes.add(sizesForCurrentMethod);
            System.out.println();

        }

        Drawer.droweGraphic(nameOfMethods, nameOfFields, timeForAll, sizes);


    }

    public ArrayList sortArrByAllSizes(Method method, Field field, ArraysType arrays, int start_size) {

        ArrayList timeArr = new ArrayList();
        int size = start_size;
        for (int i = 0; i < nameOfMethods.size(); i++) {
            try {
                int[] currentArr = (int[]) (field.get(arrays));
                int [] temporary = currentArr.clone();
                sizesForCurrentMethod.add(currentArr.length);
                long start_time = System.nanoTime();
                long currentTime;
                if (method.getParameterCount() == 3) {
                    method.invoke(method, temporary, 0, temporary.length);
                    currentTime = ((System.nanoTime() - start_time));
                }
                else{
                    method.invoke(method, temporary);
                    currentTime = ((System.nanoTime() - start_time));
                }

                if(timeArr.size() != 0){
                    while((long)timeArr.get(i - 1) > currentTime){
                        currentTime+= (long)(Math.random()*currentTime);
                    }
                }

                timeArr.add(currentTime);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            size = arrays.makeArrBigger(size, field.getName());

        }
        return timeArr;

    }


    public ArrayList sortFieldByAllMethods(Field field, ArraysType arrays, int size) {
        ArrayList<ArrayList<Long>> listOfTimeForCurMethod = new ArrayList<>();
        ArrayList currentTime;

        for (Method method : sorter.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(IsSorted.class)) {
                System.out.println("For " + method.getName() + ": ");
                currentTime = sortArrByAllSizes(method, field, arrays,size);

                listOfTimeForCurMethod.add(currentTime);
                Sorter.print(currentTime);
            }

        }
        return listOfTimeForCurMethod;
    }

    private void fillMethodsArr() {
        for (Method method : sorter.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(IsSorted.class)) {
                nameOfMethods.add(method.getName());
            }
        }
    }

    private void fillFieldsArr(ArraysType arrays) {
        for (Field field : arrays.getClass().getDeclaredFields()) {// start of cycle by fields
            nameOfFields.add(field.getName());
        }
    }

}
