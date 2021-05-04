import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.exit;

public class Driver {

        public static void main(String[] args){

            ArrayList<Matrix> matrixHolder = new ArrayList<Matrix>();
            HashMap<Boolean, Matrix> hashmapExistence = new HashMap<>();

            boolean matrixExists = false;
            boolean matrixSecondExists = false;
            Matrix matrix = null;
            Matrix matrixSecond = null;

            System.out.println();
            System.out.println("Welcome to Ty's Linear Algebra Calculator");
            System.out.println("-----------------------------------------\n");
            System.out.print("Please select an option:");

            int optionn = 0;

            while (optionn != 5)

            {
                optionn = showMenu();

                switch (optionn) {

                    case 1:
                        System.out.print("\nHow many rows in matrix:");
                        Scanner scan = new Scanner(System.in);
                        int rows = scan.nextInt();
                        System.out.print("\nHow many columns in matrix:");
                        int columns = scan.nextInt();
                        matrixExists = true;
                        matrix = new Matrix(rows,columns);
                        matrix.getEntries();
                        matrixHolder.add(matrix);
                        matrix.adjointMatrix();
                        break;

                    case 2:
                        if(!matrixExists){
                            System.out.println("Matrix not defined currently\n");
                        }
                        else{
                            matrix.printMatrix();
                        }
                        break;

                    case 3:
                        Scanner keyboard;
                        int selection;

                        keyboard = new Scanner(System.in);
                        showMatrixMenu(matrixExists,matrixSecondExists,matrix, matrixSecond);
                        System.out.println("Enter your choice:");
                        selection = keyboard.nextInt();

                        switch (selection){
                            case 1:
                                assert matrix != null;
                                System.out.printf("%nDeterminant: %d",matrix.matrixDeterminant(matrix.getMatrix()));
                                break;
                            case 2:
                                assert matrix != null;
                                matrix.formatAugmentedMatrix();
                                matrix.printRefactorMatrix();
                                break;

                            default:
                                System.out.println("Invalid Option.");
                        }

                        break;

                    case 4:
                        System.out.print("\nHow many rows in matrix:");
                        Scanner scan1 = new Scanner(System.in);
                        int rows1 = scan1.nextInt();
                        System.out.print("\nHow many columns in matrix:");
                        int columns1 = scan1.nextInt();
                        matrixSecondExists = true;
                        matrixSecond = new Matrix(rows1,columns1);
                        matrixSecond.getEntries();
                        matrixHolder.add(matrix);

                        break;


                    case 5:
                        exit(0);

                        break;

                    default:
                        System.out.println("Sorry, please enter valid Option");

                }
            }
        }

        public static int showMenu(){
            int optionn;
            Scanner keyboard;
            System.out.println();
            keyboard = new Scanner(System.in);
            System.out.println("Main Menu:");
            System.out.println("--------------");
            System.out.println("1.Add a new Matrix");
            System.out.println("2.Print Current Matrix");
            System.out.println("3. Display Matrix options");
            System.out.println("4. Add another matrix");
            System.out.println("4.Quit");
            System.out.println("--------------");
            System.out.println("Enter your choice:");
            optionn = keyboard.nextInt();

            return optionn;
        }

        public static void showMatrixMenu(boolean matrixExists,boolean secondMatrixExists, Matrix matrix, Matrix matrix2){
            System.out.println("Current Matrix");
            System.out.println("---------------\n");
            if(!matrixExists){
                System.out.println("[]");
            }
            else if(matrixExists && !secondMatrixExists){
                matrix.printMatrix();
            }
            else{
                matrix.printMatrix();
                matrix2.printMatrix();
            }
            System.out.println();
            System.out.println("Matrix Menu:");
            System.out.println("--------------");
            System.out.println("1. Find Determinant");
            System.out.println("2. Show Format Matrix");
            System.out.println("3. Elementary Operations");
            System.out.println("4.Quit");
            System.out.println("--------------");

        }
    }


