package tdd;

import org.junit.*;

public class BlogTest
{
    @Test
    public void newBlogIsEmpty() {
        Blog testblog1 = new Blog();
        Assert.assertNotNull("Blog shouldn't be null", testblog1);
        Assert.assertTrue("Blog should be empty", testblog1.isEmpty());
    }
    
    @Test
    public void addingNewEntryIncrementsSizeByOne() {
        Blog testblog2 = new Blog();
        Entry entry2 = new Entry("First entry");
        Assert.assertNotNull("Entry shouldn't be null", testblog2);
        int oldsize = testblog2.getSize();
        testblog2.addEntry(entry2);
        Assert.assertTrue("Failed, size should increment by 1", (oldsize + 1 == testblog2.getSize()));
    }
    
    @Test
    public void newEntryIsAddedToTheTop() {
        Blog testblog3 = new Blog();
        Entry entry3 = new Entry("First entry\n");
        Entry entry4 = new Entry("Second entry\n");
        Entry entry5 = new Entry("Third entry\n");
        testblog3.addEntry(entry3);
        Assert.assertTrue("entry3 should be First Entry", testblog3.getEntryIndex(entry3) == 0);
        testblog3.addEntry(entry4);
        Assert.assertTrue("entry4 should be First Entry", testblog3.getEntryIndex(entry4) == 0);
        testblog3.addEntry(entry5);
        Assert.assertTrue("entry5 should be First Entry", testblog3.getEntryIndex(entry5) == 0);
    }
    
    @Test
    public void deleteEntryDecrementsSizeByOne() {
        Blog testblog4 = new Blog();
        Entry entry6 = new Entry("First entry\n");
        Entry entry7 = new Entry("Second entry\n");
        Entry entry8 = new Entry("Third entry\n");
        testblog4.addEntry(entry6);
        testblog4.addEntry(entry7);
        testblog4.addEntry(entry8);
        int size = testblog4.getSize();
        testblog4.deleteEntry(entry6);
        Assert.assertTrue("Size should have been decremented by 1", ((size - 1) == testblog4.getSize()));
    }
    
    @Test
    public void deleteEntryRemovesTheSpecifiedEntry() {
        Blog testblog5 = new Blog();
        Entry entry9 = new Entry("First entry\n");
        Entry entry10 = new Entry("Second entry\n");
        Entry entry11 = new Entry("Third entry\n");
        testblog5.addEntry(entry9);
        testblog5.addEntry(entry10);
        testblog5.addEntry(entry11);
        testblog5.deleteEntry(entry9);
        boolean flag1 = testblog5.exists(entry9);
        Assert.assertFalse("entry9 should not exist on this blog anymore", flag1);
    }
    
    @Test
    public void deleteNonExistingEntryDoesNothing() {
        Blog testblog6 = new Blog();
        Blog testblog6clone = new Blog();
        Entry entry12 = new Entry("First entry\n");
        Entry entry13 = new Entry("Second entry\n");
        Entry entry14 = new Entry("Third entry\n");
        testblog6.addEntry(entry12);
        testblog6.addEntry(entry13);
        testblog6clone.addEntry(entry12);
        testblog6clone.addEntry(entry13);
        testblog6.deleteEntry(entry14);
        boolean flag2 = testblog6.isEqual(testblog6clone);
        Assert.assertTrue("Deleting a non-existing entry changed the Blog", flag2);
    }
    
    @Test
    public void deleteEntryFromEmptyBlogDoesNothing() {
        Blog testblog7 = new Blog();
        Assert.assertTrue("new blog should be empty", testblog7.isEmpty());
        Entry entry15 = new Entry();
        testblog7.deleteEntry(entry15);
        Assert.assertTrue("should still be empty after deletion", testblog7.isEmpty());
    }
    
    @Test
    public void blogContains10EntriesAtMost() {
        Blog testblog8 = new Blog();
        //Create and add 11 Entries to the blog
        Entry entry16 = new Entry("1st entry\n");
        Entry entry17 = new Entry("2nd entry\n");
        Entry entry18 = new Entry("3rd entry\n");
        Entry entry19 = new Entry("4th entry\n");
        Entry entry20 = new Entry("5th entry\n");
        Entry entry21 = new Entry("6th entry\n");
        Entry entry22 = new Entry("7th entry\n");
        Entry entry23 = new Entry("8th entry\n");
        Entry entry24 = new Entry("9th entry\n");
        Entry entry25 = new Entry("10th entry\n");
        Entry entry26 = new Entry("11th entry\n");
        testblog8.addEntry(entry16);
        testblog8.addEntry(entry17);
        testblog8.addEntry(entry18);
        testblog8.addEntry(entry19);
        testblog8.addEntry(entry20);
        testblog8.addEntry(entry21);
        testblog8.addEntry(entry22);
        testblog8.addEntry(entry23);
        testblog8.addEntry(entry24);
        testblog8.addEntry(entry25);
        testblog8.addEntry(entry26);
        Assert.assertTrue("Should have 10 entries at most", (10 == testblog8.getSize()));
    }
    
    @Test
    public void addingNewEntryToAFullBlogRemovesOldestEntry() {
        Blog testblog9 = new Blog();
        //Create and add 11 Entries to the blog
        Entry entry27 = new Entry("1st entry\n");
        Entry entry28 = new Entry("2nd entry\n");
        Entry entry29 = new Entry("3rd entry\n");
        Entry entry30 = new Entry("4th entry\n");
        Entry entry31 = new Entry("5th entry\n");
        Entry entry32 = new Entry("6th entry\n");
        Entry entry33 = new Entry("7th entry\n");
        Entry entry34 = new Entry("8th entry\n");
        Entry entry35 = new Entry("9th entry\n");
        Entry entry36 = new Entry("10th entry\n");
        Entry entry37 = new Entry("11th entry\n");
        testblog9.addEntry(entry27);
        testblog9.addEntry(entry28);
        testblog9.addEntry(entry29);
        testblog9.addEntry(entry30);
        testblog9.addEntry(entry31);
        testblog9.addEntry(entry32);
        testblog9.addEntry(entry33);
        testblog9.addEntry(entry34);
        testblog9.addEntry(entry35);
        testblog9.addEntry(entry36);
        testblog9.addEntry(entry37);
        Assert.assertFalse("oldest entry should be gone", testblog9.exists(entry27));
        boolean flag3 = testblog9.getEntryIndex(entry37) == 0;
        Assert.assertTrue("First entry should be entry37", flag3);
    }
}
