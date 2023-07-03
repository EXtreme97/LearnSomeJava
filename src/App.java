public class App {
    // 实现希尔排序算法
    public static void shellSort(int[] arr) {
        int n = arr.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
            gap /= 2;
        }

    }

    // 冒泡排序算法
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 基数排序算法
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int exp = 1;
        while (max / exp > 0) {
            countingSort(arr, exp);
            exp *= 10;
        }
    }

    public static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // 测试一下上面三个排序算法
    public static void testSort(int[] arr) {
        long start = System.currentTimeMillis();
        // bubbleSort(arr);
        shellSort(arr);
        // radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    public static void main(String[] args) throws Exception {
        // 生成一个包含十万个元素的整形数组
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }

        // System.out.println("Hello, World!");
        testSort(arr);
    }
}
