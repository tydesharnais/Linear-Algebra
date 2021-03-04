import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class Matrix<T> {
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
    public int determinant() {

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
    }
}