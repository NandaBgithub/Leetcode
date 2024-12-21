class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
        int row = m;
        int col = n;
        float elementsPerRow = (float) original.length / row;
        
        int[][] result2d = new int[m][n];
        int index = 0;
        if ((float) col == elementsPerRow){
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    result2d[i][j] = original[index++];
                }
            }
        } else {
            return (new int[0][0]);
        }
        return result2d;
    }
}