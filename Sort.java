public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        long start = System.currentTimeMillis();
        quickSort(arr,0,arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    public static void maoPao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

    }

    public static void xuanZe(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void chaRu(int[] arr) {
        int startIndex = 0;
        while (true) {
            if (arr[startIndex + 1] > arr[startIndex]) {
                startIndex++;
            } else {
                startIndex++;
                break;
            }

        }
        for (int i = startIndex; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] >= arr[j - 1]) {
                    break;
                } else {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start > end) return;
        int left = start;
        int right = end;
        while (left < right) {
            while (arr[right] >= arr[start] && left < right) right--;
            while (arr[left] <= arr[start] && left < right) left++;
            int tmp = arr[right];
            arr[right] = arr[left];
            arr[left] = tmp;
        }
        int tmp = arr[left];
        arr[left] = arr[start];
        arr[start] = tmp;
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);
    }
}
