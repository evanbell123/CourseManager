/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author Evan
 */
public class Main extends JFrame {

    public Main() {

        JLabel codeLabel = new JLabel("Course Code:");
        JLabel nameLabel = new JLabel("Course Name:");
        JLabel creditsLabel = new JLabel("No. Of Credits:");
        JLabel deptLabel = new JLabel("Department:");

        JTextField codeTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        JTextField creditsTextField = new JTextField();
        JComboBox deptComboBox = new JComboBox();

        JButton addCourseButton = new JButton("Add Course");
        JButton displayAllButton = new JButton("Display All");
        JButton displayDeptButton = new JButton("Display (dept)");

        String[] data = {"Data 1", "Data 2"};
        JList courseList = new JList(data);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); //how many cells can be selected
        courseList.setLayoutOrientation(JList.HORIZONTAL_WRAP); //see the figure above

        JScrollPane listScroller = new JScrollPane(courseList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        GroupLayout userInput = new GroupLayout(getContentPane());
        getContentPane().setLayout(userInput);

        userInput.setAutoCreateGaps(true);
        userInput.setAutoCreateGaps(true);

        userInput.setHorizontalGroup(userInput.createSequentialGroup()
                .addGroup(userInput.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userInput.createSequentialGroup()
                                .addGroup(userInput.createParallelGroup(LEADING)
                                        .addComponent(codeLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(creditsLabel)
                                        .addComponent(deptLabel)
                                        .addComponent(addCourseButton))
                                .addGroup(userInput.createParallelGroup(LEADING)
                                        .addComponent(codeTextField)
                                        .addComponent(nameTextField)
                                        .addComponent(creditsTextField)
                                        .addComponent(deptComboBox))))
                .addGroup(userInput.createParallelGroup(LEADING)
                        .addComponent(courseList)
                        .addComponent(displayAllButton)
                        .addComponent(displayDeptButton))
        );

        //layout.linkSize(SwingConstants.HORIZONTAL, codeTextField, nameTextField, creditsTextField);
        userInput.setVerticalGroup(userInput.createSequentialGroup()
                .addGroup(userInput.createParallelGroup(BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeTextField)
                        .addGroup(userInput.createSequentialGroup()
                                .addComponent(courseList)))
                .addGroup(userInput.createParallelGroup(BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGroup(userInput.createParallelGroup(BASELINE)
                        .addComponent(creditsLabel)
                        .addComponent(creditsTextField))
                .addGroup(userInput.createParallelGroup(BASELINE)
                        .addComponent(deptLabel)
                        .addComponent(deptComboBox))
                .addGroup(userInput.createParallelGroup(BASELINE)
                        .addComponent(addCourseButton)
                        .addGroup(userInput.createSequentialGroup()
                                .addComponent(displayAllButton)
                                .addComponent(displayDeptButton)))
        );

        //layout.linkSize(SwingConstants.HORIZONTAL, codeLabel);
        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

}
