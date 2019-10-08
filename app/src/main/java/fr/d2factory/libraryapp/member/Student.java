package fr.d2factory.libraryapp.member;

import android.content.Context;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.HasNoMoneyLeftException;
import fr.d2factory.libraryapp.library.Library;

public class Student extends Member {


    private boolean firstYear = false;


    public Student(int memberId, float wallet, Library memberState) {
        super(memberId, wallet, memberState);
    }

    public boolean isFirstYear() {
        return firstYear;
    }

    public void setFirstYear(boolean firstYear) {
        this.firstYear = firstYear;
    }

    public void payBook(int numberOfDays) {
        /*if(numberOfDays<30){
            if (!isFirstYear() || (isFirstYear() &&student had allready borrowed the book for 15 days))
                pay((float) (0.10 * numberOfDays));
            else{
                if(numberOfDays>lefted free days)
                    pay((float) (0.10 * (numberOfDays-lefted free days)));
            }
        }
        else
            pay((float) (6 + 0.20 * (numberOfDays - 30)));*/
    }

    private void pay(float toPay) {
        if(getWallet()>toPay)
            setWallet(getWallet()-toPay);
        else
            throw new HasNoMoneyLeftException("You Have No Money!");
    }

    @Override
    public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        return null;
    }

    @Override
    public void returnBook(Book book, Member member) {

    }
}
