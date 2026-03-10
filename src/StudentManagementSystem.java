

import java.util.*;

public class StudentManagementSystem {
    private static List<Admin> adminList = new ArrayList<>();
    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    private static List<Teacher> teacherList = new ArrayList<>();

    static {
        adminList.add(new Admin("Anne Perera", "anne@gmail.com", "anne123"));
        adminList.add(new Admin("Pinsara Mallawarachchi", "pinsara@gmail.com", "pinsara123"));
    }


    public static void printMenuOptions(User user) {
        System.out.println("\n\nChoose an option: ");

        String[] menuOptions = {
                "* * * * * MENU * * * * * *",
                "AE: Add Employee to List",
                "RE: Remove Employee from List",
                "VE: View All Employees",
                "AS: Add Student to List",
                "RS: Remove Student from List",
                "VS: View All Students",
                "AT: Add Teacher to List",
                "RT: Remove Teacher from List",
                "VT: View All Teachers",
                "SD: Save Data(Students,Employees,Teachers, etc...) to files",
                "LD: Load Data(Students,Employees,Teachers, etc...) from File",
                "999 or EXIT: Exit the Program"
        };

        for (String item : menuOptions) {
            boolean hasPermissions = Arrays.stream(user.getUserRoles()).anyMatch(s -> item.startsWith(s));

            if (hasPermissions) {
                System.out.println(item);
            }
        }

    }


    private static final String BANNER =
            "\n******************************************\n" +
                    "*       Student Management System       *\n" +
                    "******************************************\n\n";

    public static void main(String[] args) {

        loadEmployeesFromFile();
        loadStudentsFromFile();
        loadTeachersFromFile();

        // Start system
        System.out.println("\nWELCOME TO STUDENT MANAGEMENT SYSTEM\n");

        Scanner scanner = new Scanner(System.in);

        // Display banner
        System.out.println(BANNER);


        System.out.println("---Login To The System---\n");
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("\n\n");

        User authenticatedUser = authenticateUser(email, password);

        if(authenticatedUser == null) {
            System.err.println("\n\nInvalid Credentials!!!");
            return;
        }

        systemLoop:
        while (true) {
            // Display menu option
            printMenuOptions(authenticatedUser);


            System.out.println("\n\nEnter Menu: ");
            String menuCode = scanner.nextLine().toUpperCase();

            if (!authenticatedUser.hasUserRole(menuCode)) {
                System.out.println("Invalid menu option. You might not have permissions.");
                continue;
            }

            // Confirming that the menu codes are correct
            switch (menuCode) {
                case "AE":
                    addEmployeeToList();
                    break;
                case "RE":
                    removeEmployeeFromList();
                    break;
                case "VE":
                    viewAllEmployees();
                    break;
                case "AS":
                    addStudentToList();
                    break;
                case "RS":
                    removeStudentFromList();
                    break;
                case "VS":
                    viewAllStudents();
                    break;
                case "AT":
                    addTeacherToList();
                    break;
                case "RT":
                    removeTeacherFromList();
                    break;
                case "VT":
                    viewAllTeachers();
                    break;
                case "SD":
                    saveEmployeesToFile();
                    saveTeachersToFile();
                    saveStudentsToFile();
                    break;
                case "LD":
                    loadEmployeesFromFile();
                    loadStudentsFromFile();
                    loadTeachersFromFile();
                    break;
                case "999":
                case "EXIT":
                    System.exit(0);
                    break systemLoop;
                default:
                    System.out.println("Invalid menu option. Please try again.");
                    break;
            }

            stayOneSecond();
        }
    }


    private static User authenticateUser(String email, String password) {
        for (Employee employee : employeeList) {
            if (employee.getEmail().equals(email) && employee.getPassword().equals(password)) {
                return employee;
            }
        }

        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        return null; // Authentication failed
    }

    private static void addEmployeeToList() {
        employeeList.add(Employee.getEmployeeFromInput());
        System.out.println("Employee added to the list.");
    }

    private static void removeEmployeeFromList() {
        if (!employeeList.isEmpty()) {
            System.out.println("Removing Employee from the list: " + employeeList.remove(0).getFullName());
        } else {
            System.out.println("Employee list is empty.");
        }
    }

    private static void viewAllEmployees() {
        if (!employeeList.isEmpty()) {
            System.out.println("All Employees in the list:");
            for (Employee user : employeeList) {
                System.out.println(user.getFullName());
            }
        } else {
            System.out.println("Employee list is empty.");
        }
    }

    private static void addStudentToList() {
        studentList.add(Student.getStudentFromInput());
        System.out.println("Student added to the list.");
    }

    private static void removeStudentFromList() {
        if (!studentList.isEmpty()) {
            System.out.println("Removing Student from the list: " + studentList.remove(0).getFullName());
        } else {
            System.out.println("Student list is empty.");
        }
    }

    private static void viewAllStudents() {
        if (!studentList.isEmpty()) {
            System.out.println("All Students in the list:");
            for (Student student : studentList) {
                System.out.println(student.getFullName());
            }
        } else {
            System.out.println("Student list is empty.");
        }
    }

    private static void addTeacherToList() {
        teacherList.add(Teacher.getTeacherFromInput());
        System.out.println("Teacher added to the list.");
    }

    private static void removeTeacherFromList() {
        if (!teacherList.isEmpty()) {
            System.out.println("Removing Teacher from the list: " + teacherList.remove(0).getFullName());
        } else {
            System.out.println("Teacher list is empty.");
        }
    }

    private static void viewAllTeachers() {
        if (!teacherList.isEmpty()) {
            System.out.println("All Teachers in the list:");
            for (Teacher teacher : teacherList) {
                System.out.println(teacher.getFullName());
            }
        } else {
            System.out.println("Teacher list is empty.");
        }
    }

    private static void saveEmployeesToFile() {
        Employee.storeToFile(employeeList, "employees.database");
        System.out.println("Employees saved to file.");
    }

    private static void saveStudentsToFile() {
        Student.storeToFile(studentList, "students.database");
        System.out.println("Students saved to file.");
    }

    private static void saveTeachersToFile() {
        Teacher.storeToFile(teacherList, "teachers.database");
        System.out.println("Teachers saved to file.");
    }

    private static void loadEmployeesFromFile() {
        employeeList = Employee.readFromFile("employees.database");
        System.out.println("Employees loaded from file.");
    }

    private static void loadStudentsFromFile() {
        studentList = Student.readFromFile("students.database");
        System.out.println("Students loaded from file.");
    }

    private static void loadTeachersFromFile() {
        teacherList = Teacher.readFromFile("teachers.database");
        System.out.println("Teachers loaded from file.");
    }
    public static void stayOneSecond() {
        try {
            Thread.sleep(1000); // Sleep for one second (1000 milliseconds)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
