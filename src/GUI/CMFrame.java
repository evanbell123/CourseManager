/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import coursemanager.Controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;
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
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Evan
 */
public class CMFrame extends JFrame {

    private static final DefaultListModel courseListModel = new DefaultListModel();

    private final JLabel codeLabel = new JLabel("Course Code:");
    private final JLabel nameLabel = new JLabel("Course Name:");
    private final JLabel creditsLabel = new JLabel("No. Of Credits:");
    private final JLabel deptLabel = new JLabel("Department:");

    private final JTextField codeTextField = new JTextField();
    private final JTextField titleTextField = new JTextField();
    private final JTextField creditsTextField = new JTextField();

    private JComboBox deptComboBox;

    private final JButton addCourseButton = new JButton("Add Course");
    private final JButton editCourseButton = new JButton("Edit Course");
    private final JButton deleteCourseButton = new JButton("Delete Course");
    private final JButton displayAllButton = new JButton("Display All");
    private final JButton displayDeptButton = new JButton("Display (dept)");

    private JList courseList;

    private List<String> courseValues; // Used to keep track of selected course values

    private final Controller controller;

    public CMFrame(Controller controller) {

        this.controller = controller;
        deptComboBox = new JComboBox(controller.getDepartmentIDs().toArray());

        courseList = new JList(courseListModel);

        ListSelectionListener courseSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {

                    System.out.println(courseValues);

                    courseValues = controller.parseCourseString(courseList.getSelectedValue().toString());

                    System.out.println(courseValues);

                    deptComboBox.setSelectedItem(courseValues.get(0));
                    codeTextField.setText(courseValues.get(1));
                    titleTextField.setText(courseValues.get(2));
                    creditsTextField.setText(controller.getCredits(courseValues.get(0), courseValues.get(1)));
                }
            }

        };

        courseList.addListSelectionListener(courseSelectionListener);

        updateCourseList(controller.getCourses());

        //setDeptComboBox();
        prepareGUI();

        displayDeptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deptID = (String) deptComboBox.getSelectedItem();
                updateCourseList(controller.filterByDept(deptID));
            }
        }
        );

        displayAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourseList(controller.getCourses());
            }
        }
        );

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> errors = controller.addCourse((String) deptComboBox.getSelectedItem(), codeTextField.getText(), titleTextField.getText(), creditsTextField.getText());

                if (errors.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Course Added");
                } else {
                    displayErrors(errors);
                }

                updateCourseList(controller.getCourses());
            }
        });

        editCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (courseValues != null) {
                    List<String> errors = controller.editCourse(
                            courseValues.get(0),
                            (String) deptComboBox.getSelectedItem(),
                            courseValues.get(1),
                            codeTextField.getText(),
                            courseValues.get(2),
                            titleTextField.getText(),
                            creditsTextField.getText());

                    if (!errors.isEmpty()) {
                        displayErrors(errors);
                    } else {
                        updateCourseList(controller.getCourses());
                        JOptionPane.showMessageDialog(null, "Course Edited");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please select a course to edit");
                }

            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> errors = controller.deleteCourse((String) deptComboBox.getSelectedItem(), codeTextField.getText());

                if (errors.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Course Removed");
                } else {
                    displayErrors(errors);
                }

                updateCourseList(controller.getCourses());
            }
        });
    }

    private void updateCourseList(List<String> courses) {

        courseList.clearSelection();
        codeTextField.setText("");
        titleTextField.setText("");
        creditsTextField.setText("");
        courseListModel.removeAllElements();

        ListIterator<String> string = courses.listIterator();
        while (string.hasNext()) {
            courseListModel.addElement(string.next());
        }
    }

    private static void displayErrors(List<String> errors) {
        String errorMessage = "";
        ListIterator<String> errorIter = errors.listIterator();
        while (errorIter.hasNext()) {
            errorMessage += errorIter.next() + "\n";
        }

        JOptionPane.showMessageDialog(null, errorMessage);
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
                                        .addComponent(addCourseButton)
                                        .addComponent(editCourseButton)
                                        .addComponent(deleteCourseButton))
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(codeTextField)
                                        .addComponent(titleTextField)
                                        .addComponent(creditsTextField)
                                        .addComponent(deptComboBox))))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(courseList)
                        .addComponent(displayAllButton)
                        .addComponent(displayDeptButton))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, codeLabel, nameLabel, creditsLabel, deptLabel, addCourseButton, editCourseButton, deleteCourseButton, displayAllButton, displayDeptButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeTextField)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(courseList)))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(titleTextField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(creditsLabel)
                        .addComponent(creditsTextField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(deptLabel)
                        .addComponent(deptComboBox))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addCourseButton)
                                .addComponent(editCourseButton)
                                .addComponent(deleteCourseButton))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(displayAllButton)
                                .addComponent(displayDeptButton)))
        );

        //layout.linkSize(SwingConstants.HORIZONTAL, codeLabel);
        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
