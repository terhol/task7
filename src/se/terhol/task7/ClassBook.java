package se.terhol.task7;

import java.util.Collection;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This interface represents a class books which contains
 * marks of the students.
 * 
 * @author Jan Papousek
 */
public interface ClassBook
{

    /**
     * In add a new mark into the student's list of marks
     * 
     * @param student
     * @param mark
     * @throws NullPointerException if the parameter student is null.
     * @throws IllegalArgumentException if the mark is not a positive number
     *         or the class book doesn't contain the given student.
     */
    void addMark(Student student, int mark);

    /**
     * It inserts a new student to the class book
     * 
     * @param student
     * @return true if the student has been inserted
     *         false otherwise (class book already contains the given student)
     * @throws NullPointerException if the parameter student is null.
     */
    boolean addStudent(Student student);

    /**
     * It removes all students from the class book.
     */
    void clear();
    
    /**
     * It returns an average mark of the student
     * 
     * @return average mark of the student
     * @throws NullPointerException if the parameter student is null.
     * @throws IllegalArgumentException if the class book doesn't contain
     *         the given student.
     */
    float getAverageMark(Student student);

    /**
     * It reuturns all student's marks.
     * 
     * @param student
     * @return unmodifiable collection containing the student's marks
     * @throws NullPointerException if the parameter student is null.
     * @throws IllegalArgumentException if the class book doesn't contain
     *         the given student.
     */
    Collection<Integer> getMarks(Student student);
    
    /**
     * It returns all students
     * 
     * @returns unmodifiable collection containing all students
     */
    Collection<Student> getStudents();

    /**
     * This method loads students and their marks from the given input
     * stream. The encoding UTF-8 is used.
     * 
     * Format of input data is as follows:
     * a) single line contains information about just one student
     * b) line starts with student's first and last name seperated by a single white space
     * c) line contains also the student's marks located after the student's name (symbol ':'
     *    is used as a seperater between name and marks)
     * d) marks are seperated by a single white space
     * 
     * Example:
     * Franta Novák:1 2 3 4 5
     * Oldřich Pláteníček:2 2 2 2 2 2 2 2
     * Hugo Kokoška:1 2
     * 
     * @param input Input stream -- source of data.
     * @throws NullPointerException if the parameter input is null.
     * @throws ClassBookException on IO failure or if data in input stream
     *         has invalid format.
     */    
    void load(InputStream input) throws ClassBookException;
    
    /**
     * This method saves the content of the class book into the given output stream.
     * 
     * Output format -- see load() method.
     * 
     * @param output Output stream.
     * @throws NullPointerException if the parameter output is null.
     * @throws ClassBookException on failure during the writing data into 
     * output stream.
     */    
    void save(OutputStream output) throws ClassBookException;
    
    
}
