public class Fibonacci {

    // Сервисная функция для поиска минимума

    // из двух элементов

    public static int min(int x, int y) {
        return (x <= y) ? x : y; // тернарный оператор (одно условие - два возможных результата)
    }


    // Возвращает индекс x, если присутствует, иначе возвращает -1

    public static int fibMonaccianSearch(int arr[], int x, int n) {
        // Инициализировать числа Фибоначчи
        int fibMMm2 = 0; // (m-2) -ый номер Фибоначчи
        int fibMMm1 = 1; // (m-1) -ый номер Фибоначчи
        int fibM = fibMMm2 + fibMMm1; // м Фибоначчи


        // fibM собирается хранить самые маленькие
        // Число Фибоначчи, большее или равное n

        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }


        // Отмечает удаленный диапазон спереди

        int offset = -1;


        // пока есть элементы для проверки.
        //Обратите внимание, что мы сравниваем arr [fibMm2] с x.
        // Когда fibM становится 1, fibMm2 становится 0

        while (fibM > 1) {
            // Проверяем, является ли fibMm2 действительным местоположением

            int i = min(offset + fibMMm2, n - 1);

            //Если х больше значения в
            //индекс fibMm2, вырезать массив подмассива
            //от смещения до i

            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }

            // Если х больше, чем значение в индексе
            // fibMm2, вырезать подрешетку после i + 1

            else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }
            //элемент найден. индекс возврата
            else return i;

        }

        //сравнение последнего элемента с x

        if (fibMMm1 == 1 && arr[offset + 1] == x)
        {  return offset + 1;}

        //элемент не найден. возврат -1

        return -1;

    }
}
