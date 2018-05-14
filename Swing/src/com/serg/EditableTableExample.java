/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serg;

/**
 *
 * @author serg
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import com.serg.model.Employee;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditableTableExample extends JFrame {

    private JTable table;
    private JScrollPane jsPane;
    private EmployeeTableModel model;
    private JPanel dialogPanel;
    private JTextField tf[];
    private JLabel lbl[];
    private JPanel panel;    

    public EditableTableExample() {
        initComponents();

    }

    private void initComponents() {
        //build the list
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            employeeList.add(new Employee(i, "John " + i, 40.0 + i, false));
            employeeList.add(new Employee(i + 1, "Rambo " + i, 30.0 + i, true));
        }        
        //create the model
        model = new EmployeeTableModel(employeeList);

        table = new JTable(model);                
        //add the table to the frame
        jsPane = new JScrollPane(table);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2) {

                    int x = e.getX();
                    int y = e.getY();
                    int row = table.rowAtPoint(new Point(x, y));
                    //int col = table.columnAtPoint(new Point(x, y));

                    String array[] = new String[table.getColumnCount()];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = "" + table.getValueAt(row, i);
                    }
                    populateTextField(array);
                    JOptionPane.showMessageDialog(null, dialogPanel,
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                   
                    for (int i = 0; i < tf.length; i++) {
                        model.setValueAt(tf[i].getText(), row, i);
                    }

                }

            }
        });
        
        panel = new JPanel(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

        panel.add(panel1, BorderLayout.NORTH);
        panel.add(jsPane, BorderLayout.CENTER);
        getContentPane().add(panel);
        
        this.setTitle("Employee List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        prepareDialogPanel();
        this.pack();
        this.setVisible(true);
    }
    
     private void populateTextField(String[] s) {
        for (int i = 0; i < s.length; i++) {
            tf[i].setText(s[i]);

        }
    }
     
     private void prepareDialogPanel() {

        dialogPanel = new JPanel();
        int col = table.getColumnCount();
        dialogPanel.setLayout(new GridLayout(col, 1));
        tf = new JTextField[col];
        lbl = new JLabel[col];

        for (int i = 0; i < col; i++) {
            lbl[i] = new JLabel(table.getColumnName(i));
            tf[i] = new JTextField(10);

            dialogPanel.add(lbl[i]);
            dialogPanel.add(tf[i]);

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditableTableExample editableTableExample = new EditableTableExample();
        });
    }

}
