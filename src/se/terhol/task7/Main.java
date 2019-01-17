package se.terhol.task7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{

    public static void main(String[] args) throws Exception {
        ClassBook classBook = new ClassBookImpl();

        classBook.load(new FileInputStream("test.data"));

        System.out.print("Zadejte krestni jmeno studenta: ");
        String firstName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.print("Zadejte prijmeni studenta: ");
        String lastName = new BufferedReader(new InputStreamReader(System.in)).readLine();        
        Student student = new Student(firstName, lastName);
        classBook.addStudent(student);
        System.out.print("Zadejte známky studenta (oddělené mezerou): ");
        String[] marks = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
        for (String mark : marks) {
            classBook.addMark(student, Integer.parseInt(mark));
        }
        
        System.out.println("----------------------------");        
        System.out.println("PRŮMĚRNÉ ZNÁMKY");
        System.out.println("----------------------------");        
        
        for(Student st: classBook.getStudents()) {
            System.out.println(st.getFirstName() + " " + st.getLastName() + ": " + classBook.getAverageMark(st));
        }

        classBook.save(new FileOutputStream("test-output.data"));

        System.out.println("----------------------------");        
        System.out.println("ZÁLOHA TŘIDNÍ KNIHY");
        System.out.println("----------------------------");        
        classBook.save(System.out);        

        System.out.println("----------------------------");        
        System.out.println("----------------------------");                
    }

}