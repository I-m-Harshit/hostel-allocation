import java.util.Scanner;

class Student {
    String name, gender, course;
    boolean allotted = false;

    Student(String name, String gender, String course) {
        this.name = name;
        this.gender = gender;
        this.course = course;
    }
}

public class HostelAllotment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student[] s = new Student[20];

        // Input student data
        for (int i = 0; i < 20; i++) {
            System.out.println("\nEnter details of Student " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Gender (Boy/Girl): ");
            String gender = sc.nextLine().trim();

            System.out.print("Course (UG/PG): ");
            String course = sc.nextLine().trim();

            s[i] = new Student(name, gender, course);
        }

        int room = 1;
        int[] roomOf = new int[20];

        // Step 1: Allot rooms to students
        // Prefer same gender and same course
        for (int i = 0; i < 20; i++) {
            if (s[i].allotted)
                continue;

            int partner = -1;

            // First preference: Same gender + same course
            for (int j = i + 1; j < 20; j++) {
                if (!s[j].allotted
                    && s[i].gender.equalsIgnoreCase(s[j].gender)
                    && s[i].course.equalsIgnoreCase(s[j].course)) {
                    partner = j;
                    break;
                }
            }

            // Second preference: Same gender
            if (partner == -1) {
                for (int j = i + 1; j < 20; j++) {
                    if (!s[j].allotted
                        && s[i].gender.equalsIgnoreCase(s[j].gender)) {
                        partner = j;
                        break;
                    }
                }
            }

            s[i].allotted = true;
            roomOf[i] = room;

            if (partner != -1) {
                s[partner].allotted = true;
                roomOf[partner] = room;
            }

            room++;
        }

        // Step 2: Check room limit
        if (room - 1 > 10) {
            System.out.println(
                "\nAllotment is not possible within 10 rooms "
                + "while maintaining same-gender rooms."
            );
            System.out.println(
                "Some students need separate rooms. "
                + "More rooms or a different arrangement is required."
            );
        }

        // Step 3: Display allotment
        System.out.println("\n===== HOSTEL ROOM ALLOTMENT =====");

        for (int r = 1; r < room; r++) {
            System.out.println("\nRoom " + r + ":");

            for (int i = 0; i < 20; i++) {
                if (roomOf[i] == r) {
                    System.out.println(
                        "Name: " + s[i].name
                        + " | Gender: " + s[i].gender
                        + " | Course: " + s[i].course
                    );
                }
            }
        }

        sc.close();
    }
}