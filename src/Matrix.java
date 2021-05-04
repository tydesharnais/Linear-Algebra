import java.util.Scanner;



public class Matrix {
    /***
     * Variables and Arrays
     */
    private int rowNumbers; //real row numbers of matrix
    private int columnNumbers; //real column numbers of matrix
    int[][] numMatrix; //Original Numerical Matrix
    char[][] charMatrix;
    String[][] xMatrix; // n by 1 x's matrix
    int[][] refactorMatrix; //refactoring Numerical Aug matrix into useable nxn matrix for determinants etc
    int[][] bMatrix; // n by 1 matrix for b/y
    int[][] adjugateMatrix; //use for inverse etc
    private String determineMatrix;
    private int determinantNumber;
    private Scanner scan = new Scanner(System.in);

    //Constructor
    public Matrix(int rowNumbers, int columnNumbers) {
        this.rowNumbers = rowNumbers;
        this.columnNumbers = columnNumbers;
        charMatrix = new char[rowNumbers][columnNumbers];
        numMatrix = new int[rowNumbers][columnNumbers];
    }

    /**
     * Getters and Setters
     */
    public void setRowNumbers(int rowNumbers){
        this.rowNumbers = rowNumbers;
    }

    public void setColumnNumbers(int columnNumbers){
        this.columnNumbers = columnNumbers;
    }

    public int getRowNumbers(){
        return rowNumbers;
    }

    public int getColumnNumbers(){
        return columnNumbers;
    }

    public int[][] getMatrix(){
        return numMatrix;
    }


    public void getEntries(){

        System.out.println();

        System.out.print("What type of Matrix (say int or char):");
        determineMatrix = scan.nextLine();
        for(int i = 0; i < rowNumbers; i++){
            for(int j = 0; j < columnNumbers; j++){
                System.out.printf("Entry for Matrix[%d][%d]:",i,j);
                int entry = scan.nextInt();
                numMatrix[i][j]=entry;
                System.out.println(numMatrix[i][j]);
            }
        }
    }

    public void printMatrix() {
        System.out.printf("%nThis is a %d by %d Matrix%n", rowNumbers, columnNumbers);
            for(int i = 0; i < rowNumbers; i++){
                System.out.print("[");
                for(int j = 0; j < columnNumbers; j++){
                    System.out.print(" "+numMatrix[i][j]);
                    if(j==columnNumbers-1){
                        System.out.print(" ]");
                    }
                }
                System.out.println();
            }
    }

    public void printRefactorMatrix(){

        System.out.println();
        for(int i = 0; i < rowNumbers; i++){
            System.out.print("[");
            for(int j = 0; j < columnNumbers-1; j++){
                System.out.print(" "+refactorMatrix[i][j]);
                if(j ==columnNumbers-2){
                    System.out.printf(" ] [ %s ] [ %d ]",xMatrix[i][0],bMatrix[i][0]);
                }
            }
            System.out.println();
        }
    }

    public void getCofactor(int temp[][], int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of the matrix
        for (int row = 0; row < rowNumbers; row++)
        {
            for (int col = 0; col < columnNumbers; col++)
            {
                // Copying into temporary matrix only those element
                // which are not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = numMatrix[row][col];

                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Recursive function for finding determinant of matrix.
    n is current dimension of A[][]. */
    /**
     * Method that calculates determinant of given matrix
     *
     * @param matrix matrix of which we need to know determinant
     *
     * @return determinant of given matrix
     */
    public static double matrixDeterminant (double[][] matrix) {
        double temporary[][];
        double result = 0;

        if (matrix.length == 1) {
            result = matrix[0][0];
            return (result);
        }

        if (matrix.length == 2) {
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return (result);
        }

        for (int i = 0; i < matrix[0].length; i++) {
            temporary = new double[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporary[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temporary);
        }
        return (result);
    }







    public char subscriptHelperFunction(int currentNumber){

        char[] subscripts = {'₁','₂','₃','₄','₅','\u2086'};
        return subscripts[currentNumber];

    }
    public void formatAugmentedMatrix(){
        String temp = "₅";
        System.out.println();

        xMatrix = new String[rowNumbers][3];
        bMatrix = new int[rowNumbers][3];
        refactorMatrix = new int[rowNumbers][columnNumbers-1];

        for(int i = 0; i < rowNumbers; i++){
            for(int j = 0; j < columnNumbers; j++){
                if(j < columnNumbers-1) {
                    refactorMatrix[i][j] = numMatrix[i][j];
                }
                else if(j == columnNumbers-1){
                    bMatrix[i][0] = numMatrix[i][j];
                    xMatrix[i][0] = String.format("x%c",subscriptHelperFunction(i));
                }
            }
        }
    }
   /* public int determinant() {

        determinantNumber = 0;
        if(rowNumbers == 2){
            if(columnNumbers ==2){
                determinantNumber = numMatrix[0][0]*numMatrix[1][1] - numMatrix[0][1]*numMatrix[1][0];
            }
        }
        else if(rowNumbers == 3){

            if(columnNumbers == 3){

                int iRow = numMatrix[0][0] * (numMatrix[1][1]*numMatrix[2][2] - numMatrix[1][2]*numMatrix[2][1]);
                int jRow = numMatrix[0][1] * (numMatrix[1][0]*numMatrix[2][2] - numMatrix[1][2]*numMatrix[2][0]);
                int kRow = numMatrix[0][2] * (numMatrix[1][0]*numMatrix[2][1] - numMatrix[1][1]*numMatrix[2][0]);

                determinantNumber = iRow - jRow + kRow;
            }
        }

        if(rowNumbers != columnNumbers){

            System.out.println("Determinant cannot be found, must be a n x n matrix");

        }

        return determinantNumber;
    }*/

    public void adjointMatrix(){

        if(rowNumbers == 2) {
            if (columnNumbers == 2) {

                adjugateMatrix = new int[rowNumbers][columnNumbers];

                int temp1 = numMatrix[0][1] - 2 * (numMatrix[0][1]);
                int temp2 = numMatrix[1][0] - 2 * (numMatrix[1][0]);

                adjugateMatrix[0][0] = numMatrix[1][1];
                adjugateMatrix[0][1] = temp1;
                adjugateMatrix[1][0] = temp2;
                adjugateMatrix[1][1] = numMatrix[0][0];

            }
        }
        else if(rowNumbers == 3){
            if(columnNumbers == 3){
                adjugateMatrix = new int[rowNumbers][columnNumbers];

                for(int i = 0; i < rowNumbers; i++){
                    for(int j = 0; j < columnNumbers; j++){
                        adjugateMatrix[i][j] = cofactorFinder(i,j);
                    }
                }



            }

        }
    }

    /**
     *
     * @param iRow - for the specific row of element in matrix
     * @param jRow - for the specific column of element in matrix
     * @return positive or negative 1
     */
    public int negativeOneHelper(int iRow, int jRow){
        int temp = iRow + jRow;
        int outputNum;

        if(temp%2 == 0){ //If row + column mod 2 has a remainder or not determines whether it is odd or even
            outputNum = 1;
        }
        else{
            outputNum = -1;
        }
        return outputNum;
    }

    /**
     * /
     * @param rowNum - for the specific row of element in matrix
     * @param colNum - for the specific column of element in matrix
     * [C(11) C(12) C(13).....C(1n)]
     * [.                        . ]
     * [C(n1) C(n2) C(n3).....C(nn)]
     *
     * @return cofactor
     */
    public int cofactorFinder(int rowNum, int colNum){
        //Recall a cofactor can be found as (-1)^i+j |A(ij)|



        return 3;
    }

    public void printAdjointMatrix(){

        System.out.printf("%nThis is a %d by %d Adjoint Matrix%n", rowNumbers, columnNumbers);
        for(int i = 0; i < rowNumbers; i++){
            System.out.print("[");
            for(int j = 0; j < columnNumbers; j++){
                System.out.print(" "+adjugateMatrix[i][j]);
                if(j==columnNumbers-1){
                    System.out.print(" ]");
                }
            }
            System.out.println();
        }
    }
}