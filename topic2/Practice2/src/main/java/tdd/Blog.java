package tdd;

import java.util.*;

public class Blog {
    
    private List <Entry> listOfEntries = null;
    private int size;
    private static final int MAX_SIZE = 10;
    
    public Blog() {
        listOfEntries = new LinkedList();
        size = 0;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int getSize() {
        return size;
    }
    
    public void addEntry(Entry entry) {
        if (size < MAX_SIZE) {
            size++;
        } else {
            listOfEntries.remove(MAX_SIZE-1);
        }
        listOfEntries.add(0, entry);
    }
    
    public void deleteEntry(Entry entry) {
        if (size > 0) {
            listOfEntries.remove(entry);
            size--;
        }
    }
    /**
     *Wrapper for LinkedList.indexOf, returns the index of the first occurrence 
     *of the specified Entry on this Blog, or -1 if this Blog does not contain 
     *the given Entry
     */
    public int getEntryIndex(Entry entry) {
        return listOfEntries.indexOf(entry);
    }
    
    /**
     *Does the given entry exist in this blog?
     */
    public boolean exists(Entry entry) {
        return (listOfEntries.indexOf(entry) >= 0);
    }
    
    public boolean isEqual(Blog blog) {
        return listOfEntries.equals(blog.listOfEntries);
    }
    
    public void showBlogEntries() {
        for (int i = 0; i<size; i++) {
            System.out.println(listOfEntries.get(i).showEntry());
        }
    }
    
/*
    HERE I tried to write a method to clone this Blog and return it, but 
    it didn't compile due to "cannot find symbol" error.

    public Blog blogClone() {
        Blog result = new Blog();
        result.size = size;
//        LinkedList list = this.listOfEntries.clone();
        result.listOfEntries = (this.listOfEntries).clone();
        return result;
    }
*/

}
