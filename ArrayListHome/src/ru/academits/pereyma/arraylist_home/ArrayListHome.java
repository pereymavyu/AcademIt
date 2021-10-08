package ru.academits.pereyma.arraylist_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        String filePath = "ArrayListHomeInput.txt";

        ArrayList<String> strings = getStringsFromFile(filePath);

        System.out.println("Строки из файла " + filePath + ": " + strings);
        System.out.println();

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 3, 1, 4, 6, 9, 3, 14, 14, 3, -7, -8, 9, 3, 3));

        System.out.println("Исходный список: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("Список после удаления четных чисел: " + numbers);

        System.out.println("Список после удаления четных чисел и дубликатов: " + getListWithoutRepeats(numbers));
    }

    public static ArrayList<String> getStringsFromFile(String filePath) {
        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                strings.add(currentLine);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Файл " + filePath + " не найден");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода-вывода");
        }

        return strings;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);

                --i;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutRepeats(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutRepeats = new ArrayList<>();

        for (Integer e : list) {
            if (!(listWithoutRepeats.contains(e))) {
                listWithoutRepeats.add(e);
            }
        }

        return listWithoutRepeats;
    }
}