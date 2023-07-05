import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task1 {
    // Начало: 7:40
    // Конец: 10:10
    public static void main(String[] args) {
        // testResult(); // Быстрая проверка по конрольным примерам
        String inpuString = getInput();
        if (checkInput(inpuString)) {
            System.out.println("На входе: “" + inpuString + "”");
            System.out.println("На выходе: “" + result(inpuString) + "”");

            // У меня проблемы с кодировкой разносторонних кавычек в консоли,
            // так что добавлю альтернативный вариант с классическими кавычками:

            // System.out.println("На входе: \"" + inpuString + "\"");
            // System.out.println("На выходе: \"" + result(inpuString) + "\"");
        }
    }

    // Вывод результата в виде строки
    public static String result(String input) {
        return resultAsString(sortArrayList(listMostCommonIntegers(countUniqueNumbers(getIntegers(input)))));
    }

    // Контрольные строки ввода (игнорируют проверку)
    private static void testResult() {
        String test = "5 12 76 34 2 76 5 3 5 2 8 5";
        System.out
                .println(resultAsString(sortArrayList(listMostCommonIntegers(countUniqueNumbers(getIntegers(test))))));
        test = "7 13 11 34 2 12 7 34";
        System.out
                .println(resultAsString(sortArrayList(listMostCommonIntegers(countUniqueNumbers(getIntegers(test))))));
        test = "7 13 11 34 2 12 7 34 7 13 34";
        System.out
                .println(resultAsString(sortArrayList(listMostCommonIntegers(countUniqueNumbers(getIntegers(test))))));
        System.out
                .println(resultAsString(sortArrayList(listMostCommonIntegers(countUniqueNumbers(getIntegers(test))))));
    }

    // Преобразование Array в строку для вывода
    private static String resultAsString(ArrayList<Integer> inputArray) { 
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputArray.size() - 1; i++) {
            result.append(inputArray.get(i));
            result.append(" ");
        }
        result.append(inputArray.get(inputArray.size() - 1)); // Избегаем пробела после последнего числа
        return result.toString();
    }

    // Преобразование списка чисел в пары [число, сколько раз число встречается в ряду чисел]
    private static HashMap<Integer, Integer> countUniqueNumbers(ArrayList<Integer> inputArray) {
        HashMap<Integer, Integer> countResult = new HashMap<>();
        for (Integer checkingNum : inputArray) {
            if (countResult.containsKey(checkingNum)) {
                countResult.put(checkingNum, countResult.get(checkingNum) + 1);
            } else {
                countResult.put(checkingNum, 1);
            }
        }
        return countResult;
    }

    // Вывод наиболее часто встречающегося числа / чисел
    private static ArrayList<Integer> listMostCommonIntegers(HashMap<Integer, Integer> inputHashMap) {
        ArrayList<Integer> mostCommonIntegers = new ArrayList<>();
        Integer max = 1;
        for (Map.Entry<Integer, Integer> input : inputHashMap.entrySet()) {
            if (input.getValue() > max)
                max = input.getValue();
        }
        for (Map.Entry<Integer, Integer> input : inputHashMap.entrySet()) {
            if (input.getValue() == max)
                mostCommonIntegers.add(input.getKey());
        }
        return mostCommonIntegers;
    }

    // По заданию игнорируем существование Collections.sort
    private static ArrayList<Integer> sortArrayList(ArrayList<Integer> toSort) {
        if (toSort.size() < 2)
            return toSort;
        ArrayList<Integer> sortedIntList = toSort;
        Integer temp = 0;
        for (int i = 0; i < sortedIntList.size(); i++) {
            for (int j = 0; j < sortedIntList.size() - 1; j++) {
                if (sortedIntList.get(i) < sortedIntList.get(j)) {
                    temp = sortedIntList.get(i);
                    sortedIntList.set(i, sortedIntList.get(j));
                    sortedIntList.set(j, temp);
                }
            }
        }
        return sortedIntList;
    }

    // Преобразование строки в список чисел
    private static ArrayList<Integer> getIntegers(String inputString) {
        if (inputString == null)
            return null;
        ArrayList<Integer> integerList = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(inputString.split(" ")));
        for (String strNum : stringList) {
            integerList.add(Integer.parseInt(strNum));
        }
        return integerList;
    }

    // Ввод данных
    private static String getInput() {
        // "которая на входе получает строку с k числами, разделенными пробелами"
        // В примере использованы только целые числа, на практике я бы уточнил,
        // но предположим, что все входящие числа - целые и положительные.
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        sc.close();
        if (checkInput(inputString))
            return inputString;
        else {
            errorMsg(null);
            return null;
        }
    }

    // Проверка входящей строки
    private static boolean checkInput(String inputString) {
        int lengthMin = 3; // 3 - минимальная длинна строки из 2 чисел и пробела
        if (inputString == null || inputString.length() < lengthMin)
            return false;

        String filteredString = inputString.replaceAll("[^0-9 ]", "");
        // Проверка на содержание символов отличных от цифр и пробелов
        if (inputString.length() != filteredString.length()) {
            return false;
        }
        
        // Проверка на наличие двойного(и более) пробела. Наличие приводит к ошибке при разделении строки
        if (inputString.contains("  "))
            return false;

        return true;
    }

    // Вывод ошибки
    private static void errorMsg(String msg) {
        String defaultErrorMsg = "Ошибка ввода данных";
        if (msg == null || msg.length() == 0)
            System.out.println(defaultErrorMsg + "."); // Вывод ошибки без комментария
        else
            System.out.println(defaultErrorMsg + "(" + msg + ")."); // Вывод ошибки с комментарием
        // Можно было бы реализовать через StringBuilder, 
        // но в данном моменте не столь важно
    }
}