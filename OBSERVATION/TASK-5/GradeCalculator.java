package observation;

import java.util.Scanner;

class Student {
    // Data Members
    private int rollNumber;
    private String studentName;
    private int[] marks;

    // Constructor to initialize student details
    public Student(int rollNumber, String studentName, int[] marks) {
        this.rollNumber = rollNumber;
        // String operations: trim leading/trailing spaces and convert to upper case
        this.studentName = studentName.trim().toUpperCase();
        this.marks = marks;
    }

    // Method to calculate total marks
    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to calculate average marks
    public double calculateAverage() {
        return (double) calculateTotal() / marks.length;
    }

    // Method to find highest marks using Math.max()
    public int findHighest() {
        int highest = marks[0];
        for (int i = 1; i < marks.length; i++) {
            highest = Math.max(highest, marks[i]);
        }
        return highest;
    }

    // Method to find lowest marks using Math.min()
    public int findLowest() {
        int lowest = marks[0];
        for (int i = 1; i < marks.length; i++) {
            lowest = Math.min(lowest, marks[i]);
        }
        return lowest;
    }

    // Method to calculate percentage (assuming each subject is out of 100)
    public double calculatePercentage() {
        int maxPossibleMarks = marks.length * 100;
        return ((double) calculateTotal() / maxPossibleMarks) * 100;
    }

    // Method to round percentage to two decimal places using Math.round()
    public double getRoundedPercentage() {
        double percentage = calculatePercentage();
        return Math.round(percentage * 100.0) / 100.0;
    }

    // Method to determine grade based on criteria
    public String calculateGrade() {
        double percentage = calculatePercentage();

        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to determine pass or fail (Pass if percentage >= 50%)
    public String getResultStatus() {
        return calculatePercentage() >= 50 ? "PASSED" : "FAILED";
    }

    // Method to display performance remark based on grade
    public String getPerformanceRemark() {
        String grade = calculateGrade();
        switch (grade) {
            case "A+": return "Outstanding Performance!";
            case "A":  return "Excellent Job!";
            case "B":  return "Good Performance.";
            case "C":  return "Satisfactory, Scope for improvement.";
            case "D":  return "Needs Improvement.";
            default:   return "Unsatisfactory. Needs serious effort.";
        }
    }

    // Method to display student performance report
    public void displayReport() {
        System.out.println("\n=============================================");
        System.out.println("      STUDENT PERFORMANCE ANALYSIS REPORT     ");
        System.out.println("=============================================");
        System.out.println("Roll Number        : " + rollNumber);
        System.out.println("Student Name       : " + studentName + " (Length: " + studentName.length() + ")");
        System.out.println("---------------------------------------------");
        System.out.print("Marks Obtained     : ");
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Sub " + (i + 1) + ": " + marks[i] + (i < marks.length - 1 ? " | " : ""));
        }
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("Total Marks        : " + calculateTotal() + " / " + (marks.length * 100));
        System.out.println("Average Marks      : " + calculateAverage());
        System.out.println("Highest Subject Mark: " + findHighest());
        System.out.println("Lowest Subject Mark : " + findLowest());
        System.out.println("Percentage         : " + getRoundedPercentage() + "%");
        System.out.println("Grade              : " + calculateGrade());
        System.out.println("Result Status      : " + getResultStatus());
        System.out.println("Performance Remark : " + getPerformanceRemark());
        System.out.println("=============================================");
    }
}

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Student Performance Analysis System ===");
        
        // Input Roll Number
        System.out.print("Enter Roll Number: ");
        int rollNum = scanner.nextInt();
        scanner.nextLine(); // Clear newline buffer

        // Input Student Name
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        // Input Marks for 5 Subjects
        int[] subjectMarks = new int[5];
        System.out.println("Enter marks for 5 subjects (out of 100):");
        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            subjectMarks[i] = scanner.nextInt();
        }

        // Create Student object
        Student student = new Student(rollNum, name, subjectMarks);

        // Display performance report
        student.displayReport();

        scanner.close();
    }
}