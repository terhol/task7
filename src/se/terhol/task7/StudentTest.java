package se.terhol.task7;
/**
 * The test class StudentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StudentTest extends junit.framework.TestCase
{

    public void testEquals() {
        Student[] students = new Student[] {
            new Student("Hugo", "Kokoška"),
            new Student("Hugo", "Kokoška"),
            new Student("Aleš", "Kokoška"),
            new Student("Hugo", "Kokoska")
        };
        
        assertTrue("Stejni studenti musi byt ekvivalentni.", students[0].equals(students[1]));
        assertTrue("Stejni studenti musi byt ekvivalentni.", students[1].equals(students[0]));        
        assertFalse("Studenti s ruznym krestnim jmenem nesmi byt ekvivelentni.", students[0].equals(students[2]));        
        assertFalse("Studenti s ruznym prijmenim nesmi byt ekvivelentni.",students[0].equals(students[3]));                
    }

    public void testHashCode() {
        Student[] students = new Student[] {
            new Student("Hugo", "Kokoška"),
            new Student("Hugo", "Kokoška")
        };        
        assertEquals("Stejni studenti musi mit stejny hash code.", students[0].hashCode(), students[1].hashCode());
    }
    
}
