import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a credit card number: ");
        String CCNumber = in.nextLine().trim();

        boolean valid = false;
        while (!valid) {
            valid = checkValidCCNumber(CCNumber);
            if (valid) {
                System.out.println("Credit card number is valid.");
                break;
            } else {
                System.out.print("Please enter a credit card number: ");
                CCNumber = in.nextLine().trim();
            }
        }
    }

    private static boolean checkValidCCNumber(String CCNumber) {
        if (!passedLuhnCheck(CCNumber)) {
            System.out.println("This is not a valid credit card number...");
            return false;
        } else if (!isValidCCNumberLength(CCNumber)) {
            System.out.println("This is not a valid credit card number...");
            return false;
        } else if (!isNumber(CCNumber)) {
            System.out.println("You did not enter in a number...");
            return false;
        } else if (!hasValidPrefix(CCNumber)) {
            System.out.println("This is not a valid credit card number...");
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasValidPrefix(String CCNumber) {
        String firstTwoNumbers = CCNumber.substring(0, 2);
        return (CCNumber.charAt(0) == '4') || (CCNumber.charAt(0) == '5') || (CCNumber.charAt(0) == '6') || (firstTwoNumbers.contains("37"));
    }

    private static boolean isNumber(String CCNumber) {
        try {
            Long.parseLong(CCNumber);
        } catch (NumberFormatException exc) {
            System.out.println("You did not enter a number...");
            return false;
        }

        return true;
    }

    private static boolean isValidCCNumberLength(String CCNumber) {
        if(CCNumber.length() + 1 >= 13 || CCNumber.length() + 1 <= 16) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean passedLuhnCheck(String CCNumber) {
        long CCNumberLong = Long.parseLong(CCNumber);
        int luhnCollection = 0;
        for (long temp = CCNumberLong; temp > 0; temp /= 100) {
            long currentSecondDigit = (temp % 100);
            if (currentSecondDigit * 2 >= 10) {
                luhnCollection += ((currentSecondDigit / 10) + (currentSecondDigit % 10));
            } else {
                luhnCollection += (currentSecondDigit * 2);
            }
        }

        for (long temp = CCNumberLong; temp > 0; temp /= 100) {
            long currentOddDigit = temp % 10;
            luhnCollection += currentOddDigit;
        }

        return luhnCollection % 10 == 0;
    }


}
