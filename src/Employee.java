
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee extends User {

    private String fullName;
    private double baseSalary;
    private String phoneNumber;

    public Employee(String fullName, double baseSalary, String phoneNumber, String email, String password) {
        super(email, password);
        this.fullName = fullName;
        this.baseSalary = baseSalary;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static Employee getEmployeeFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Base Salary: ");
        double baseSalary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return new Employee(fullName, baseSalary, phoneNumber, email, password);
    }

    public static void storeToFile(List<Employee> employees, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Employee employee : employees) {
                writer.println(employee.fullName + "," + employee.baseSalary + "," + employee.phoneNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> readFromFile(String fileName) {
        List<Employee> employees = new ArrayList<>();

        File file = new File(fileName);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.lines().forEach(line -> {
                String[] employeeInfo = line.split(",");
                employees.add(new Employee(
                        employeeInfo[0], Double.parseDouble(employeeInfo[1]), employeeInfo[2],  employeeInfo[3], employeeInfo[4]
                ));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public String[] getUserRoles() {
        return new String[]{
                "VT", // View Teachers
                "VS", // View Students
                "VE", // View Employees
                "LD", // Load Data
                "999", // Exit
        };
    }
}

