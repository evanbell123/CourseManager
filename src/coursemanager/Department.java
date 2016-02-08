/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager;

import java.util.LinkedList;
import java.util.ListIterator;

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
    
    public LinkedList<Course> getCourses() {
        return this.courses;
    }

    /*
     Description: Add a new course to the department if user input is valid.
     Input: The id, title, and credits of the new course
     Output: A linked list of user input errors
     */
    public LinkedList<String> add(String id, String title, int credits) {

        LinkedList<String> errors = this.inputValidation(id, title, credits);
        
        if (errors.size() == 0) {
            courses.add(new Course(id, title, credits));
        }
        
        return errors;
        
    }
    
    private LinkedList<String> inputValidation(String id, String title, int credits) {
        LinkedList<String> errors = new LinkedList<>();
        if (id.length() > 0) {
            if (title.length() > 0) {
                if (credits > 0 && credits < 5) {
                    /*
                     Check for duplicate id and duplicate name
                     */
                    Course temp;
                    ListIterator<Course> courseIter = courses.listIterator();
                    while (courseIter.hasNext()) {
                        temp = courseIter.next();
                        if (temp.getId().equals(id)) {
                            errors.add("The course ID '" + id + "' already exists");
                        }
                        if (temp.getTitle().equals(title)) {
                            errors.add("The course name '" + title + "' is already taken");
                        }
                    }
                } else { //credits > 0 && credits < 5
                    errors.add("Credits must be greater than 0 and less than 5");
                }
            } else { //title.length() > 0
                errors.add("Please enter a name for this course");
            }
        } else { //id.length() > 0
            errors.add("Please enter an ID for this course");
        }
        return errors;
    }
}
