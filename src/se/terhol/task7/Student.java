package se.terhol.task7;

/**
 * This class represents a student
 * 
 * @author Jan Papousek
 */
public class Student
{
    private String firstName;
    
    private String lastName;

    /**
     * Creates a new student with the given first name and last name.
     * 
     * @param firstName student's first name
     * @param lastName student's last name
     * @throws NullPointerException if some of the parameters are null
     */
    public Student(String firstName, String lastName) {
        if (firstName == null) {
            throw new NullPointerException("The parameter [firstName] is null.");
        }
        if (lastName == null) {
            throw new NullPointerException("The parameter [lastName] is null.");
        }        
        this.firstName  = firstName;
        this.lastName   = lastName;
    }

    /**
     * @return student's first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @return student's last name
     */
    public String getLastName() {
        return lastName;
    }
}
