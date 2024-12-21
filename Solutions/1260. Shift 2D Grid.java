import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int[] flatGrid = flatten(grid);
        int[] shiftedGrid = roundrobin(flatGrid, k, flatGrid.length);
        int[][] expandedGrid = expand(shiftedGrid, new int[]{grid.length, grid[0].length});
        List<List<Integer>> result = arrayToList(expandedGrid);
        return result;
    }


    private static int[] flatten(int[][] grid){
        int len = grid.length * grid[0].length;
        int[] result = new int[len]; 
        int i = 0;
        for (int[] arr: grid){
            for (int n: arr){
                result[i] = n;
                i += 1;
            }
        }

        return result;
    }

    private static int[][] expand(int[] arr, int[] dimensions){
        int row = dimensions[0];
        int col = dimensions[1];
        int index = 0;
        int[][] result = new int[row][col];

        for (int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result[i][j] = arr[index];
                index += 1;
            }
        }
        return result;
    }

    private static int[]  roundrobin(int[] arr, int k, int len){
        int[] tail;
        int[] head;
        int[] result;
        int[] arrList = Arrays.copyOf(arr, arr.length);
        int remainder = 0;

        if (k > len){
            remainder = k % len; 
            System.out.println(remainder);


            while (remainder >= len){
                remainder = remainder % len;
            }
        } else {
            remainder = k;
        }
        
        arr = arrList;
        tail = Arrays.copyOfRange(arr, arr.length-remainder, arr.length);
        head = Arrays.copyOfRange(arr, 0, arr.length-remainder);
        result = concatenateArrays(tail, head);
        return result;
    }

    private static boolean isEven(int n){
        if (n % 2 == 0){
            return true;
        } else {return false;}
    }

    private static int[] concatenateArrays(int[] headArray, int[] tailArray) {
        int[] result = new int[headArray.length + tailArray.length];
        System.arraycopy(headArray, 0, result, 0, headArray.length);
        System.arraycopy(tailArray, 0, result, headArray.length, tailArray.length);
        return result;
    }
    
    private static void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    private static List<List<Integer>> arrayToList(int[][] arr){
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> innerList = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                innerList.add(arr[i][j]);
            }
            list.add(innerList);
        }

        return list;
    }
}


