/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Evan
 */
public class Department {

    private final String id;
    private final String name;
    private final List<Course> courses;

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void addCourse(String id, String title, int credits) {
        courses.add(new Course(id, title, credits));
    }
    
    public void editCourse(String oldID, String newID, String title, String credits) {
        Course temp = findCourseByID(oldID);
        temp.edit(newID, title, Integer.parseInt(credits));
    }
    
    public void deleteCourse(String id) {
        courses.remove(findCourseByID(id));
    }
    
    public Course findCourseByID(String courseID) {
        
        
        ListIterator<Course> courseIter = courses.listIterator();
        Course temp;
        while (courseIter.hasNext()) {
            temp = courseIter.next();
            if (temp.getId().equals(courseID)) {
                
                return temp;
            }
        }
        
        return null;
    }
}
