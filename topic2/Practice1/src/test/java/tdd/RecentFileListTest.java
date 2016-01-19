package tdd;

import org.junit.*;
import java.io.File;

public class RecentFileListTest 
{
    @Test
    public void listIsEmptyWhenItIsCreated() {
        RecentFileList testlist = new RecentFileList();
        Assert.assertTrue("New list should be empty",testlist.isEmpty());
    }
    
    @Test
    public void openingAFileIncrementsListSizeByOne() {
        RecentFileList testlist1 = new RecentFileList();
        File testfile1 = new File("~/testfile1.txt");
        File testfile2 = new File("~/testfile2.txt");
        File testfile3 = new File("~/testfile3.txt");
        File testfile4 = new File("~/testfile4.txt");
        testlist1.openFile(testfile1);
        Assert.assertTrue("List size didn't increment", 1 == testlist1.getSize());
        testlist1.openFile(testfile2);
        Assert.assertTrue("List size didn't increment", 2 == testlist1.getSize());
        testlist1.openFile(testfile3);
        Assert.assertTrue("List size didn't increment", 3 == testlist1.getSize());
        testlist1.openFile(testfile4);
        Assert.assertTrue("List size didn't increment", 4 == testlist1.getSize());
    }
    
    @Test
    public void openingAFileAddsTheGivenFileToTheList() {
        RecentFileList testlist2 = new RecentFileList();
        File testfile5 = new File("~/testfile5.txt");
        File testfile6 = new File("~/testfile6.txt");
        testlist2.openFile(testfile5);
        testlist2.openFile(testfile6);
        boolean flag1 = testlist2.exists(testfile5);
        boolean flag2 = testlist2.exists(testfile6);
        Assert.assertTrue("File should exist on the list because it was opened", flag1);
        Assert.assertTrue("File should exist on the list because it was opened", flag2);
    }
    
    @Test
    public void openingAFileAddsItToTheTopOfTheList() {
        RecentFileList testlist3 = new RecentFileList();
        File testfile7 = new File("~/testfile7.txt");
        File testfile8 = new File("~/testfile8.txt");
        File testfile9 = new File("~/testfile9.txt");
        File testfile10 = new File("~/testfile10.txt");
        testlist3.openFile(testfile7);
        boolean flag3 = 0 == testlist3.getIndex(testfile7);
        testlist3.openFile(testfile8);
        flag3 = flag3 && (0 == testlist3.getIndex(testfile8));
        testlist3.openFile(testfile9);
        flag3 = flag3 && (0 == testlist3.getIndex(testfile9));
        testlist3.openFile(testfile10);
        flag3 = flag3 && (0 == testlist3.getIndex(testfile10));
        Assert.assertTrue("Files were not added in the correct order",flag3);
    }
    
    @Test
    public void openingAnExistingFileDoesNotDuplicateItOnTheList() {
        RecentFileList testlist4 = new RecentFileList();
        File testfile11 = new File("~/testfile11.txt");
        File testfile12 = new File("~/testfile12.txt");
        File testfile13 = new File("~/testfile13.txt");
        File testfile14 = new File("~/testfile14.txt");
        testlist4.openFile(testfile11);
        testlist4.openFile(testfile12);
        testlist4.openFile(testfile13);
        testlist4.openFile(testfile14);
        testlist4.openFile(testfile12);
        boolean flag4 = testlist4.getIndex(testfile12) == testlist4.getLastIndex(testfile12);
        Assert.assertTrue("Found Duplicated file", flag4);
    }
    
    @Test
    public void openingAnExistingFileBumpsItToTheTop() {
        RecentFileList testlist5 = new RecentFileList();
        File testfile15 = new File("~/testfile15.txt");
        File testfile16 = new File("~/testfile16.txt");
        File testfile17 = new File("~/testfile17.txt");
        File testfile18 = new File("~/testfile18.txt");
        testlist5.openFile(testfile15);
        testlist5.openFile(testfile16);
        testlist5.openFile(testfile17);
        testlist5.openFile(testfile18);
        testlist5.openFile(testfile16);
        Assert.assertTrue("file 16 should be at the top", 0 == (testlist5.getIndex(testfile16)));
    }
    
    @Test
    public void listContains15FilesAtMost() {
        RecentFileList testlist6 = new RecentFileList();

        //Creates and adds 20 different files to the list
        File testfile19 = new File("~/testfile19.txt");
        File testfile20 = new File("~/testfile20.txt");
        File testfile21 = new File("~/testfile21.txt");
        File testfile22 = new File("~/testfile22.txt");
        File testfile23 = new File("~/testfile23.txt");
        File testfile24 = new File("~/testfile24.txt");
        File testfile25 = new File("~/testfile25.txt");
        File testfile26 = new File("~/testfile26.txt");
        File testfile27 = new File("~/testfile27.txt");
        File testfile28 = new File("~/testfile28.txt");
        File testfile29 = new File("~/testfile29.txt");
        File testfile30 = new File("~/testfile30.txt");
        File testfile31 = new File("~/testfile31.txt");
        File testfile32 = new File("~/testfile32.txt");
        File testfile33 = new File("~/testfile33.txt");
        File testfile34 = new File("~/testfile34.txt");
        File testfile35 = new File("~/testfile35.txt");
        File testfile36 = new File("~/testfile36.txt");
        File testfile37 = new File("~/testfile37.txt");
        File testfile38 = new File("~/testfile38.txt");
        testlist6.openFile(testfile19);
        testlist6.openFile(testfile20);
        testlist6.openFile(testfile21);
        testlist6.openFile(testfile22);
        testlist6.openFile(testfile23);
        testlist6.openFile(testfile24);
        testlist6.openFile(testfile25);
        testlist6.openFile(testfile26);
        testlist6.openFile(testfile27);
        testlist6.openFile(testfile28);
        testlist6.openFile(testfile29);
        testlist6.openFile(testfile30);
        testlist6.openFile(testfile31);
        testlist6.openFile(testfile32);
        testlist6.openFile(testfile33);
        testlist6.openFile(testfile34);
        testlist6.openFile(testfile35);
        testlist6.openFile(testfile36);
        testlist6.openFile(testfile37);
        testlist6.openFile(testfile38);
        
        Assert.assertTrue("Size should be 15 at most",testlist6.getSize() == 15);
        
    }
    
    @Test
    public void openingAFileWithAFullListDeletesTheOldestFile() {
        RecentFileList testlist7 = new RecentFileList();
        //Creates and adds 16 files to the list
        File testfile39 = new File("~/testfile39.txt");
        File testfile40 = new File("~/testfile40.txt");
        File testfile41 = new File("~/testfile41.txt");
        File testfile42 = new File("~/testfile42.txt");
        File testfile43 = new File("~/testfile43.txt");
        File testfile44 = new File("~/testfile44.txt");
        File testfile45 = new File("~/testfile45.txt");
        File testfile46 = new File("~/testfile46.txt");
        File testfile47 = new File("~/testfile47.txt");
        File testfile48 = new File("~/testfile48.txt");
        File testfile49 = new File("~/testfile49.txt");
        File testfile50 = new File("~/testfile50.txt");
        File testfile51 = new File("~/testfile51.txt");
        File testfile52 = new File("~/testfile52.txt");
        File testfile53 = new File("~/testfile53.txt");
        File testfile54 = new File("~/testfile54.txt");
        
        testlist7.openFile(testfile39);
        testlist7.openFile(testfile40);
        testlist7.openFile(testfile41);
        testlist7.openFile(testfile42);
        testlist7.openFile(testfile43);
        testlist7.openFile(testfile44);
        testlist7.openFile(testfile45);
        testlist7.openFile(testfile46);
        testlist7.openFile(testfile47);
        testlist7.openFile(testfile48);
        testlist7.openFile(testfile49);
        testlist7.openFile(testfile50);
        testlist7.openFile(testfile51);
        testlist7.openFile(testfile52);
        testlist7.openFile(testfile53);
        testlist7.openFile(testfile54);
        
        Assert.assertFalse("file 39 should be gone", testlist7.exists(testfile39));
        Assert.assertTrue("file 54 should be at the top", 0 == testlist7.getIndex(testfile54));
    }
}
