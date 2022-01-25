/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Subject;
import Model.Teach_Manage;
import Model.Teacher;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Management mn = new Management();
        Utility util = new Utility();

        Subject[] subjects = new Subject[100];
        Teacher[] teachers = new Teacher[100];
        Teach_Manage[] teach_Manages = new Teach_Manage[teachers.length];

        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = new Subject(0, " ", 0, 0, 0);
        }

        for (int i = 0; i < teachers.length; i++) {
            teachers[i] = new Teacher(0, " ", " ", " ", 3);
        }

        for (int i = 0; i < teach_Manages.length; i++) {
            Subject[] sub = new Subject[100];
            for (int j = 0; j < sub.length; j++) {
                sub[j] = new Subject(0, " ", 0, 0, 0);
            }
            int[] classes = new int [100];
            for (int j = 0;j<classes.length;j++){
                classes[j] = 0;
            }
            Teacher teacher = new Teacher(0, " ", " ", " ", 3);
            teach_Manages[i] = new Teach_Manage(teacher, sub, classes);
        }

        //Test data:
        subjects[0] = new Subject(101, "Mathematics", 70, 50, 10);
        subjects[1] = new Subject(102, "OOP Java", 60, 40, 30);
        subjects[2] = new Subject(103, "Avanced Mathematics", 70, 30, 20);
        subjects[3] = new Subject(104, "Advanced Algorithms", 50, 20, 35);

        teachers[0] = new Teacher(101, "TrUOng    BUI", "Ha Noi", "0986787866", 4);
        teachers[1] = new Teacher(102, "Phan Truong Lam", "Nghe An", "08644789", 2);
        teachers[2] = new Teacher(103, "Hoai Thu", "Thanh Hoa", "097543237", 3);
                

            
        while (true) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("1.  Enter new subject");
            System.out.println("2.  Enter new teacher");
            System.out.println("3.  Register new job");
            System.out.println("4.  Sort teacher management");
            System.out.println("5.  Show salary");
            System.out.println("6.  Exit");
            int choice = util.checkChoice("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    for (int i = 0; i < subjects.length; i++) {
                        if (i == 0) {
                            if (subjects[i].getSubject_Id() == 0) {
                                subjects[0] = mn.input_Subject(101);
                                System.out.println("Input Subject Succesfully !!!");
                                break;
                            }
                        } else {
                            if (subjects[i].getSubject_Id() == 0) {
                                subjects[i] = mn.input_Subject(subjects[i - 1].getSubject_Id());
                                System.out.println("Input Subject Successfully !!!");
                                break;
                            }
                        }
                    }

                    mn.print_Subjects(subjects);

                    break;
                case 2:
                    for (int i = 0; i < teachers.length; i++) {
                        if (i == 0) {
                            if (teachers[i].getId() == 0) {
                                teachers[0] = mn.input_Teacher(101);
                                System.out.println("Input Teacher Susccessfully !!!");
                                break;
                            }
                        } else {
                            if (teachers[i].getId() == 0) {
                                teachers[i] = mn.input_Teacher(teachers[i - 1].getId());
                                System.out.println("Input Teacher Susccessfully !!!");
                                break;
                            }
                        }
                    }
                    
                    mn.print_Teachers(teachers);                    
                    break;
                case 3:
                    mn.teach(teachers, subjects, teach_Manages);
                    mn.print_teachs(teach_Manages);
                    break;
                case 4:
                    System.out.println("1.  Sort by name");
                    System.out.println("2.  Sort by lesson");
                    switch (util.checkChoice("Enter your choice: ", 1, 2)){
                        case 1:
                            mn.sort_by_name(teach_Manages);
                            break;
                        case 2:
                            mn.sort_by_lesson(teach_Manages);
                    }
                    mn.print_teachs(teach_Manages);
                    break;
                case 5:
                    mn.print_Salary(teach_Manages);
                    break;
                case 6:
                    return;
                default:
                    return;
            }
        }

    }

}
