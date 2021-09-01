package ru.academits.pereyma.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        String filePath = "ArrayListHomeInput.txt";

        ArrayList<String> stringArrayList = getListFromFile(filePath);

        System.out.println("Строки из файла " + "ArrayListHomeInput.txt" + ": " + stringArrayList);
        System.out.println();

        Integer[] array = new Integer[]{3, 3, 1, 4, 6, 9, 3, 14, 14, 3, -7, -8, 9, 3, 3};
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));

        System.out.println("Исходный список: " + arrayList);

        removeEvenNumbers(arrayList);
        System.out.println("Список после удаления четных чисел: " + arrayList);

        removeRepeats(arrayList);
        System.out.println("Список после удаления четных чисел и дубликатов: " + arrayList);
    }

    public static ArrayList<String> getListFromFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);

                --i;
            }
        }
    }

    public static void removeRepeats(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            int currentValue = list.get(i);

            for (int j = i + 1; j < list.size(); ++j) {
                if (currentValue == list.get(j)) {
                    list.remove(j);

                    --j;
                }
            }
        }
    }
}
