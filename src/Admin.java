

import java.util.Scanner;

public class Admin extends User {
    private String fullName;

    public Admin(String fullName, String email, String password) {
        super(email, password);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public static Admin getEmployeeFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();


        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return new Admin(fullName, email, password);
    }


    @Override
    public String[] getUserRoles() {
        return new String[]{
                "VT", // View Teachers
                "VS", // View Students
                "VE", // View Employees
                "AT", // Add Teachers
                "AS", // Add Students
                "AE", // Add Employees
                "RT", // Remove Teachers
                "RS", // Remove Students
                "RE", // Remove Employees
                "LD", // Load Data
                "SD", // Load Data
                "999", // Exit
        };
    }
}

