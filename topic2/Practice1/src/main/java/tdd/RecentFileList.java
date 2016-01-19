package tdd;

import java.util.*;
import java.io.File;


public class RecentFileList 
{
    private List<File> list;
    private int size;
    private static final int MAX_SIZE = 15;
    
    public RecentFileList() {
        list = new LinkedList();
        size = 0;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int getSize() {
        return size;
    }
    
    public void openFile(File file) {
        if (size < 15) {
            if (list.contains(file)) {
                list.remove(file);
            }
            list.add(0, file);
            size++;
        } else {
            if (list.contains(file)) {
                list.remove(file);
            } else {
                list.remove(MAX_SIZE - 1);
            }
            list.add(0, file);
        }
    }
    
    public boolean exists(File file) {
        return list.contains(file);
    }
    
    public int getIndex(File file) {
        return list.indexOf(file);
    }
    
    public int getLastIndex(File file) {
        return list.lastIndexOf(file);
    }
}
