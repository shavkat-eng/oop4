import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Класс Teacher
class Teacher {
    private String name;
    private int id;

    public Teacher(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + "'}";
    }
}

// Класс TeacherService
class TeacherService {
    private List<Teacher> teachers = new ArrayList<>();
    private int nextId = 1;

    public void createTeacher(String name) {
        Teacher teacher = new Teacher(name, nextId++);
        teachers.add(teacher);
    }

    public void editTeacher(int id, String newName) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                teacher.setName(newName);
                return;
            }
        }
    }

    public List<Teacher> getAllTeachers() {
        return teachers;
    }
}

// Класс TeacherView
class TeacherView {
    public void displayTeachers(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
}

// Класс TeacherController
class TeacherController {
    private TeacherService teacherService = new TeacherService();
    private TeacherView teacherView = new TeacherView();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("1. Create Teacher");
            System.out.println("2. Edit Teacher");
            System.out.println("3. Display All Teachers");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    createTeacher();
                    break;
                case 2:
                    editTeacher();
                    break;
                case 3:
                    displayAllTeachers();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createTeacher() {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        teacherService.createTeacher(name);
    }

    private void editTeacher() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Enter new teacher name: ");
        String newName = scanner.nextLine();
        teacherService.editTeacher(id, newName);
    }

    private void displayAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        teacherView.displayTeachers(teachers);
    }
}

// Класс Main для тестирования
public class Main {
    public static void main(String[] args) {
        TeacherController controller = new TeacherController();
        controller.run();
    }
}
