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
public class Teacher extends Person {

    private int id;
    private int level;

    public Teacher(int id, String name, String address, String phone, int level) {
        super(name, address, phone);
        this.id = id;
        this.level = level;
    }

    public Teacher(int id, int level) {
        this.id = id;
        this.level = level;
    }

    public Teacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    
    
    
}
