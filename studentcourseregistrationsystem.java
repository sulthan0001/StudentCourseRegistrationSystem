import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Course 
{
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int availableSlots;

    public Course(String code, String title, String description, int capacity)
    {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
    }

    public String getCode() 
    {
        return code;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getDescription() 
    {
        return description;
    }

    public int getAvailableSlots() 
    {
        return availableSlots;
    }

    public boolean registerStudent()
     {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        } else {
            return false;
        }
    }

    public void dropStudent()
    {
        availableSlots++;
    }
        
    
    @Override
    public String toString()
     {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nAvailable Slots: " + availableSlots + "/" + capacity;
    }
}

class Student 
{
    private int id;
    private String name;
    private List<Course> registeredCourses;

    public Student(int id, String name) 
    {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<Course> getRegisteredCourses()
    {
        return registeredCourses;
    }

    public void registerCourse(Course course) 
    {
        if (course.registerStudent()) 
        {
            registeredCourses.add(course);
        } else 
        {
            System.out.println("Course " + course.getCode() + " is already full.");
        }
    }

    public void dropCourse(Course course) 
    {
        if (registeredCourses.remove(course)) 
        {
            course.dropStudent();
            System.out.println("Dropped course: " + course.getCode());
        } else 
        {
            System.out.println("You are not registered for this course: " + course.getCode());
        }
    }

    @Override
    public String toString() 
    {
        return "Student ID: " + id + "\nName: " + name;
    }
}

public class studentcourseregistrationsystem
{
    public static void main(String[] args) 
    {
        Course course1 = new Course("CSCI101", "Introduction to  python Programming", "Learn the basics of python programming", 20);
        Course course2 = new Course("WEBI304", "Introduction to Development", "Learn the Web Development", 15);
        Course course3 = new Course("CSEI202", "Introduction to java programming", "Learn the basics of java programming", 10);

        Student student1 = new Student(1, "John");
        Student student2 = new Student(2, "Latha");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) 
            {
                System.out.println("1. List Courses");
                System.out.println("2. Register for a Course");
                System.out.println("3. Drop a Course");
                System.out.println("4. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Available Courses:");
                        System.out.println(course1);
                        System.out.println(course2);
                        System.out.println(course3);
                        break;

                    case 2:
                        System.out.print("Enter student ID: ");
                        int studentId = scanner.nextInt();
                        System.out.print("Enter course code to register: ");
                        String courseCode = scanner.next();

                        Student student = (studentId == student1.getId()) ? student1 : student2;
                        Course course = null;

                        if (courseCode.equals(course1.getCode())) 
                        {
                            course = course1;
                        } else if (courseCode.equals(course2.getCode())) 
                        {
                            course = course2;
                        } else if (courseCode.equals(course3.getCode())) 
                        {
                            course = course3;
                        } else 
                        {
                            System.out.println("Invalid course code.");
                            continue;
                        }
                        System.out.print(" You are Registered for this course");
                        student.registerCourse(course);
                        break;

                    case 3:
                        System.out.print("Enter student ID: ");
                        studentId = scanner.nextInt();
                        System.out.print("Enter course code to drop: ");
                        courseCode = scanner.next();

                        student = (studentId == student1.getId()) ? student1 : student2;
                        course = null;

                        if (courseCode.equals(course1.getCode())) 
                        {
                            course = course1;
                        } else if (courseCode.equals(course2.getCode())) 
                        {
                            course = course2;
                        } else if (courseCode.equals(course3.getCode())) 
                        {
                            course = course3;
                        } else 
                        {
                            System.out.println("Invalid course code.");
                            continue;
                        }

                        student.dropCourse(course);
                        break;

                    case 4:
                        System.out.println("Thank you for using the Course Registration System.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}


