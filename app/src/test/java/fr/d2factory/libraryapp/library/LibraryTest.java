package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;
import fr.d2factory.libraryapp.states.NormalMemberState;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LibraryTest {
    private Library library ;
    private BookRepository bookRepository;
    Student student;
    Resident resident;
    @Before
    public void setup(){
        //TODO instantiate the library and the repository
        bookRepository = new BookRepository();
        //TODO add some test books (use BookRepository#addBooks)
        bookRepository.loadJSONFromAsset();
        //TODO to help you a file called books.json is available in src/test/resources
        student = new Student(12,1354, new NormalMemberState());
        resident = new Resident(12,1254, new NormalMemberState());
    }

    @Test
    public void member_can_borrow_a_book_if_book_is_available(){

        assertTrue(student.borrowBook(getClass().getClassLoader(), 968787565445,LocalDate.now()));    }

    @Test
    public void borrowed_book_is_no_longer_available(){
        fail("Implement me");
    }

    @Test
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book(){
        fail("Implement me");
    }

    @Test
    public void students_pay_10_cents_the_first_30days(){
        fail("Implement me");
    }

    @Test
    public void students_in_1st_year_are_not_taxed_for_the_first_15days(){
        fail("Implement me");
    }

    @Test
    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days(){
        fail("Implement me");
    }

    @Test
    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
        fail("Implement me");
    }

    @Test
    public void members_cannot_borrow_book_if_they_have_late_books(){
        fail("Implement me");
    }
}
