package record;
import java.util.Scanner;

//Custom Exception for length less than 10
class LengthNotSufficientException extends Exception {
 public LengthNotSufficientException(String message) {
     super(message);
 }
}

public class MobileNumberValidator {

 public static void validateMobileNumber(String number) throws LengthNotSufficientException {
     // 1. Check if length exceeds 10
     if (number.length() > 10) {
         throw new ArrayIndexOutOfBoundsException("Invalid Mobile Number-ArrayIndexOutofBounds Exception");
     }
     
     // 2. Check if length is less than 10
     if (number.length() < 10) {
         throw new LengthNotSufficientException("Invalid Mobile Number – LengthNotSufficientException");
     }
     
     // 3. Check if all characters are digits (0-9)
     for (int i = 0; i < number.length(); i++) {
         if (!Character.isDigit(number.charAt(i))) {
             throw new NumberFormatException("Invalid Mobile Number –NumberFormatException");
         }
     }
     
     System.out.println("Valid number");
 }

 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     String mobileNumber = scanner.nextLine();
     
     try {
         validateMobileNumber(mobileNumber);
     } catch (ArrayIndexOutOfBoundsException | LengthNotSufficientException | NumberFormatException e) {
         System.out.println(e.getMessage());
     } finally {
         scanner.close();
     }
 }
}

