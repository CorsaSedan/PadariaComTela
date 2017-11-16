
package tablemodel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class GenericTableModel<T> extends AbstractTableModel {

    protected List objectList;
    protected Class objectClass;
    protected String[] columns;

    public GenericTableModel(Class clazz) {
        objectClass = clazz;
        columns = new String[objectClass.getDeclaredFields().length];
        for (int i = 0; i < objectClass.getDeclaredFields().length;i++) {
            columns[i] = objectClass.getDeclaredFields()[i].getName();
        }
    }

    public GenericTableModel(Class clazz, List objectList) {
        this(clazz);
        this.objectList = objectList;
    }
    
    public GenericTableModel(Class clazz, String[] columns) {
        this(clazz);
        this.columns = columns;
    }
    
    public GenericTableModel(List objectList) {
        objectClass = objectList.get(0).getClass();
        this.objectList = objectList;
        columns = new String[objectClass.getDeclaredFields().length];
        for (int i = 0; i < objectClass.getDeclaredFields().length; i++) {
            columns[i] = objectClass.getDeclaredFields()[i].getName();
        }
    }

    public GenericTableModel(List objectList, String[] columns) {
        this(objectList);
        this.columns = columns;
    }
    
    public void setObjectList(List objectList) {
        this.objectList = objectList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return objectList.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public void add(T t) {
        objectList.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void remove(T t) {
        objectList.remove(t);
        fireTableDataChanged();
    }

    public void remove(int rowIndex) {
        objectList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void remove(int[] rowsIndexes) {
        for (int i = 0; i < rowsIndexes.length; i++) {
            remove(rowsIndexes[i] - i);
        }
    }
    
    public void add(int index, T t){
        objectList.add(index,t);
        fireTableRowsInserted(index, index);
    }

    public List getObjectList() {
        return objectList;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            String methodName = "get" + getColumnName(columnIndex).substring(0, 1).toUpperCase() + getColumnName(columnIndex).substring(1);
            return objectClass.getMethod(methodName).invoke(objectList.get(rowIndex));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
