package com.mca.algorithm.rookie;

/**
 * ClassName: Code002_Sort
 * Package: com.mca.algorithm.rookie
 * Description: 各种排序方法
 *
 * @Author: yujie.qin
 * @Create: 2023/4/3 - 10:41
 * @version: v1.0
 */
public class Code002_Sort {
    public static int[] initUnsortedArr = {2,4,8,6,3,1,5,7,9,10};

    public static void swap(int[] arr, int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void print(int[] arr){
        for (int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 选择排序
     * 0 ~ N-1 => 找到最小值
     * 1 ~ N-1 => 找到最小值
     * 2 ~ N-1 => 找到最小值
     * */
    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i + 1;j < N;j++){
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    /**
     * 冒泡排序 ==> 谁大谁往后，后面的排好序了
     * 0 ~ N-1 => 比较大小，交换
     * 0 ~ N-2 => 比较大小，交换
     * 0 ~ N-3 => 比较大小，交换
     * */
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--){
            // 0 ~ N-1两两比较，交换
            for (int second = 1; second <= end; second++){
                if (arr[second - 1] > arr[second]){
                    swap(arr, second - 1, second);
                }
            }
        }
    }

    /**
     * 插入排序
     * 数组范围：0 ~ 1 => 做到有序
     * 数组范围：0 ~ 2 => 做到有序
     * 数组范围：0 ~ 3 => 做到有序
     * 数组范围：0 ~ end => 比较大小，交换，做到有序
     * */
    public static void insertSort1(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        for (int end = 1; end < N; end++){
            int newNunIndex = end;
            while (newNunIndex - 1 >= 0 && arr[newNunIndex - 1] > arr[newNunIndex]){
                swap(arr, newNunIndex - 1, newNunIndex);
                newNunIndex--;
            }
        }
    }

    public static void insertSort2(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        for (int end = 1; end < N; end++){
            // pre 是当前数的前一个数，如果前一个数比当前数大，就
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1] ; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    /**
     * 二分查找法
     * */
    public static int midSelector(int[] arr, int num){
        if (arr == null || arr.length == 0) return -1;
        int L = 0;
        int R = arr.length - 1;
        while (L <= R){
            // 如果是int类型最大值，会出现越界
            // int mid = (L + R) / 2;
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == num){
                return mid;
            }else if (arr[mid] < num){
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return  -1;
    }

    /**
     * 找到数组中 >= num 最左的位置
     * */
    public static int mostLeftNoLessNumIndex(int[] arr, int num){
        if (arr == null || arr.length == 0) return -1;
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while (L <= R){
            // 如果是int类型最大值，会出现越界
            // int mid = (L + R) / 2;
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= num){
                ans = mid;
                R = mid - 1;
            }else if (arr[mid] < num){
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 找到数组中 <= num 最右的位置
     * */
    public static int mostRightLessNumIndex(int[] arr, int num){
        if (arr == null || arr.length == 0) return -1;
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while (L <= R){
            // 如果是int类型最大值，会出现越界
            // int mid = (L + R) / 2;
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= num){
                ans = mid;
                L = mid + 1;
            }else if (arr[mid] > num){
                R = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 局部最小值
     * [1,2,4,3,5] => 3就是局部最小值
     * */
    public static int binarySearchAwsome(int[] arr){
        if (arr == null || arr.length == 0) return -1;
        int N = arr.length - 1;
        if (N == 0) return 0;
        if (arr[0] < arr[1]) return 0;
        if (arr[N] < arr[N-1]) return N;
        int L = 0;
        int R = N;
        while (L < R - 1){
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]){
                return mid;
            }else {
                if (arr[mid] > arr[mid - 1]){
                    R = mid - 1;
                }
                if (arr[mid] > arr[mid + 1]){
                    L = mid + 1;
                }
            }
        }
        return arr[L] < arr[R] ? L : R;
    }

    public static void main(String[] args) {
        print(initUnsortedArr);
//        selectionSort(initUnsortedArr);
//        bubbleSort(initUnsortedArr);
//        insertSort1(initUnsortedArr);
//        insertSort2(initUnsortedArr);
//        print(initUnsortedArr);
//
        int[] sortedArray = {1,2,2,3,4,5,6,7,7,8};
//        System.out.println(midSelector(sortedArray, 7));
//        System.out.println(mostLeftNoLessNumIndex(sortedArray, 7));
        System.out.println(mostRightLessNumIndex(sortedArray, 7));

        // 局部最小值

    }

    /**
     * 相邻不相等随机长度，随机数组
     * */
    public static int[] randomArray(int maxLen, int maxValue){
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0){
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                }while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int minValueIndex){
        if (arr.length == 0) return minValueIndex == -1;
        int left = minValueIndex - 1;
        int right = minValueIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minValueIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minValueIndex] : true;
        return leftBigger && rightBigger;
    }
}
