

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private String firstName;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String address;

    // Constructor
    public Student(String firstName, String lastName, String birthday, String phoneNumber, String address) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthday(birthday);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
    }

    // Getter methods

    // Static method to get student information from user input using Scanner
    public static Student getStudentFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Birthday: ");
        String birthday = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        return new Student(firstName, lastName, birthday, phoneNumber, address);
    }

    // Static method to store a list of students in a file
    public static void storeToFile(List<Student> students, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.println(student.firstName + "," + student.lastName + "," +
                        student.birthday + "," + student.phoneNumber + "," + student.address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static method to read a list of students from a file
    public static List<Student> readFromFile(String fileName) {
        File file = new File(fileName);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.lines().forEach(line -> {
                String[] studentInfo = line.split(",");
                students.add(new Student(
                        studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3], studentInfo[4]
                ));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
