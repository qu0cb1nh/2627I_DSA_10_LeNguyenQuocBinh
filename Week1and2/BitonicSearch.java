package homework;

import java.util.Scanner;

public class BitonicSearch {
    public static int search(int[] a, int key) {
        int lo = 0, hi = a.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < a[mid + 1]) lo = mid + 1;
            else hi = mid;
        }
        int peak = lo;

        int leftResult = binarySearch(a, key, 0, peak, true);
        if (leftResult != -1) return leftResult;

        return binarySearch(a, key, peak + 1, a.length - 1, false);
    }

    private static int binarySearch(int[] a, int key, int lo, int hi, boolean isAsc) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == key) return mid;
            if (isAsc ? a[mid] < key : a[mid] > key) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int key = sc.nextInt();
        System.out.println("Phan tu xuat hien tai vi tri: " + search(arr, key));
        sc.close();
    }
}
