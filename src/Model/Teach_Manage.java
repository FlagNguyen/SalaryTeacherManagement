/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author asus
 */
public class Teach_Manage {

    private Teacher teacher;
    private Subject[] subjects;
    private int[] classes_num;

    public Teach_Manage(Teacher teacher, Subject[] subjects, int[] classes_num) {
        this.teacher = teacher;
        this.subjects = subjects;
        this.classes_num = classes_num;
    }

    public Teach_Manage() {
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public int[] getClasses_num() {
        return classes_num;
    }

    public void setClasses_num(int[] classes_num) {
        this.classes_num = classes_num;
    }

}


