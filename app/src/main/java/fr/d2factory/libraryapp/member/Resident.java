package fr.d2factory.libraryapp.member;

import android.content.Context;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.HasNoMoneyLeftException;
import fr.d2factory.libraryapp.library.Library;

public class Resident extends Member{


    public Resident(int memberId, float wallet) {
        super(memberId, wallet);
    }

    @Override
    public void payBook(int numberOfDays) {
        /**
         * Check the state of the user.
         * each case has a specific process
         */
        if(numberOfDays<60)
            pay((float) (0.10*numberOfDays));
        else
            pay((float) (6 + 0.20 * (numberOfDays - 60)));
    }

    private void pay(float toPay) {
        /**
         * simple method to pay for borrowed books.
         */
        if(getWallet()>toPay) {
            setWallet(getWallet() - toPay);
            System.out.println("The cost was: "+toPay+"eu and now you still have: "+getWallet()+"eu");
        }
        else
            throw new HasNoMoneyLeftException("You Have No Money!");

    }
}
