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
public class Subject {

    private int subject_Id;
    private String subject_NameString;
    private int total_Lesson;
    private int total_Theory;
    private int cost_Theory;

    public Subject() {
    }

    public Subject(int subject_Id, String subject_NameString, int total_Lesson, int total_Theory, int cost_Theory) {
        this.subject_Id = subject_Id;
        this.subject_NameString = subject_NameString;
        this.total_Lesson = total_Lesson;
        this.total_Theory = total_Theory;
        this.cost_Theory = cost_Theory;
    }

    public int getSubject_Id() {
        return subject_Id;
    }

    public void setSubject_Id(int subject_Id) {
        this.subject_Id = subject_Id;
    }

    public String getSubject_NameString() {
        return subject_NameString;
    }

    public void setSubject_NameString(String subject_NameString) {
        this.subject_NameString = subject_NameString;
    }

    public int getTotal_Lesson() {
        return total_Lesson;
    }

    public void setTotal_Lesson(int total_Lesson) {
        this.total_Lesson = total_Lesson;
    }

    public int getTotal_Theory() {
        return total_Theory;
    }

    public void setTotal_Theory(int total_Theory) {
        this.total_Theory = total_Theory;
    }

    public int getCost_Theory() {
        return cost_Theory;
    }

    public void setCost_Theory(int cost_Theory) {
        this.cost_Theory = cost_Theory;
    }

    @Override
    public String toString() {
        String out = String.format("%-5d| %-20s| %-12d| %-12d| %s%-12d| ", getSubject_Id(), getSubject_NameString(), getTotal_Lesson(), getTotal_Theory(), "$", getCost_Theory());
        return out;
    }

}
