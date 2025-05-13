package model;

/**
 * This class is to record the map of one game. For example:
 */
public class MapModel {
    int[][] matrix;
    private int[][] originalMatrix;


    public MapModel(int[][] matrix) {this.matrix = matrix;this.originalMatrix = copyMatrix(matrix); }

    public int[][] copyMatrix (int[][] matrix){
        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i],0,copy[i],0,matrix[i].length);
        }
        return copy;
    }//保留了原来matrix的样子

    public void resetOriginalMatrix() {
        this.matrix = copyMatrix(originalMatrix);
    }//把matrix还原为初始状态

    public int getWidth() {
        return this.matrix[0].length;
    }

    public int getHeight() {
        return this.matrix.length;
    }

    public int getId(int row, int col) {
        return matrix[row][col];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public boolean checkInWidthSize(int col) {
        return col >= 0 && col < matrix[0].length;
    }

    public boolean checkInHeightSize(int row) {
        return row >= 0 && row < matrix.length;
    }

//    public boolean isVictory() {
//        if (matrix[4][2] == 4 && matrix[4][3] == 4 && matrix[5][2] == 4 && matrix[5][3] == 4) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
