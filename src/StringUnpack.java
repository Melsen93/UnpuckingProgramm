import java.util.Arrays;

public class StringUnpack {

    private final static String SYMBOLS = ("abcdefghijklmnopqrstuvwxyz123456789[]"); //Валидные символы


    //Метод трансформации строки
    public static String transformation (String startString) { //Принимаем на вход строку
        if (validStr(startString)) {                           //Если строка валида, то конвертируем и возвращаем рузультат
            return stringUnpacking(startString.toCharArray());
        }                                                      //Иначе выводим сообщение об ошибке
        return "Error. Invalid Input String. Valid input characters: Latin letters, numbers, and parentheses []";
    }

    //Метод проверки строки на валидность
    private static boolean validStr(String startStr){          //Принимаем строку
        startStr = startStr.toLowerCase();                     //Приводим к единому регистру
        char [] name = startStr.toCharArray();                 //Создаем массив символов для посимвольной проверки
        for (char c : name) {
            int valid = SYMBOLS.indexOf(Character.toString(c));
            if (valid == -1) {
                return false;
            }
        }
        return true;
    }

    private static String stringUnpacking(char[] validString){

        StringBuilder stringResult = new StringBuilder();
        int bracketsFixer = 0;           //        счетчик - фиксатор внутренних скобок
        int bracketPosition = 0;         //        позиция открывающей скобки
        int repetitions = 0;             //        количество повторений символов внутри скобок



        for (int i = 0; i < validString.length;i++){
//            если символ - буква за скобкой, добавить его к возвращаемой строке
            if (Character.isLetter(validString[i]) && bracketsFixer == 0)
                stringResult.append(validString[i]);

//            если символ - число за скобкой, присвоить его переменной repetitions
            if (Character.isDigit(validString[i]) && bracketsFixer == 0)
                repetitions = Integer.parseInt(String.valueOf(validString[i]));

//            если символ - открывающая скобка, то изменить счётчик,
//            а если открываюшая внешняя - зафиксировать в придачу ее позицию
            if (validString[i] == '['){
                bracketsFixer++;
                if (bracketsFixer == 1) bracketPosition = i;
            }

//           если символ - открывающая скобка, то изменить счётчик,
//           а если открываюшая внешняя - зафиксировать в придачу ее позицию
            if (validString[i] == ']'){
                bracketsFixer--;
                if (bracketsFixer == 0){


                    for (int j = 0; j < repetitions; j++) { //с помощью цикла соединяем символы необходимое количество раз
                        stringResult.append(stringUnpacking(Arrays.copyOfRange(validString, bracketPosition + 1, i)));
                    }

                    bracketPosition = 0;
                    repetitions = 0;
                }
            }
        }
        return stringResult.toString(); //выводим итоговый результат
    }
}

