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

        String name = util.standardlizeString(util.checkString("Enter subject's name: "));
        int total_lesson = util.checkChoice("Enter total lesson: ", 1, 200);
        int total_theory = util.checkChoice("Enter total theory lesson: ", 1, total_lesson);
        int cost = util.checkInterger("Enter cost per theory lesson: ");

        Subject subject = new Subject(id, name, total_lesson, total_theory, cost);
        return subject;
    }

    protected Teacher input_Teacher(int teacher_id) throws IOException {
        int id = teacher_id + 1;

        String name = util.standardlizeString(util.checkString("Enter teacher's name: "));
        String address = util.standardlizeString(util.checkString("Enter teacher's address: "));
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

        if (util.sum_lesson(teachs, teacher) >= 200) {
            System.out.println("You cant's teach more subject !");
            return;
        }

        int pos = 0;
        if (util.find_Teacher(teachs, teacher)) {
            for (int i = 0; i < teachs.length; i++) {
                if (teachs[i].getTeacher().equals(teacher)) {
                    pos = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < teachs.length; i++) {
                if (teachs[i].getTeacher().getId() == 0) {
                    pos = i;
                    teachs[pos].setTeacher(teacher);
                    break;
                }
            }
        }

        Subject sub = util.seacrh_subject(subjects);
        System.out.println(sub.toString());

        if (util.find_Subject(teachs[pos].getSubjects(), sub)) { //Teacher has been taught this subject
            for (int i = 0; i < teachs[pos].getSubjects().length; i++) {
                if (teachs[pos].getSubjects()[i].equals(sub)) {

                    System.out.printf("You have taught %d classes this subject !\n", teachs[pos].getClasses_num()[i]);
                    int num = 0;
                    do {
                        num = util.checkChoice("Enter number of class you want to change: ", 0, 3);
                        if (util.sum_lesson(teachs, teacher) + sub.getTotal_Lesson() * num <= 200) {
                            break;
                        }
                        System.out.println("Your lesson will be over exceed !!");
                    } while (true);
                    teachs[pos].getClasses_num()[i] = num;
                    break;
                }
            }
        } else { //Teacher has not taught this subject
            for (int i = 0; i < teachs[pos].getSubjects().length; i++) {
                if (teachs[pos].getSubjects()[i].getSubject_Id() == 0 && util.sum_lesson(teachs, teacher) + sub.getTotal_Lesson() < 200) {
                    int num = 0;

                    do {
                        num = util.checkChoice("Enter number of class: ", 0, 3);
                        if (util.sum_lesson(teachs, teacher) + sub.getTotal_Lesson() * num <= 200) {
                            break;
                        }
                        System.out.println("Your lesson will be over exceed !!");
                    } while (true);
                    teachs[pos].getSubjects()[i] = sub;
                    teachs[pos].getClasses_num()[i] = num;
                    break;

                }
            }
        }
    }

    protected void print_teachs(Teach_Manage[] teachs) {
        System.out.println("");
        for (int i = 0; i < teachs.length; i++) {
            if (teachs[i].getTeacher().getId() != 0) {
                System.out.printf("Teacher info:\n");
                util.print_Teacher(teachs[i].getTeacher());

                System.out.printf("%-20s| %-5s|\n", "Subject Name", "Number Class");
                for (int j = 0; j < teachs[i].getSubjects().length; j++) {
                    if (teachs[i].getSubjects()[j].getSubject_Id() != 0) {
                        System.out.printf("%-20s| %-12d|\n", teachs[i].getSubjects()[j].getSubject_NameString(),
                                teachs[i].getClasses_num()[j]);
                    }
                }

                System.out.println("Total lesson: " + util.sum_lesson(teachs, teachs[i].getTeacher()));
                System.out.println();
            }
        }
    }

    protected double sum_Salary(Teach_Manage teachs) {
        double sum = 0;
        for (int i = 0; i < teachs.getSubjects().length; i++) {
            sum += (teachs.getSubjects()[i].getTotal_Theory() * teachs.getSubjects()[i].getCost_Theory()
                    + // Total theory lesson 
                    (teachs.getSubjects()[i].getTotal_Lesson() - teachs.getSubjects()[i].getTotal_Theory()) * (0.7 * teachs.getSubjects()[i].getCost_Theory())) // Total pratice lesson
                    * teachs.getClasses_num()[i];
        }
        return sum;
    }

    protected void print_Salary(Teach_Manage[] teachs) {
        System.out.printf("%-20s| %-10s|\n", "Teacher Name", "Salary");
        for (int i = 0; i < teachs.length; i++) {
            if (teachs[i].getTeacher().getId() != 0) {
                System.out.printf("%-20s| %-9.2f$|\n", util.standardlizeString(teachs[i].getTeacher().getName()),
                        sum_Salary(teachs[i]));
            }
        }
    }

    protected void sort_by_name(Teach_Manage[] teachs) {
        for (int i = 0; i < teachs.length - 1; i++) {
            for (int j = i + 1; j < teachs.length; j++) {
                if (teachs[i].getTeacher().getName().compareTo(teachs[j].getTeacher().getName()) > 0) {
                    Teach_Manage temp = teachs[i];
                    teachs[i] = teachs[j];
                    teachs[j] = temp;
                }
            }
        }
    }

    protected void sort_by_lesson(Teach_Manage[] teachs) {
        for (int i = 0; i < teachs.length; i++) {
            if (teachs[i].getTeacher().getId() != 0) {
                for (int a = 0; a < teachs[i].getClasses_num().length - 1; a++) {
                    for (int b = a + 1; b < teachs[i].getClasses_num().length; b++) {
                        if (teachs[i].getClasses_num()[a] < teachs[i].getClasses_num()[b]) {
                            Subject temp = teachs[i].getSubjects()[a];
                            int tmp = teachs[i].getClasses_num()[a];

                            teachs[i].getSubjects()[a] = teachs[i].getSubjects()[b];
                            teachs[i].getClasses_num()[a] = teachs[i].getClasses_num()[b];

                            teachs[i].getSubjects()[b] = temp;
                            teachs[i].getClasses_num()[b] = tmp;
                        }
                    }
                }
            }
        }
    }
}
