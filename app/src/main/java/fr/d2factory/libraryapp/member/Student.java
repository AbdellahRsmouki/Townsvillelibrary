package fr.d2factory.libraryapp.member;

import android.content.Context;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.HasNoMoneyLeftException;
import fr.d2factory.libraryapp.library.Library;

public class Student extends Member {


    private boolean firstYear = false;
    private int freePeriod = 0;


    public Student(int memberId, float wallet) {
        super(memberId, wallet);
    }

    public void setFirstYear(boolean firstYear) {
        this.firstYear = firstYear;
        this.freePeriod = 15;
    }

    public boolean isFirstYear() {
        return firstYear;
    }

    public void payBook(int numberOfDays) {
        /**
         * Check the state of the user.
         * Then if the user is in First year or not.
         * finally each case has a specific process
         */
        if(numberOfDays<=30){
            if (!isFirstYear() || (isFirstYear() && this.freePeriod == 0)) {
                pay((float) (0.10 * numberOfDays));
            }
            else{
                if(numberOfDays>freePeriod){
                    pay((float) (0.10 * (numberOfDays-this.freePeriod)));
                    this.freePeriod=0;
                }else {
                    this.freePeriod -= numberOfDays;
                }
            }
        }
        else
            pay((float) (3 + 0.15 * (numberOfDays - 30)));
    }

    private void pay(float toPay) {
        /**
         * simple method to pay for borrowed books.
         */
        if(getWallet()>toPay) {
            setWallet(getWallet() - toPay);
            System.out.println("The cost was: " + toPay + "eu and now you still have: " + getWallet()+"eu");
        }
        else
            throw new HasNoMoneyLeftException("You Have No Money!");
    }

    public int getFreePeriod() {
        return freePeriod;
    }
}
