import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;


public class Lab2 {

    public static void heap_sort(int[] arr)
    {
        int n = arr.length;

        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--)
        {
            // Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(arr, i, 0);
        }
    }

    // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i, что является
// индексом в arr[]. n - размер кучи
    static void heapify(int[] arr, int n, int i)
    {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // Если самый большой элемент не корень
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largest);
        }
    }

    public static int binSearch(int[] sortedArray, int key, int low, int high) { // на входе массив, число, индекс к-рого ищем в массиве, лау и хай
        int index = -1; // если вышли из цикла, не вернув индекс, то эл-та index нет в массиве

        while (low <= high) { // пока лоу не станет больше, чем хай, т.е. если в массиве sortedArray нет искомого числа или регион поиска стал пустым
            int mid = (low + high) / 2; // ищем среднее число в массиве
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index; // возвращаем либо индекс искомого числа, либо -1, если оно не найдено
    }



    public static int interpolationSearch(int[] sortedArray, int toFind) { // принимает массив целых чисел и целое число как искомое в этом массиве
        // Возвращает индекс элемента со значением toFind или -1, если такого элемента не существует
        int mid;
        int low = 0; // создаем 2 индекса
        int high = sortedArray.length - 1;

        while (sortedArray[low] < toFind && sortedArray[high] > toFind) { // пока то, что мы ищем больше, чем то, что на индексе low и меньше high
            if (sortedArray[high] == sortedArray[low]) // если на индексе лау и хай стоит одно и то же, т.е. область поиска сузилась до одного эл-та, то заканчиваем выполнение цикла. Защита от деления на 0
                break;
            mid = low + ((toFind - sortedArray[low]) * (high - low)) / (sortedArray[high] - sortedArray[low]);

            if (sortedArray[mid] < toFind) // если по вычисленному индексу стоит меньше, чем то, что мы ищем, тогда смещаем левую точку
                low = mid + 1;
            else if (sortedArray[mid] > toFind) // если по вычисленному индексу стоит больше, чем то, что мы ищем, тогда смещаем правую точку
                high = mid - 1;
            else
                return mid;
        }

        if (sortedArray[low] == toFind) // если на индексе лау стоит то, что нам нужно, возвращаем значение лоу
            return low;
        if (sortedArray[high] == toFind) // если на индексе хай стоит то, что нам нужно, возвращаем значение хай
            return high;

        return -1; // Not found
    }

    static int[] addElement(int[] a, int e) { // добавление эл-та в массив
        a  = Arrays.copyOf(a, a.length + 1); // в метод AcO передаем наш массив, из к-рого надо скопировать знач-я и длину нового массива, в к-рый мы копируем данные
        a[a.length - 1] = e;
        return a;
    }


    public static int[] removeElement(int index, int[] n) { // удаление эл-та из массива

        int end = n.length;

        for(int j = index; j < end - 1; j++) {
            n[j] = n[j + 1];
        }
        end--;

        int[] newArr = new int[end];
        for(int k = 0; k < newArr.length; k++) {
            newArr[k] = n[k];
        }

        return newArr;
    }

    

    public static void main (String[]args) {
      Scanner scan = new Scanner(System.in);
      int n, min, max;
        System.out.println("Введите размерность набора данных");
        n = scan.nextInt();
        System.out.println("Минимальный элемент генерации набора данных");
        min = scan.nextInt();
        System.out.println("Максимальный элемент генерации набора данных");
        max = scan.nextInt();

        int a[] = new int[n];
      for (int i = 0; i < n; i++){
          a[i] = (int) (Math.random() * ((max - min) + 1)) + min;
      }

        System.out.println("Набор данных:");

      heap_sort(a);
      Tree tree = new Tree();
        for (int i = 0; i < n; i++){
            System.out.println(a[i] + " ");
            tree.insertNode(a[i]);
        }
        System.out.println("Введите эллемент для поиска: ");
        int key = scan.nextInt();
        int low = 0;
        int high = a.length - 1;
        long time1 = System.nanoTime();
        System.out.println("Бинарный поиск. Индекс " + binSearch(a, key, low, high) + " time: " + (System.nanoTime() - time1) + "ns");
        long time2 = System.nanoTime();
        Node foundNode = tree.findNodeByValue(key);
        foundNode.printNode();
        System.out.println(" time: " + (System.nanoTime()-time2) + "ns");
        long time3 = System.nanoTime();
        System.out.println("Фибоначчиев поиск. Индекс " + Fibonacci.fibMonaccianSearch(a, key, n) + " time: " + (System.nanoTime() - time3) + "ns");
        long time4 = System.nanoTime();
        System.out.println("Интерполяционный поиск. Индекс "+ interpolationSearch(a, key) + " time: " + (System.nanoTime() - time4) + "ns");
        long time5 = System.nanoTime();
        System.out.println("Стандартный поиск. Индекс " + Arrays.stream(a).boxed().collect(Collectors.toList()).indexOf(key) + " time: " + (System.nanoTime() - time5));
    }

}
