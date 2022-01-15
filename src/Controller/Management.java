/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Subject;
import Model.Teacher;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class Management {

    Utility util = new Utility();

    Subject input_Subject(int subject_id) throws IOException {
        int id = subject_id + 1;

        String name = util.checkString("Enter subject's name: ");
        int total_lesson = util.checkChoice("Enter total lesson: ", 1, 200);
        int total_theory = util.checkChoice("Enter total theory lesson: ", 1, total_lesson);
        int cost = util.checkInterger("Enter cost per theory lesson: ");

        Subject subject = new Subject(id, name, total_lesson, total_theory, cost);
        return subject;
    }

    Teacher input_Teacher(int teacher_id) throws IOException {
        int id = teacher_id + 1;

        String name = util.checkString("Enter teacher's name: ");
        String address = util.checkString("Enter teacher's address: ");
        String phone = "";

        do {
            phone = util.checkString("Enter teacher's phone: ");
            if (phone.matches("[1234567890]+") && phone.length() >= 5 && phone.length() <= 10) {
                break;
            } else {
                System.err.println("Phone number can't has a letter and must has the length is 5 ! Please re-enter !");
            }
        } while (true);

        System.out.println("Teacher's level: "
                + "\n1. Prof. Dr"
                + "\n2. Assoc Prof Ph.D"
                + "\n3. Main Teacher"
                + "\n4. Master");
        int level = util.checkChoice("Enter teacher's level (1-4): ", 1, 4);

        Teacher teacher = new Teacher(id, name, address, phone, level);
        return teacher;
    }

    void print_Subjects(Subject[] subjects) {
        System.out.printf("%-5s| %-20s| %-10s| %-10s| %-10s|\n", "ID", "Subject's Name", "Total Lesson", "Total Theory", "Cost/T.Lesson");
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].getSubject_Id() != 0) {
                System.out.println(subjects[i].toString());
            }
        }
    }

    void print_Teachers(Teacher[] teachers) {
        System.out.printf("%-5s| %-20s| %-10s| %-13s| %-20s|\n","ID","Teacher Name","Address","Contact","Level");
        for (int i =0;i<teachers.length;i++){
            if(teachers[i].getId()!=0){
                util.print_Teacher(teachers[i]);
            }
        }
    }

}
