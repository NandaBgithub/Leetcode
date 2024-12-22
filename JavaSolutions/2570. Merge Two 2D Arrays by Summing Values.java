import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int[][] result = {};

        Arrays.sort(nums1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(nums2, (a, b) -> Integer.compare(a[0], b[0]));

        Set<Integer> s1 = toSet(nums1);
        Set<Integer> s2 = toSet(nums2);

        s1.addAll(s2);
        for (int id : s1){
            int n1 = (BinarySearch(id, nums1))[1];
            int n2 = (BinarySearch(id, nums2))[1];
    
            int[] pair = {id, n1 + n2};
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = pair;
        }
        
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
        return result;
    }

    private static Set<Integer> toSet(int[][] arr){
        Set<Integer> aset = new HashSet<>();

        for (int[] pair : arr){
            int id = pair[0];
            aset.add(id);
        }
        return aset;
    }

    private static int[] BinarySearch(int id, int[][] arr) {
        return BinarySearchHelper(id, arr, 0, arr.length - 1);
    }

    private static int[] BinarySearchHelper(int id, int[][] arr, int start, int end) {
        if (start > end) {
            return new int[]{0, 0};
        }

        int pivot = start + (end - start) / 2;

        if (arr[pivot][0] == id) {
            return arr[pivot];
        } else if (arr[pivot][0] > id) {
            return BinarySearchHelper(id, arr, start, pivot - 1);
        } else {
            return BinarySearchHelper(id, arr, pivot + 1, end);
        }
    }
}