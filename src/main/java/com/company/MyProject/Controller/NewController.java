package com.company.MyProject.Controller;

import com.company.MyProject.Abstract.NewDrawer;
import com.company.MyProject.Abstract.Sorter;
import com.company.MyProject.Annotation.IsNOTSorter;
import com.company.MyProject.Annotation.IsSPECIAL;
import com.company.MyProject.POJO.ARRAYS;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class NewController {
    private static Sorter method = new Sorter();
    private static ArrayList<String> fields = new ArrayList<>();
    private static ArrayList<String> methods = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<Double>>> time = new ArrayList<>();

    public static void work() {
        fillMethodsArr();// заполняем массив названий методов
        ARRAYS arrays = new ARRAYS(methods.size() ); // генерируем массивы массивов
        for (Field field : arrays.getClass().getDeclaredFields()) {// проход по полям
            System.out.println("Time for" + field.getName() + " ");
            ArrayList<ArrayList<Double>> listOfTime = new ArrayList<>();
            try {
                field.setAccessible(true);
                fields.add(field.getName());// добаляем название поля в масив полей
                ArrayList<int []> biglist = (ArrayList<int[]>) field.get(arrays);// получаем поле
                for (int i = 0; i < biglist.size() ; i++) {// проходимся по всем азмеран конкретного массива
                    System.out.println("Size of arr: " + (i + 1));
                    int [] arr = biglist.get(i);

                    ArrayList myTime = process(arr);
                    listOfTime.add(myTime);// добавляем полученный массив времени для конкретного поля
                    Sorter.print(myTime);
                }
                time.add(listOfTime);// добавляем массив времени конкретного поля в массив общего времени

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println();




        }
        NewDrawer.main( methods, fields, time);


    }

    public static ArrayList process(int[] arr) { // цикл по массиву методов
        ArrayList timearr = new ArrayList();

        for (Method m : method.getClass().getDeclaredMethods()) {
            int[] temporary = arr.clone();
            if (!m.isAnnotationPresent(IsNOTSorter.class)) {// если присудствует анномация
                long starttime = System.nanoTime();// померяли время перед началом работы
                if (m.isAnnotationPresent(IsSPECIAL.class)) {// нужно ли три параметра
                    try {
                        m.invoke(m, temporary, 0, temporary.length);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        m.invoke(m, temporary);// исполняем нужный нам метод


                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                long currenttime = ((System.nanoTime() - starttime)); // считаем конечное время
                double result = (double) currenttime / 1000000;
                timearr.add(result);// добавляем полученое время в масив конкретного метода и конкретного массива

            }

        }
        return timearr;
    }

    public static void fillMethodsArr() {
        for (Method m : method.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(IsNOTSorter.class)) {
                methods.add(m.getName());
            }
        }
    }

    public static void print(ArrayList<ArrayList> lists) {
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j) + ", ");

            }
            System.out.println();

        }

    }
}
