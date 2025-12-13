import java.util.Scanner;

public class Q2 {
    public static String getLargestNumber(String num) {
        int count = 0;
        for (char ch : num.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                count++;
            }
        }

        int index = 0;
        char[] digits = new char[count];
        for(char ch : num.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                digits[index] = ch;
                index++;
            }
        }

        for(int pass = 1; pass < digits.length; pass++) {
            for(int i = 0; i < digits.length - 1; i++) {
                if(digits[i] < digits[i+1]) {
                    char hold = digits[i];
                    digits[i] = digits[i+1];
                    digits[i+1] = hold;
                }
            }
        }

        String sum = "";
        for(char n : digits) {
            sum += n;
        }

        return sum;
    }

    public static String getSmallestNumber(String num) {
        char[] digits = getLargestNumber(num).toCharArray();

        for(int i = 0; i < digits.length / 2; i++) {
            char hold = digits[i];
            digits[i] = digits[digits.length - i - 1];
            digits[digits.length - i - 1] = hold;
        }

        String sum = "";
        for(char n : digits) {
            if(n != '0') {
                sum += n;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        String num =  sc.nextLine();

        System.out.println("Largest number: " + getLargestNumber(num));
        System.out.println("Smallest number: " + getSmallestNumber(num));

    }
}

