class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            // Start from the top-right corner of the matrix
            return search(matrix, target, 0, matrix[0].length - 1);
        }

    // Recursive binary-like search from top-right corner
    private boolean search(int[][] matrix, int target, int row, int col) {
        if (row >= matrix.length || col < 0) {
            return false; // Out of bounds
        }

        if (matrix[row][col] == target) {
            return true; // Found the target
        } else if (matrix[row][col] < target) {
            // Move down
            return search(matrix, target, row + 1, col);
        } else {
            // Move left
            return search(matrix, target, row, col - 1);
        }
    }
}