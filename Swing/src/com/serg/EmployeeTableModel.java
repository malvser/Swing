package com.serg;

/**
 *
 * @author serg
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.serg.model.Employee;

public class EmployeeTableModel extends AbstractTableModel {

    private final List<Employee> employeeList;

    private final String[] columnNames = new String[]{
        "Id", "Name", "Hourly Rate", "Part Time"
    };
    private final Class[] columnClass = new Class[]{
        Integer.class, String.class, Double.class, Boolean.class
    };

    public EmployeeTableModel(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return employeeList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee row = employeeList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row.getId();
            case 1:
                return row.getName();
            case 2:
                return row.getHourlyRate();
            case 3:
                return row.isPartTime();
            default:
                break;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    }

  
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Employee row = employeeList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                try {
                    row.setId(Integer.parseInt((String) aValue));
                } catch (NumberFormatException e) {
                    e.getMessage();
                }

                break;
            case 1:
                try {
                    row.setName((String) aValue);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                break;
            case 2:
                try {
                    row.setHourlyRate(Double.parseDouble((String) aValue));
                } catch (NumberFormatException e) {
                    e.getMessage();                    
                }
                break;
            case 3:
                try {
                    row.setPartTime(Boolean.parseBoolean((String) aValue));
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                break;
            default:
                break;
        }
    }

}
