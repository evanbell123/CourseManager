/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import coursemanager.Course;
import coursemanager.Department;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author Evan
 */
public class Main extends JFrame {

    private static final List<Department> departments = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();

    private static final DefaultComboBoxModel deptIDs = new DefaultComboBoxModel();
    private static final DefaultListModel courseInfo = new DefaultListModel();

    //private static final List<Course> courses = new ArrayList<>();
    private final JLabel codeLabel = new JLabel("Course Code:");
    private final JLabel nameLabel = new JLabel("Course Name:");
    private final JLabel creditsLabel = new JLabel("No. Of Credits:");
    private final JLabel deptLabel = new JLabel("Department:");

    private final JTextField codeTextField = new JTextField();
    private final JTextField nameTextField = new JTextField();
    private final JTextField creditsTextField = new JTextField();

    private JComboBox deptComboBox;

    private final JButton addCourseButton = new JButton("Add Course");
    private final JButton displayAllButton = new JButton("Display All");
    private final JButton displayDeptButton = new JButton("Display (dept)");

    private JList courseList;

    public Main() {

        setDepartments();

        setCourses();

        displayAll();

        setDeptComboBox();

        setCourses();

        prepareGUI();

        displayDeptButton.addActionListener((ActionEvent e) -> {
            //JOptionPane.showMessageDialog(null, "Display Department Button");
            filterByDepartment((String) deptComboBox.getSelectedItem());
        });

        displayAllButton.addActionListener((ActionEvent e) -> {
            displayAll();
        });

        addCourseButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Add Course Button");
        });
    }

    private void setDepartments() {
        LinkedList<String> errors = new LinkedList<>();

        Department cs = new Department("CS", "Computer Science");
        errors = cs.add("404", "Object-Oriented Programming", 3);
        errors = cs.add("403", "Algorithms and Complexities", 3);

        Department zo = new Department("ZO", "Zoology");
        errors = zo.add("404", "Animals", 3);
        errors = zo.add("403", "Plants", 3);

        Department ch = new Department("CH", "Chemistry");
        errors = ch.add("404", "Atoms", 3);
        errors = ch.add("403", "Chemicals", 3);

        departments.add(cs);
        departments.add(zo);
        departments.add(ch);
    }

    private void setCourses() {

        ListIterator<Department> deptIter = departments.listIterator();
        while (deptIter.hasNext()) {
            courses.addAll(deptIter.next().getCourses());
        }
    }

    private void displayAll() {
        courseInfo.removeAllElements();

        ListIterator<Course> courseIter = courses.listIterator();
        while (courseIter.hasNext()) {
            courseInfo.addElement(courseIter.next().toString());
        }

        courseList = new JList(courseInfo);
    }

    private void displayDept(Department dept) {
        courseInfo.removeAllElements();

        ListIterator<Course> courseIter = dept.getCourses().listIterator();
        while (courseIter.hasNext()) {
            courseInfo.addElement(courseIter.next().toString());
        }

        courseList = new JList(courseInfo);
    }

    private void setDeptComboBox() {

        ListIterator<Department> deptIter = departments.listIterator();
        while (deptIter.hasNext()) {
            deptIDs.addElement(deptIter.next().getId());
        }

        deptComboBox = new JComboBox(deptIDs);
    }

    private void filterByDepartment(String DeptID) {
        List<Department> filteredDept = departments.stream().filter(p -> p.getId().equals(DeptID)).collect(Collectors.toList());

        displayDept(filteredDept.get(0));

        //courseList = new JList(filteredDept.toArray());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        "javax.swing.plaf.metal.MetalLookAndFeel");
                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                //UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            new Main().setVisible(true);
        });

        //layout.setVisible(true);
        //layout.setSize(400,400);
    }

    private void prepareGUI() {

        courseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); //how many cells can be selected
        courseList.setLayoutOrientation(JList.HORIZONTAL_WRAP); //see the figure above

        JScrollPane listScroller = new JScrollPane(courseList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(codeLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(creditsLabel)
                                        .addComponent(deptLabel)
                                        .addComponent(addCourseButton))
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(codeTextField)
                                        .addComponent(nameTextField)
                                        .addComponent(creditsTextField)
                                        .addComponent(deptComboBox))))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(courseList)
                        .addComponent(displayAllButton)
                        .addComponent(displayDeptButton))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, addCourseButton, displayAllButton, displayDeptButton, courseList);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeTextField)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(courseList)))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(creditsLabel)
                        .addComponent(creditsTextField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(deptLabel)
                        .addComponent(deptComboBox))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(addCourseButton)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(displayAllButton)
                                .addComponent(displayDeptButton)))
        );

        //layout.linkSize(SwingConstants.HORIZONTAL, codeLabel);
        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static void displayErrors(LinkedList<String> errors) {
        ListIterator<String> errorIter = errors.listIterator();
        while (errorIter.hasNext()) {
            System.out.println(errorIter.next());
        }
    }

}
