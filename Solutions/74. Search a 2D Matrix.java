class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int pivot = matrix.length/2;
        int[] arr = binarySearchCol(matrix, target, pivot, 0, matrix.length - 1);
        boolean result = binarySearchRow(arr, target, arr.length/2, 0, arr.length-1);
        return result;
    }

    private static int[] binarySearchCol(int[][] arr, int target, int pivot, int start, int end) {
        
        if (start > end) {
            return new int[0]; 
        }

 
        pivot = start + (end - start) / 2;

        if (arr[pivot][0] == target) {
            return arr[pivot]; 
        }

        if (target > arr[pivot][0]) {

            if (binarySearchRow(arr[pivot], target, arr[pivot].length / 2, 0, arr[pivot].length - 1)) {
                return arr[pivot];
            }
            return binarySearchCol(arr, target, pivot, pivot + 1, end); 
        } else {
            return binarySearchCol(arr, target, pivot, start, pivot - 1); // Recurse left
        }
    }


    private static boolean binarySearchRow(int[] arr, int target, int pivot, int start, int end) {
        if (start > end) {
            return false; 
        }


        pivot = start + (end - start) / 2;


        if (arr[pivot] == target) {
            return true;
        }

        if (target > arr[pivot]) {
            return binarySearchRow(arr, target, pivot, pivot + 1, end);
        }

        else {
            return binarySearchRow(arr, target, pivot, start, pivot - 1); 
        }
    }


}