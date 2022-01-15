/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Subject;
import Model.Teach_Manage;
import Model.Teacher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author asus
 */
public class Utility {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public int checkChoice(String mess, int a, int b) {
        int output = 0;
        try {
            do {
                System.out.print(mess);
                output = Integer.parseInt(in.readLine());
                if (output >= a && output <= b) {
                    break;
                }
                System.err.printf("\nPlease enter %d-%d !!", a, b);

            } while (true);
        } catch (Exception e) {
            System.err.println("Please enter integer !!!");
        }

        return output;
    }

    public String checkString(String mess) throws IOException {
        String out = "";
        try {
            do {
                System.out.print(mess);
                out = in.readLine();
                if (out.isEmpty()) {
                    System.err.println("Please Enter String");
                } else {
                    break;
                }
            } while (true);
        } catch (Exception e) {
        }

        return out.trim();
    }

    public int checkInterger(String mess) {
        int output = 0;
        do {
            try {
                System.out.print(mess);
                output = Integer.parseInt(in.readLine());
                break;
            } catch (Exception e) {
                System.err.println("Please Enter Interger");
            }

        } while (true);

        return output;
    }

    protected String standardlizeString(String s) {
        String strOut = "";
        String st = "";
        st = s.trim().toLowerCase().replaceAll("\\s+", " ");

        String[] temp = new String[100];
        temp = st.split(" ");
        for (int i = 0; i <= temp.length - 1; i++) {
            strOut += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                strOut += " ";
            }
        }
        return strOut;
    }

    protected void print_Teacher(Teacher teacher) {
        String name = standardlizeString(teacher.getName());
        String address = standardlizeString(teacher.getAddress());

        String level = "";
        switch (teacher.getLevel()) {
            case 1:
                level = "Prof. Dr";
                break;
            case 2:
                level = "Assoc Professor Ph.D";
                break;
            case 3:
                level = "Main Teacher";
                break;
            case 4:
                level = "Masters";
                break;
        }

        System.out.printf("%-5d| %-20s| %-10s| %-13s| %-20s|\n", teacher.getId(), name, address, teacher.getPhone(), level);

    }

    protected Teacher search_Teacher(Teacher[] teachers) {
        int id;
        do {
            id = checkInterger("Enter your ID Teacher: ");

            if (id != 0) {
                for (int i = 0; i < teachers.length; i++) {
                    if (id == teachers[i].getId()) {
                        return teachers[i];
                    }
                }
                System.err.println("Your ID is not exist in system !!!");
            } else {
                System.err.println("Your Id can't be 0 !!!");
            }

        } while (true);
    }

    protected int sum_lesson(Teach_Manage[] teachs, Teacher teacher) {
        int sum = 0;
        for (int i = 0; i < teachs.length; i++) {
            if (teachs[i].getTeacher().equals(teacher)) {
                for (int j = 0; j < teachs[i].getSubjects().length; j++) {
                    sum += teachs[i].getSubjects()[j].getTotal_Lesson();
                }
            }
        }
        return sum;
    }

    protected Subject seacrh_subject(Subject[] subjects) {
        int id;
        do {
            id = checkInterger("Enter ID Subject: ");

            if (id != 0) {
                for (int i = 0; i < subjects.length; i++) {
                    if (id == subjects[i].getSubject_Id()) {
                        return subjects[i];
                    }
                }
                System.err.println("This subject is not exist in system !!!");
            } else {
                System.err.println("Subject's id can't be 0 !!!");
            }

        } while (true);
    }
}
