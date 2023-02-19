import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Task2 {
    private static int[] fromStrToIntArr(String incomingString){
        String[] stringSplit = incomingString.split(" ");
        int[] integerArray = new int[stringSplit.length];
        for (int i = 0; i < stringSplit.length; i++){
            integerArray[i] = Integer.parseInt(stringSplit[i]);
        }
        return integerArray;
    }

    private static int findSmallest(ArrayList<Integer> inputArray){ // ищем минимальный элемент массива
        int smallest = inputArray.get(0);
        int smallestIndex = 0;
        for (int i = 0; i < inputArray.size(); i++) {
            if (inputArray.get(i) < smallest){
                smallest = inputArray.get(i);
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    private  static ArrayList<Integer> findIndexes(ArrayList<Integer> inputArray, int minutiesTillNY){
        ArrayList<Integer> sortedArray = new ArrayList<>();
        ArrayList <Integer> indexesOfFigurs = new ArrayList<>();
        for (int i = 0; i < inputArray.size(); i++){
            int smallestIndex = findSmallest(inputArray);
            int smallest = inputArray.get(smallestIndex);
            if (minutiesTillNY < smallest){ //если первый же минимальный элемент больше идеального веса возвращаем пустой список
                break;
            }
            indexesOfFigurs.add(smallestIndex + 1); // здесь хранятся индексы минимальный элементов + единица из за условий задачи
            minutiesTillNY = minutiesTillNY - inputArray.get(smallestIndex);
            inputArray.set(smallestIndex, Integer.MAX_VALUE);
        }
        return indexesOfFigurs;
    }
    /*
    идея в том, что при сортировке выбором мы будем отнимать от общего количества минут, то количество минут,
    коротрое необходимо на фигуру с минимальным количеством минут на исправление, затем следующую и следующую
    и т.д.
    Так в структуре 'ArrayList <Integer> indexesOfFigurs' появятся индексы тех фигур, которое Кузя сможет исправить.
    Длина данной структуры будет количеством таких фигур.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputString1 = bufferedReader.readLine();
        String inputString2 = bufferedReader.readLine();
        int[] descriptionTask = fromStrToIntArr(inputString1);
        int[] weightsFigurs = fromStrToIntArr(inputString2);
        int quantityFigurs = descriptionTask[0]; // количество фигур
        int idealWeight = descriptionTask[1]; // идеальный вес фигуры
        int minuties = descriptionTask[2]; // количество минут до НГ

        ArrayList<Integer> minutiesOnChange = new ArrayList<>(); // количество минут на изменение каждой фигуры
        for (int i = 0; i < quantityFigurs; i++) {
            minutiesOnChange.add(i, Math.abs(idealWeight - weightsFigurs[i]));
        } // модуль ращницы между идеальным весом и весом фигуры, то есть количество минут, которое требуется для исправлениякаждой фигуры
        ArrayList<Integer> quantityFigursAnswer = findIndexes(minutiesOnChange, minuties);
        if (quantityFigursAnswer.isEmpty()){
            System.out.println(0);
        } else {
            System.out.println(quantityFigursAnswer.size());
            for(int i = 0; i < quantityFigursAnswer.size(); i++){
                System.out.print(quantityFigursAnswer.get(i) + " ");
            }
        }
    }
}
