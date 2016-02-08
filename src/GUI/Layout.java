/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Evan
 */
public class Layout extends JFrame {

    public Layout() {

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

        JList courseList = new JList();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(codeLabel)
                .addComponent(codeTextField)
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, codeLabel, codeTextField);
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(codeLabel)
                .addComponent(codeLabel)
                .addComponent(codeLabel))
            .addGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeLabel))
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeLabel)))
                .addComponent(codeLabel))
        );
        
        //layout.linkSize(SwingConstants.HORIZONTAL, codeLabel);
        setTitle("Find");
        //pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
