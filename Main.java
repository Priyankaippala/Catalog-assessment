import java.util.Scanner;

public class Main {

    public static void gaussElimination(double[][] matrix, double[] constants) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            
            double pivot = matrix[i][i];
            for (int j = i; j < n; j++) {
                matrix[i][j] /= pivot;
            }
            constants[i] /= pivot;

            for (int j = i + 1; j < n; j++) {
                double factor = matrix[j][i];
                for (int k = i; k < n; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
                constants[j] -= factor * constants[i];
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = constants[i];
            for (int j = i + 1; j < n; j++) {
                solution[i] -= matrix[i][j] * solution[j];
            }
        }
        System.out.print("constant = ",solution[solution.length-1]);
    }

    public static int calcY(String value, int base) {
        int decimalValue = 0;
        int power = 0;

        for (int i = value.length() - 1; i >= 0; i--) {
            char digitChar = value.charAt(i);

            int digit;
            if (Character.isDigit(digitChar)) {
                digit = digitChar - '0';
            } else if (Character.isLetter(digitChar)) {
                digit = Character.toUpperCase(digitChar) - 'A' + 10; 
            } else {
                throw new IllegalArgumentException("Invalid character in the number.");
            }

            if (digit >= base) {
                throw new IllegalArgumentException("Digit " + digit + " is not valid for base " + base);
            }

            decimalValue += digit * Math.pow(base, power);
            power++;
        }

        return decimalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        int n = sc.nextInt();  
        int k = sc.nextInt();  

        double[][] matrix = new double[k][k];
        double[] constants = new double[k];

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();       
            int base = sc.nextInt();    
            String value = sc.next();  
            int y = calcY(value, base); 

            constants[i] = y;

            for (int j = 0; j < k; j++) {
                matrix[i][j] = Math.pow(x, k - j - 1); // x^(k-j-1)
            }
        }

        gaussElimination(matrix, constants);

        sc.close();
    }
}
