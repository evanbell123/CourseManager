/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager;

/**
 *
 * @author Evan
 */
public class Course {
    private String id;
    private String title;
    private int credits;
    
    public Course(String id, String title, int credits) {
        this.id = id;
        this.title = title;
        this.credits = credits;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getCredits() {
        return this.credits;
    }
    
    @Override
    public String toString() {
        return this.id + " " + this.title;
    }
    
    /*
    public void setId(String id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    */
}
