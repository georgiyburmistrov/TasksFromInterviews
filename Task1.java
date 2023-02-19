import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Task1 {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        String str = bufferedReader.readLine();
        String[] keys = str.split(" "); // считываем уникальные символы

        String rowStr = bufferedReader.readLine();
        String[] rows = rowStr.split(" "); // считываем по порядку какому ряду какой символ принадлеждит
        int[] rowsArr = new int[rows.length];
        for (int i = 0; i < rows.length; i++) {
            rowsArr[i] = Integer.parseInt(rows[i]);
        } // переводим ряды в целочисленные цифровые значения
        HashMap<String, Integer> KeysRows = new HashMap<>();
        for (int i = 0; i < n; i++) {
            KeysRows.put(keys[i], rowsArr[i]);
        } // в этой МАП хранится соответствие клавиши ряду, при этом клавиши выступают ключом
        int quantityRefSymbol = Integer.parseInt(bufferedReader.readLine()); // считываем количество символов в реферате
        String refStr = bufferedReader.readLine();
        String[] ref = refStr.split(" "); // считываем символы вы реферате
        int bitness = 0; // искомая разноразрядность
        for (int i = 0; i < quantityRefSymbol - 1; i++) {
            // проверяем следующий символ, вытаскиваем значение по ключу. Если значения разные, то bitness + 1
            int refVal = KeysRows.get(ref[i]); //
            int refValNext = KeysRows.get(ref[i + 1]);
            if (refVal != refValNext){
                ++bitness;
            }
        }
        System.out.println(bitness);
    }
}
