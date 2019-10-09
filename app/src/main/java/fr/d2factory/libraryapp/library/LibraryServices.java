package fr.d2factory.libraryapp.library;

import android.content.Context;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.HasNoMoneyLeftException;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

public class LibraryServices implements Library {

    private Book book;
    private BookRepository bookRepository;

    @Override
    public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        bookRepository = BookRepository.getBookRepository();
        book = bookRepository.findBook(isbnCode);
        /**
         * Check if the user had already borrowed books. Then check the state of each book
         */
        if(bookRepository.isMemberBorrowedBook(member)){
            bookRepository.MemberBorrowedBook(member).forEach(book ->{
                long daysOfBorrow = ChronoUnit.DAYS.between(bookRepository.findBorrowedBookDate(book), LocalDate.now());
                if(member instanceof Student){
                    if(daysOfBorrow>30){
                        System.out.println("You need to return borrowed books first");
                        //throw new HasLateBooksException("You need to return borrowed books!");
                    }
                }
                else if(member instanceof Resident){
                    if(daysOfBorrow>60){
                        System.out.println("You need to return borrowed books first");
                        //throw new HasLateBooksException("You need to return borrowed books!");
                    }
                }
            } );
        }
        /**
         * Check if the book is available or not
         */
        if(bookRepository.isBorrowedBook(book)){
            System.out.println("Book is not available");
            return null;
        }
        bookRepository.saveBookBorrow(book, borrowedAt);
        bookRepository.saveBookBorrowMember(book, member);
        System.out.println("Book is available ;)");
        return book;
    }

    @Override
    public void returnBook(Book book, Member member) {
        /**
         * Check if the book was borrowed.
         * Then start payment process and remove the book from borrowed books list.
         */
        bookRepository = BookRepository.getBookRepository();
        LocalDate borrowedDate = bookRepository.findBorrowedBookDate(book);
        if(borrowedDate!=null){
            long daysOfBorrow = ChronoUnit.DAYS.between(borrowedDate, LocalDate.now());
            member.payBook((int)daysOfBorrow);
            bookRepository.removeBookBorrow(book,borrowedDate);
            bookRepository.removeBookBorrowMember(book,member);
            System.out.println("Book returned");
        }
        else{
            System.out.println("The book is not borrowed");
        }
    }
}
