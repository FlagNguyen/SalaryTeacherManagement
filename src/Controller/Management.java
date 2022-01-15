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
public class Management {

    Utility util = new Utility();

    protected Subject input_Subject(int subject_id) throws IOException {
        int id = subject_id + 1;

        String name = util.checkString("Enter subject's name: ");
        int total_lesson = util.checkChoice("Enter total lesson: ", 1, 200);
        int total_theory = util.checkChoice("Enter total theory lesson: ", 1, total_lesson);
        int cost = util.checkInterger("Enter cost per theory lesson: ");

        Subject subject = new Subject(id, name, total_lesson, total_theory, cost);
        return subject;
    }

    protected Teacher input_Teacher(int teacher_id) throws IOException {
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

    protected void print_Subjects(Subject[] subjects) {
        System.out.printf("%-5s| %-20s| %-10s| %-10s| %-10s|\n", "ID", "Subject's Name", "Total Lesson", "Total Theory", "Cost/T.Lesson");
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].getSubject_Id() != 0) {
                System.out.println(subjects[i].toString());
            }
        }
    }

    protected void print_Teachers(Teacher[] teachers) {
        System.out.printf("%-5s| %-20s| %-10s| %-13s| %-20s|\n", "ID", "Teacher Name", "Address", "Contact", "Level");
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i].getId() != 0) {
                util.print_Teacher(teachers[i]);
            }
        }
    }

    protected void teach(Teacher[] teachers, Subject[] subjects, Teach_Manage[] teachs) {

        //S1. Enter teacher id and search in Teacher[]
        Teacher teacher = util.search_Teacher(teachers);
        System.out.println("This's your information: ");
        util.print_Teacher(teacher);
        Subject subject = null;

        for (int i = 0; i < teachs.length; i++) {
            if (teachs[i].getTeacher().getId() == teacher.getId()) { //teachs[i] is your teacher information of class
                if (util.sum_lesson(teachs, teacher) >= 200) {
                    System.out.println("You can't teach more subject !");
                } else { // Nếu vẫn nhận được lớp tiếp
                    subject = util.seacrh_subject(subjects);
                    for (int j = 0; j < teachs[i].getSubjects().length; j++) { //Vòng lăp các môn học để nhận
                        if (teachs[i].getSubjects()[j].equals(subject)) { //Đã nhận dạy môn này rồi
                            if (teachs[i].getClasses_num()[j] == 3) {// Nếu đã nhận dạy đủ 3 lớp
                                System.out.println("You can't take more classes in this subject");
                                break;
                            } else if (teachs[i].getClasses_num()[j] < 3) {
                                int classes = util.checkChoice("Enter number of class: ", 1, 3 - teachs[i].getClasses_num()[j]);
                                teachs[i].getClasses_num()[j] += classes;
                                break;
                            }
                        } else if (teachs[i].getSubjects()[j].getSubject_Id() != 0) {//Nếu chưa nhận môn này
                            teachs[i].getSubjects()[j] = subject;
                            int num = 0;

                            do {
                                num = util.checkChoice("Enter number of class", 0, 3);

                                if (util.sum_lesson(teachs, teacher) + subject.getTotal_Lesson() * num <= 200) {
                                    break;
                                }
                                System.out.println("Your lesson will be over exceed !!");
                            } while (true);
                            teachs[i].getClasses_num()[j] = num;
                            break;
                        }
                    }
                }
            }
        }
        for (int k = 0; k < teachs.length; k++) {
            if (teachs[k].getTeacher().getId() != 0) {
                subject = util.seacrh_subject(subjects);
                for (int l = 0; l < teachs[k].getSubjects().length; l++) {
                    if (teachs[k].getSubjects()[l].getSubject_Id() != 0) {
                        teachs[k].getSubjects()[l] = subject;
                        int num = 0;
                        do {
                            num = util.checkChoice("Enter number of class", 0, 3);

                            if (util.sum_lesson(teachs, teacher) + subject.getTotal_Lesson() * num <= 200) {
                                break;
                            }
                            System.out.println("Your lesson will be over exceed !!");
                        } while (true);
                        teachs[k].getClasses_num()[l] = num;
                    }
                }
            }
        }
    }

    protected void print_teachs(Teach_Manage[] teachs) {
        for (int i = 0; i < teachs.length; i++) {
            if (teachs[i].getTeacher().getId() != 0) {
                System.out.printf("Teacher %d", i + 1);
                util.print_Teacher(teachs[i].getTeacher());

                System.out.printf("%-20s| %-5s|", "Subject Name", "Number Class");
                for (int j = 0; j < teachs[i].getSubjects().length; j++) {
                    if (teachs[i].getSubjects()[j].getSubject_Id() != 0) {
                        System.out.printf("%-20s| %-5d|", teachs[i].getSubjects()[j].getSubject_NameString(),
                                teachs[i].getClasses_num()[j]);
                    }
                }

            }
        }
    }

}
