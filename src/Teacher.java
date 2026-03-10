

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teacher {
    private String fullName;
    private String nic;
    private String description;
    private String subject;
    private String phoneNumber;
    private String qualification;

    public Teacher(String fullName, String nic, String description, String subject, String phoneNumber, String qualification) {
        this.fullName = fullName;
        this.nic = nic;
        this.description = description;
        this.subject = subject;
        this.phoneNumber = phoneNumber;
        this.qualification = qualification;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public static Teacher getTeacherFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter NIC: ");
        String nic = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Qualification (O/L or A/L): ");
        String qualification = scanner.nextLine();

        return new Teacher(fullName, nic, description, subject, phoneNumber, qualification);
    }

    public static void storeToFile(List<Teacher> teachers, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Teacher teacher : teachers) {
                writer.println(teacher.fullName + "," + teacher.nic + "," + teacher.description + "," +
                        teacher.subject + "," + teacher.phoneNumber + "," + teacher.qualification);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Teacher> readFromFile(String fileName) {
        List<Teacher> teachers = new ArrayList<>();

        File file = new File(fileName);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.lines().forEach(line -> {
                String[] teacherInfo = line.split(",");
                teachers.add(new Teacher(
                        teacherInfo[0], teacherInfo[1], teacherInfo[2], teacherInfo[3], teacherInfo[4], teacherInfo[5]
                ));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teachers;
    }
}
