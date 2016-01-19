package tdd;

public class Entry
{
    public String data;
    
    public Entry() {
    }
    
    public Entry(String entryData) {
        data = entryData;
    }
    
    public void setEntryData(String entryData) {
        data = entryData;
    }
    
    public String showEntry() {
        return data;
    }
}
