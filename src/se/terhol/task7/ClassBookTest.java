package se.terhol.task7;

import java.util.*;
import java.io.*;

/**
 * The test class ClassBookTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClassBookTest extends junit.framework.TestCase
{

    private Map<Student, Collection<Integer>> data;

    private String[] dataAsLines;
    
    private ClassBook classBook;
    
    public static final String ENCODING = "UTF-8";
    
    protected void setUp() {
        classBook = new ClassBookImpl();
    }
    
    public void testAddMark() {
        try {
            classBook.addMark(null, 1);
            fail("Pri volani addMark() s null studentem nebyla vyhozena vyjimka.");
        }
        catch(NullPointerException e) {}
        catch(Exception e) {
            fail("Pri volani addMark() s null studentem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }          
        
        loadTestData(classBook);
        
        try {
            classBook.addMark(new Student("Lary", "Fary"), 0);
            fail("Pri volani addMark() s nulovou znamkou nebyla vyhozena vyjimka.");
        }
        catch(IllegalArgumentException e) {}
        catch(Exception e) {
            fail("Pri volani addMark() s nulovou znamkou byla vyhozena spatna vyjimka " + e.getClass().getName());
        }          
        
        try {
            classBook.addMark(new Student("Lary", "Fary"), 1);
            fail("Pri volani addMark() se studentem, ktery neni v tridni knize, nebyla vyhozena vyjimka.");
        }
        catch(IllegalArgumentException e) {}
        catch(Exception e) {
            fail("Pri volani addMark() s studentem, ktery neni v tridni knize, byla vyhozena spatna vyjimka " + e.getClass().getName());
        }             
    }
    
    public void testAddStudent() {
        try {
            classBook.addStudent(null);
            fail("Pri volani addStudent() s null studentem nebyla vyhozena vyjimka.");
        }
        catch(NullPointerException e) {}
        catch(Exception e) {
            fail("Pri volani addStudent() s null studentem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }           
        
        loadTestData(classBook);
        
        for (Student student : data().keySet()) {
            assertFalse("Pri vkladani studenta, ktery uz v tridni knize je, musi metoda addStudent() vracet false.", classBook.addStudent(student));
        }
        
        assertTrue("Pri vkladani noveho studenta musi metoda addStudent() vracet true.", classBook.addStudent(new Student("Lary", "Fary")));
        
        classBook.clear();
        
        for (Student student : data().keySet()) {
            assertTrue("Pri vkladani noveho studenta musi metoda addStudent() vracet true.", classBook.addStudent(student));
        }        
    }
    
    public void testAverageMark() {
        loadTestData(classBook);

        try {
            classBook.getAverageMark(null);
            fail("Pri volani getAverageMark() s null studentem nebyla vyhozena vyjimka.");
        }
        catch(NullPointerException e) {}
        catch(Exception e) {
            fail("Pri volani getAverageMark() s null studentem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }   
        
        try {
            classBook.getAverageMark(new Student("Lary", "Fary"));
            fail("Pri volani getAverageMark() se studentem, ktery v knize neni, nebyla vyhozena vyjimka.");
        }
        catch(IllegalArgumentException e) {}
        catch(Exception e) {
            fail("Pri volani getAverageMark() se studentem, ktery v knize neni, byla vyhozena spatna vyjimka " + e.getClass().getName());
        }        
        for (Student student : data().keySet()) {
            float avg = 0;
            for (Integer mark : data().get(student)) {
                avg += mark;
            }
            avg = avg/data().get(student).size();
            assertEquals("Vraceny prumer znamek neodpovida.", avg, classBook.getAverageMark(student));
        }
    }
    
    public void testClear() {
        loadTestData(classBook);
        assertFalse("Po pridani studentu a znamek je tridni kniha stale prazdna.", classBook.getStudents().isEmpty());
        classBook.clear();
        assertTrue("Po zavolani metody clear() neni tridni kniha prazdna.", classBook.getStudents().isEmpty());
    }
    
    public void testGetMarks() {
        loadTestData(classBook);

        try {
            classBook.getMarks(null);
            fail("Pri volani getMarks() s null studentem nebyla vyhozena vyjimka.");
        }
        catch(NullPointerException e) {}
        catch(Exception e) {
            fail("Pri volani getMarks() s null studentem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }        
        
        try {
            classBook.getMarks(new Student("Lary", "Fary"));
            fail("Pri volani getMarks() se studentem, ktery v knize neni, nebyla vyhozena vyjimka.");
        }
        catch(IllegalArgumentException e) {}
        catch(Exception e) {
            fail("Pri volani getMarks() se studentem, ktery v knize neni, byla vyhozena spatna vyjimka " + e.getClass().getName());
        }
        
        for (Student student : data().keySet()) {
            assertEquals("Pocet znamek studenta se musi shodovat s poctem vlozenych znamek.", data().get(student).size(), classBook.getMarks(student).size());
            assertTrue("Tridni kniha neobsahuje znamky, ktere do ni byly vlozeny.", classBook.getMarks(student).containsAll(data().get(student)));            
            try {
                classBook.getMarks(student).add(1);
                fail("getMarks() vraci modifikovatelnou kolekci.");
            }
            catch(UnsupportedOperationException e) {}            
        }
    }
    
    public void testGetStudents() {
        loadTestData(classBook);        
        assertEquals("Pocet studentu v tridni knize neodpovida vlozenemu poctu studentu.", data().keySet().size(), classBook.getStudents().size());
        assertTrue("Studenti v tridni knize neodpovidaji tem, kteri do ni byli vlozeni.", classBook.getStudents().containsAll(data().keySet()));
        try {
            classBook.getStudents().add(new Student("Honza", "Novák"));
            fail("getStudents() vraci modifikovatelnou kolekci.");
        }
        catch(UnsupportedOperationException e) {}
    }
    
    public void testLoad() throws Exception {
        classBook.load(getTestInputStream());
        
        assertEquals("Pocet studentu v tridni knize neodpovida vlozenemu poctu studentu.", data().keySet().size(), classBook.getStudents().size());
        assertTrue("Studenti v tridni knize neodpovidaji tem, kteri do ni byli vlozeni.", classBook.getStudents().containsAll(data().keySet()));        
        
        for (Student student : data().keySet()) {
            assertEquals("Pocet znamek studenta se musi shodovat s poctem vlozenych znamek.", data().get(student).size(), classBook.getMarks(student).size());
            assertTrue("Tridni kniha neobsahuje znamky, ktere do ni byly vlozeny.", classBook.getMarks(student).containsAll(data().get(student)));            
        }
        
        try {
            classBook.load(new BadInputStream());
            fail("Pri volani metody load() s vadnym vstupnim proudem nebyla vyhozena vyjimka.");            
        }
        catch(ClassBookException e) {}
        catch(Exception e) {
            fail("Pri volani metody load() s vadnym vstupnim proudem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }         
        
        try {
            classBook.load(createInputStream(new String[] { "Lary Fary:1a" }));
            fail("Pri volani metody load() s vadnym vstupnim proudem nebyla vyhozena vyjimka.");              
        }
        catch(ClassBookException e) {}
        catch(Exception e) {
            fail("Pri volani metody load() s vadnym vstupnim proudem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }         
        
        try {
            classBook.load(createInputStream(new String[] { "Lary Fary aaa" }));
            fail("Pri volani metody load() s vadnym vstupnim proudem nebyla vyhozena vyjimka.");              
        }
        catch(ClassBookException e) {}
        catch(Exception e) {
            fail("Pri volani metody load() s vadnym vstupnim proudem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }           
        
        try {
            classBook.load(null);
            fail("Pri volani metody load() nulovym vstupnim proudem nebyla vyhozena vyjimka.");            
        }
        catch(NullPointerException e) {}
        catch(Exception e) {
            fail("Pri volani metody load() s nulovym vstupnim proudem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }          
    }
    
    public void testSave() throws Exception {
        classBook.load(getTestInputStream());
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        classBook.save(outputStream);
        
        // Testovaci data ulozit do pomocne kolekce
        Set<String> testDataLines = new HashSet<String>();
        for (String line : getTestDataAsLines()) {
            testDataLines.add(line);
        }        
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(
        new ByteArrayInputStream(outputStream.toByteArray()), ENCODING));        
        
        String line;
        while (null != (line = reader.readLine())) {
            assertTrue("Vystupni soubor obsahuje radek, ktery nebyl ve vstupnich" +
            " testovacich datech: " + line,testDataLines.contains(line));
            testDataLines.remove(line);
        }
        assertTrue("Ve vystupnim souboru chybi nektere radky ze vstupnich " +
        "testovacich dat: " + testDataLines,testDataLines.isEmpty());       
        
        try {
            classBook.save(new BadOutputStream());
            fail("Pri volani metody save() s vadnym vystupnim proudem nebyla vyhozena vyjimka.");            
        }
        catch(ClassBookException e) {}
        catch(Exception e) {
            fail("Pri volani metody save() s vadnym vystupnim proudem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }         
        
        try {
            classBook.save(null);
            fail("Pri volani metody save() nulovym vystupnim proudem nebyla vyhozena vyjimka.");            
        }
        catch(NullPointerException e) {}
        catch(Exception e) {
            fail("Pri volani metody save() s nulovym vystupnim proudem byla vyhozena spatna vyjimka " + e.getClass().getName());
        }  
                
    }
    
    private void loadTestData(ClassBook classBook) {
        for(Student student : data().keySet()) {
            classBook.addStudent(student);
            for (Integer mark : data.get(student)) {
                classBook.addMark(student, mark);
            }
        }
    }
    
    private Map<Student, Collection<Integer>> data() {
        if (this.data == null) {
            Map<Student, Collection<Integer>> data = new HashMap<Student, Collection<Integer>>();
        
            Student student = new Student("Franta", "Novák");
            int[] marks     = new int[] { 1, 2, 3, 4, 5 };
            data.put(student, new ArrayList<Integer>());
            for (int mark : marks) {
                data.get(student).add(mark);
            }
        
            student = new Student("Oldřich", "Pláteníček");
            marks   = new int[] { 2, 2, 2, 2, 2, 2, 2, 2 };        
            data.put(student, new ArrayList<Integer>());            
            for (int mark : marks) {
                data.get(student).add(mark);
            }
        
            student = new Student("Hugo", "Kokoška");
            marks   = new int[] { 1, 2 };        
            data.put(student, new ArrayList<Integer>());            
            for (int mark : marks) {
                data.get(student).add(mark);
            }        
            
            this.data = data;
        }
        return this.data;
    }

    // Tato metoda vytvori testovaci InputStream, ktery bude obsahovat data 
    // predana jako parametr v kodovani UTF-8
    private byte[] stringLinesToByteArray(String[] lines) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(out,ENCODING);
        for (String line : lines) {
            writer.write(line);
            writer.write("\n");
        }
        writer.flush();
        return out.toByteArray();
    }

    private InputStream createInputStream(String[] lines) throws IOException {
        return new ByteArrayInputStream(stringLinesToByteArray(lines));
    }    
    
    private String[] getTestDataAsLines() {
        if (dataAsLines == null) {
            dataAsLines = new String[data().keySet().size()];
        
            int i = 0;
            for (Student student : data().keySet()) {
                dataAsLines[i] = student.getFirstName() + " " + student.getLastName() + ":";
                StringBuilder builder = new StringBuilder();
                for (Integer mark : data().get(student)) {
                    if (!builder.toString().isEmpty()) {
                        builder.append(" ");
                    }
                    builder.append(mark);
                }
                dataAsLines[i] += builder.toString();
                i++;
            }
        }
        return dataAsLines;
    }
    
    private InputStream getTestInputStream() throws IOException {
        return createInputStream(getTestDataAsLines());
    }

    private class BadInputStream extends InputStream {
        public int read() throws IOException {
            throw new IOException("It is not possible to read from this stream");
        }
        
        public int available() {
            return 3;
        }
    }    
    
    private class BadOutputStream extends OutputStream {
        public void write(int value) throws IOException {
            throw new IOException("It is not possible to write to this stream");
        }        
    }    
    
}
