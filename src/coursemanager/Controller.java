/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 *
 * @author evanb
 */
public class Controller {

    private static final List<Department> departments = new ArrayList<>();

    public Controller() {
        setDepartments();
    }

    private void setDepartments() {

        Department cs = new Department("CS", "Computer Science");
        Department zo = new Department("ZO", "Zoology");
        Department ch = new Department("CH", "Chemistry");

        cs.addCourse("404", "Object-Oriented Programming", 3);
        cs.addCourse("403", "Algorithms and Complexities", 3);

        zo.addCourse("404", "Animals", 3);
        zo.addCourse("403", "Plants", 3);

        ch.addCourse("404", "Atoms", 3);
        ch.addCourse("403", "Chemicals", 3);

        departments.add(cs);
        departments.add(zo);
        departments.add(ch);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<String> getCourses() {

        List<String> courseStringList = new ArrayList<>();

        ListIterator<Department> deptIter = departments.listIterator();

        Department dept;
        while (deptIter.hasNext()) {
            dept = deptIter.next();
            for (Course course : dept.getCourses()) {
                courseStringList.add(dept.getId() + course.toString());
            }
        }

        return courseStringList;

    }

    public List<String> getDepartmentIDs() {
        List<String> deptIDs = new ArrayList<>();

        ListIterator<Department> deptIter = departments.listIterator();
        while (deptIter.hasNext()) {
            deptIDs.add(deptIter.next().getId());
        }

        return deptIDs;

    }

    private Department findDeptByID(String deptID) {
        ListIterator<Department> deptIter = departments.listIterator();
        Department temp;
        while (deptIter.hasNext()) {
            temp = deptIter.next();
            if (temp.getId().equals(deptID)) {
                return temp;
            }
        }

        return null;
    }

    public String getCredits(String deptID, String courseID) {
        ListIterator<Department> deptIter = departments.listIterator();
        Department temp;
        while (deptIter.hasNext()) {
            temp = deptIter.next();
            if (temp.getId().equals(deptID)) {
                return Integer.toString(temp.findCourseByID(courseID).getCredits());
            }
        }

        return null;
    }

    public List<String> addCourse(String deptID, String courseID, String title, String credits) {

        List<String> errors = addCourseInputValidation(deptID, courseID, title, credits);

        if (errors.isEmpty()) {
            findDeptByID(deptID).addCourse(courseID, title, Integer.parseInt(credits));
        }

        return errors;
    }

    public List<String> editCourse(String oldDeptID, String newDeptID, String oldCourseID, String newCourseID, String oldTitle, String newTitle, String credits) {

        List<String> errors = editCourseInputValidation(oldDeptID, newDeptID, oldCourseID, newCourseID, oldTitle, newTitle, credits);

        if (errors.isEmpty()) {
            //findDeptByID(deptID).addCourse(courseID, title, Integer.parseInt(credits));
            if (oldDeptID.equals(newDeptID)) {
                findDeptByID(oldDeptID).editCourse(oldCourseID, newCourseID, newTitle, credits);
            }
        }

        return errors;
    }

    private List<String> generalInputValidation(String id, String title, String credits) {
        List<String> errors = new ArrayList<>();

        if (id.isEmpty()) {
            errors.add("Please enter an ID for this course");
        }

        if (title.isEmpty()) {
            errors.add("Please enter a title for this course");
        }

        if (credits.isEmpty()) {
            errors.add("Please enter the number of credit hours for this course");
        } else if (!credits.matches("^-?\\d+$")) { // if credits is not an integer value
            errors.add("Credits must be an integer value");
        } else {
            int intCredits = Integer.parseInt(credits);
            if (intCredits > 0 && intCredits < 5) {

            } else { //credits > 0 && credits < 5
                errors.add("Credits must be greater than 0 and less than 5");
            }
        }

        return errors;
    }

    /*
    http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
     */
    private List<String> addCourseInputValidation(String deptID, String courseID, String title, String credits) {
        List<String> errors = generalInputValidation(courseID, title, credits);

        if (!courseID.isEmpty() && !title.isEmpty()) {
            /*
                     Check for duplicate id and/or duplicate name
             */
            ListIterator<Department> deptIter = departments.listIterator();

            Department dept;
            while (deptIter.hasNext()) {
                dept = deptIter.next();
                for (Course course : dept.getCourses()) {
                    /*
                    check for duplicate course id within department
                     */
                    if (dept.getId().equals(deptID)) {
                        if (course.getId().equals(courseID)) {
                            errors.add("The course ID '" + courseID + "' already exists");
                        }
                    }

                    /*
                    check for duplicate course title within all courses
                     */
                    if (course.getTitle().equals(title)) {
                        errors.add("The course name '" + title + "' is already taken");
                    }
                }
            }
        }

        return errors;
    }

    private List<String> editCourseInputValidation(String oldDeptID, String newDeptID, String oldCourseID, String newCourseID, String oldTitle, String newTitle, String credits) {

        List<String> errors = generalInputValidation(newCourseID, newTitle, credits);

        if (!newCourseID.isEmpty() && !newTitle.isEmpty()) {
            /*
                     Check for duplicate id and/or duplicate name
             */

            if (!oldDeptID.equals(newDeptID)) {
                /*
                     Check for duplicate id and/or duplicate name
                 */
                ListIterator<Department> deptIter = departments.listIterator();

                Department dept;
                while (deptIter.hasNext()) {
                    dept = deptIter.next();
                    for (Course course : dept.getCourses()) {
                        /*
                    check for duplicate course id within department
                         */
                        if (dept.getId().equals(deptID)) {
                            if (course.getId().equals(courseID)) {
                                errors.add("The course ID '" + courseID + "' already exists");
                            }
                        }

                        /*
                    check for duplicate course title within all courses
                         */
                        if (course.getTitle().equals(title)) {
                            errors.add("The course name '" + title + "' is already taken");
                        }
                    }
                }
            }

            ListIterator<Department> deptIter = departments.listIterator();

            Department dept;
        }

        return errors;
    }

    public List<String> filterByDept(String DeptID) {
        //System.out.println(departments.stream().filter(p -> p.getId().equals(DeptID)).collect(Collectors.toList()).get(0).getCourses().toString());
        Department dept = departments.stream().filter(p -> p.getId().equals(DeptID)).collect(Collectors.toList()).get(0);
        List<String> courseStringList = new ArrayList<>();
        dept.getCourses().stream().forEach((course) -> {
            courseStringList.add(dept.getId() + course.toString());
        });

        return courseStringList;
        //courseList = new JList(filteredDept.toArray());
    }

    public List<String> parseCourseString(String courseString) {

        //This string contains the depart id and course id
        String ids = courseString.substring(0, courseString.indexOf(' '));
        String courseTitle = courseString.substring(courseString.indexOf(' ') + 1);
        //This string contains the course title

        List<String> courseListString = new ArrayList<>();
        courseListString.add(ids.substring(0, 2)); //deptID
        courseListString.add(ids.substring(2)); //courseID
        courseListString.add(courseTitle); //courseTitle
        return courseListString;
    }
}
