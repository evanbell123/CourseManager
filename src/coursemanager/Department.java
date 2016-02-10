/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager;

import java.util.LinkedList;

/**
 *
 * @author Evan
 */
public class Department {

    private final String id;
    private final String name;
    private final LinkedList<Course> courses;

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new LinkedList<>();
    }

    public String getId() {
        return this.id;
    }

    public LinkedList<Course> getCourses() {
        return this.courses;
    }

    public void add(String id, String title, int credits) {
        courses.add(new Course(id, title, credits));

    }

}
