package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;
import fr.d2factory.libraryapp.states.NormalMemberState;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LibraryTest {
    private BookRepository bookRepository;
    private Library library;
    Student student,firstYearStudent, lateStudent;
    Resident resident, lateResident, cantBorrow;

    @Before
    public void setup(){
        //TODO instantiate the library and the repository
        library = new NormalMemberState();
        bookRepository  = BookRepository.getBookRepository();
        //TODO add some test books (use BookRepository#addBooks)
        addBooksData();
        //TODO to help you a file called books.json is available in src/test/resources
        instantiateMembers();

    }

    private void instantiateMembers() {
        student = new Student(12,100);
        lateStudent = new Student(1,100);
        firstYearStudent =  new Student(10,100);
        firstYearStudent.setFirstYear(true);
        resident = new Resident(11,100);
        lateResident = new Resident(11,100);
        cantBorrow = new Resident(11,100);
    }

    private void addBooksData() {
        ArrayList<Book> books = new ArrayList<>();
        ISBN isbn;
        Book book;
        isbn = new ISBN(968787565445L);
        book = new Book("Catch 22", "Joseph Heller", isbn);
        books.add(book);
        isbn = new ISBN(465789453149L);
        book = new Book("La peau de chagrin", "Balzac", isbn);
        books.add(book);
        isbn = new ISBN(3326456467846L);
        book = new Book("Around the world in 80 days", "Jules Verne", isbn);
        books.add(book);
        isbn = new ISBN(46578964513L);
        book = new Book("Harry Potter", "J.K. Rowling", isbn);
        books.add(book);
        bookRepository.addBooks(books);
    }

    @Test
    public void member_can_borrow_a_book_if_book_is_available(){
        library.borrowBook( 968787565445L,resident,LocalDate.now().minusDays(10));
    }

    @Test
    public void borrowed_book_is_no_longer_available(){
        library.borrowBook( 968787565445L,resident,LocalDate.now().minusDays(10));
        library.borrowBook( 968787565445L,student,LocalDate.now().minusDays(30));
    }

    @Test
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book(){
        library.borrowBook( 968787565445L,resident,LocalDate.now().minusDays(10));
        library.returnBook( bookRepository.findBook(968787565445L),resident);
        // resident.getWallet()-(0.1*10)
    }

    @Test
    public void students_pay_10_cents_the_first_30days(){
        library.borrowBook( 968787565445L,student,LocalDate.now().minusDays(30));
        library.returnBook( bookRepository.findBook(968787565445L),student);
    }

    @Test
    public void students_in_1st_year_are_not_taxed_for_the_first_15days(){
        library.borrowBook( 465789453149L,firstYearStudent,LocalDate.now().minusDays(15));
        library.returnBook( bookRepository.findBook(465789453149L),firstYearStudent);
        library.borrowBook( 465789453149L,firstYearStudent,LocalDate.now().minusDays(10));
        library.returnBook( bookRepository.findBook(465789453149L),firstYearStudent);
    }

    @Test
    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days(){
        library.borrowBook( 3326456467846L,lateStudent,LocalDate.now().minusDays(35)) ;
        library.returnBook( bookRepository.findBook(3326456467846L),lateStudent);
    }

    @Test
    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
        library.borrowBook( 968787565445L,lateResident,LocalDate.now().minusDays(65)) ;
        library.returnBook( bookRepository.findBook(968787565445L),lateResident);
    }

    @Test
    public void members_cannot_borrow_book_if_they_have_late_books(){
        library.borrowBook( 46578964513L,cantBorrow,LocalDate.now().minusDays(456));
        library.borrowBook( 968787565445L,cantBorrow,LocalDate.now().minusDays(6));
    }
}
