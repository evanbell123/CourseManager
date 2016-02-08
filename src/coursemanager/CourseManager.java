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
public class CourseManager {

    private static final Department cs = new Department("CS", "Computer Science");
    private static final Department zo = new Department("ZO", "Zoology");
    private static final Department ch = new Department("CH", "Chemistry");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<String> errors = cs.add("404", "OOP", 3);
        if (errors.size() == 0) {
            System.out.println("Success");
        } else {
            displayErrors(errors);
        }
        
        errors = cs.add("403", "OOB", 3);
        if (errors.size() == 0) {
            System.out.println("Success");
        } else {
            displayErrors(errors);
        }
        
        displayCourses(cs.getCourses());
    }

    private static void displayErrors(LinkedList<String> errors) {
        ListIterator<String> errorIter = errors.listIterator();
        while (errorIter.hasNext()) {
            System.out.println(errorIter.next());
        }
    }
    
    private static void displayCourses(LinkedList<Course> courses) {
        Course temp;
        ListIterator<Course> courseIter = courses.listIterator();
        while (courseIter.hasNext()) {
            temp = courseIter.next();
            System.out.println("ID: " + temp.getId() + " Title: " + temp.getTitle() + " Credits: " + temp.getCredits());
        }
    }

}
