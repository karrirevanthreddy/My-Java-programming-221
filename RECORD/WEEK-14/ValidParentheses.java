package record;

import java.util.Scanner;

public class ValidParentheses {
    public static boolean isValid(String s) {
        // An array to act as our stack
        char[] stack = new char[s.length()];
        int top = -1;

        for (char c : s.toCharArray()) {
            // Push the expected closing bracket onto the array
            if (c == '(') {
                stack[++top] = ')';
            } else if (c == '{') {
                stack[++top] = '}';
            } else if (c == '[') {
                stack[++top] = ']';
            } 
            // If it's a closing bracket, check if the stack is empty or mismatched
            else if (top == -1 || stack[top--] != c) {
                return false;
            }
        }

        // Valid only if all opened brackets were closed
        return top == -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter parentheses string: ");
        String input = sc.nextLine();

        // Remove spaces if input is entered like "( )"
        input = input.replaceAll("\\s+", "");

        if (isValid(input)) {
            System.out.println("Output: valid");
        } else {
            System.out.println("Output: Not valid");
        }
        sc.close();
    }
}
