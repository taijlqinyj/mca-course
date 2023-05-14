package com.mca.algorithm.rookie;

import java.util.Stack;

/**
 * ClassName: Code009_MergeSortAndQuickSort
 * Package: com.mca.algorithm.rookie
 * Description: 归并排序  +  快速排序
 *
 * @Author: yujie.qin
 * @Create: 2023/4/11 - 16:40
 * @version: v1.0
 */
public class Code009_MergeSortAndQuickSort {

    /**
     * 递归方法实现归并排序
     * */
    public static void mergeSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }
    // 让arr[L ... R]范围上的数有序
    public static void process(int[] arr, int L, int R){
        if (L == R){
            return;
        }
        int mid = L + ((R -L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    public static void merge(int[] arr, int L, int mid, int R){
        int[] help = new int[R -L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        // 两部分都没越界，谁小copy到help数组中，p1或者p2 ++，数组下标++
        while (p1 <= mid && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 左边和右边越界，只可能有一个发生
        // 不可能出现同时越界
        // 右边越界
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        // 左边越界
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        //
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    /**
     * 非递归方法实现归并排序
     * 步长问题
     * 越界问题
     * */
    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        // 步长
        int step = 1;
        int N = arr.length;
        while (step < N){
            // 左组边界 L 。。。mid
            int L = 0;
            while (L < N){
                int mid = 0;
                // L ... N-1 之间由 N-1-L+1个数，如果N-L之间的数大于step，则可以，反之最后一个左组只能到N-1
                if (N - L >= step){
                    mid = L + step - 1;
                }else {
                    mid = N - 1;
                }
                if (mid == N - 1){
                    break;
                }
                // 右组边界 mid+1 ... R
                int R = 0;
                if (N - 1 - mid >= step){
                    R = mid + step;
                }else {
                    R = N - 1;
                }
                merge(arr, L, mid, R);

                // 下一个左右组
                // 如果越界了直接break，否则下一个左组的开始边界为 R + 1
                if (R == N - 1){
                    break;
                }else {
                    L = R + 1;
                }
            }
            // 需要处理步长越界问题
            if (step > (N / 2)){
                break;
            }else {
                step *= 2;
            }
        }
    }

    /**
     * 非递归方法实现归并排序
     * 步长问题
     * 精简版本
     * */
    public static void mergeSort3(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N){
            int L = 0;
            while (L < N){
                if (mergeSize >= N - L){
                    break;
                }
                int mid = L + mergeSize - 1;
                int R = mid + Math.min(mergeSize, N - mid - 1);
                merge(arr, L, mid, R);
                L = R + 1;
            }
            if (mergeSize > (N / 2)){
                break;
            }
            mergeSize <<= 2;
        }
    }

    /**
     * 快速排序
     * 小于等于P的都放左边（不保证有序），大于P的都放右边（不保证有序）
     * 1、当前数 <= P ，当前数和小于等于区的下一个数交换，小于等于区向右扩张一位 ，当前数跳下一个
     * 2、当前数 > P ，当前数直接跳下一个
     * 得到 <=P 的在左边，>p 的在右边
     * */
    public static void splitNum1(int[] arr){
        // 小于等于区右边界
        int lastEqualR = -1;
        // 当前数下标
        int index = 0;
        // arr[mostR] ==> P
        int mostR = arr.length - 1;
        while (index < arr.length){
            if (arr[index] <= arr[mostR]){
                /*swap(arr, lastEqualR + 1, index);
                lastEqualR++;
                index++;*/
                swap(arr, ++lastEqualR, index++);
            }else {
                index++;
            }
        }
    }

    /**
     * 升级快排
     * 小于等于P的都放左边（不保证有序），大于P的都放右边（不保证有序）
     * 1、当前数 < P ，当前数和小于区的下一个数交换，小于区向右扩张一位(++) ，当前数跳下一个(++)
     * 2、当前数 > P ，当前数和大于区的前一个数交换，大于区向左扩张一位(--) ，当前数不动
     * 3、当前数 = P ，当前数直接跳下一个(++)
     * 4、当前数和大于区左边界相撞时，结束遍历，P和 大于区的第一个数交换
     * 得到 <P 的在左边，=p 的在中间，>p 的在右边
     * */
    public static void splitNum2(int[] arr){
        int N = arr.length;
        // 小于区右边界
        int lastR = -1;
        // 大于区左边界
        int mostL = N - 1;
        // 当前数位置下标
        int index = 0;
        while (index < mostL){
            if (arr[index] < arr[N - 1]){
                // step 1
                swap(arr, ++lastR, index++);
            } else if(arr[index] > arr[N - 1]){
                // step 2
                swap(arr, --mostL, index);
            }else {
                // step 3
                index++;
            }
        }
        // step 4
        swap(arr, mostL, N - 1);
    }

    /**
     * 在arr[L ... R]范围上，拿arr[R] 做划分
     * @return int[] 返回等于区数组的左右边界
     * */
    public static int[] partition(int[] arr, int L, int R){
        // 小于区的右边界
        int lessR = L - 1;
        // 大于区的左边界
        int mostL = R;
        // 当前数位置
        int index = L;
        while (index < mostL){
            if (arr[index] < arr[R]){
                // step 1
                swap(arr, ++lessR, index++);
            } else if(arr[index] > arr[R]){
                // step 2
                swap(arr, --mostL, index);
            }else {
                // step 3
                index++;
            }
        }
        // step 4
        swap(arr, mostL, R);
        return new int[]{ lessR + 1, mostL };
    }

    /**
     * 递归实现快排
     * */
    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        processQuickSort(arr, 0, arr.length - 1);
    }

    public static void processQuickSort(int[] arr, int L, int R){
        if (L >= R){
            return;
        }
        int[] equalsAreaLeftAndRightIndex = partition(arr, L, R);
        // equalsAreaLeftAndRightIndex[0] 等于区域第一个数
        // equalsAreaLeftAndRightIndex[1] 等于区域最后一个数
        processQuickSort(arr, L, equalsAreaLeftAndRightIndex[0] - 1);
        processQuickSort(arr, equalsAreaLeftAndRightIndex[1] + 1, R);
    }

    /**
     * 非递归实现快排
     * */
    public static class Job {
        public int L;
        public int R;

        public Job(int l, int r) {
            L = l;
            R = r;
        }
    }
    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()){
            Job current = stack.pop();
            int[] equalsAreaLeftAndRightIndex = partition(arr, current.L, current.R);
            // 注意：可能不存在比划分值小的数，即不存在小于区域，小于区域的边界问题
            if (equalsAreaLeftAndRightIndex[0] > current.L){
                // 才有小于区域
                stack.push(new Job(current.L, equalsAreaLeftAndRightIndex[0] - 1));
            }
            if (equalsAreaLeftAndRightIndex[1] < current.R){
                // 才有大于区域
                stack.push(new Job(equalsAreaLeftAndRightIndex[0] + 1, current.R));
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,5,2,3,7,6,1,4,5,7,4};
        quickSort1(arr);
        quickSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
    }
}
