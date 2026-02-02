package com.example.BTL2;

import com.example.BTL2.exception.StudentNotFoundException;
import com.example.BTL2.model.Course;
import com.example.BTL2.model.FullTimeStudent;
import com.example.BTL2.model.PartTimeStudent;
import com.example.BTL2.model.Student;
import com.example.BTL2.server.ScoreInputTask;
import com.example.BTL2.server.StudentManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Khởi tạo dữ liệu mẫu");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Thêm khóa học");
            System.out.println("4. Đăng ký khóa học cho sinh viên");
            System.out.println("5. Nhập điểm cho sinh viên");
            System.out.println("6. Xem bảng điểm của 1 sinh viên");
            System.out.println("7. Tìm kiếm sinh viên");
            System.out.println("8. Lọc & sắp xếp sinh viên");
            System.out.println("9. Tính học phí sinh viên");
            System.out.println("10. Nhập điểm tự động bằng đa luồng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1 -> sm.initDataClass();

                    case 2 -> {
                        System.out.print("ID: ");
                        String id = sc.nextLine();
                        System.out.print("Tên: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("GPA: ");
                        double gpa = Double.parseDouble(sc.nextLine());
                        System.out.print("1.FullTime  2.PartTime: ");
                        int type = Integer.parseInt(sc.nextLine());

                        Student s = (type == 1)
                                ? new FullTimeStudent(id, name, email, gpa)
                                : new PartTimeStudent(id, name, email, gpa);

                        sm.addStudent(s);
                        System.out.println("Thêm sinh viên thành công");
                    }

                    case 3 -> {
                        System.out.print("Course ID: ");
                        String cid = sc.nextLine();
                        System.out.print("Tên khóa: ");
                        String cname = sc.nextLine();
                        System.out.print("Credits: ");
                        int cr = Integer.parseInt(sc.nextLine());

                        sm.addCourse(new Course(cid, cname, cr));
                        System.out.println("Thêm khóa học thành công");
                    }

                    case 4 -> {
                        System.out.print("Student ID: ");
                        String sid = sc.nextLine();
                        System.out.print("Course ID: ");
                        String cid = sc.nextLine();
                        sm.enrollStudentToCourse(sid, cid);
                        System.out.println("Đăng ký thành công");
                    }

                    case 5 -> {
                        System.out.print("Student ID: ");
                        String sid = sc.nextLine();
                        System.out.print("Course ID: ");
                        String cid = sc.nextLine();
                        System.out.print("Score: ");
                        double score = Double.parseDouble(sc.nextLine());
                        sm.inputScore(sid, cid, score);
                        System.out.println(" Nhập điểm thành công");
                    }

                    case 6 -> {
                        System.out.print("Student ID: ");
                        sm.printStudentScoreBoard(sc.nextLine());
                    }

                    case 7 -> {
                        System.out.print("Tên cần tìm: ");
                        String name = sc.nextLine();
                        System.out.print("GPA tối thiểu: ");
                        double gpa = Double.parseDouble(sc.nextLine());
                        sm.searchStudent(name, gpa)
                                .forEach(s -> System.out.println(s.getId() + " - " + s.getName()));
                    }

                    case 8 -> {
                        System.out.println("1. Lọc GPA > 8");
                        System.out.println("2. Sort GPA giảm dần");
                        System.out.println("3. Sort tên A-Z");
                        int opt = Integer.parseInt(sc.nextLine());

                        List<Student> list = switch (opt) {
                            case 1 -> sm.filterStudents(s -> s.getGpa() > 8);
                            case 2 -> sm.sortStudentsByGpaDesc();
                            case 3 -> sm.sortStudentsByNameAsc();
                            default -> List.of();
                        };

                        list.forEach(s ->
                                System.out.println(s.getId() + " | " + s.getName() + " | " + s.getGpa()));
                    }

                    case 9 -> {
                        System.out.print("Student ID: ");
                        Student s = sm.searchStudent(sc.nextLine())
                                .orElseThrow(() -> new StudentNotFoundException("Không tồn tại"));
                        System.out.println(" Học phí: " + sm.calculateTuition(s));
                    }

                    case 10 -> {
                        Thread t = new Thread(new ScoreInputTask(
                                sm.getAllStudentIds(),
                                sm.getAllCourseIds(),
                                sm
                        ));
                        t.start();
                        t.join();
                        System.out.println("Nhập điểm đa luồng xong");
                    }
                }
            } catch (Exception e) {
                System.out.println(" Lỗi: " + e.getMessage());
            }

        } while (choice != 0);

        System.out.println("Thoát chương trình");
    }
}

