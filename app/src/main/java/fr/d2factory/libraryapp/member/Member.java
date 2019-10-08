package fr.d2factory.libraryapp.member;

import android.content.Context;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

/**
 * A member is a person who can borrow and return books to a {@link Library}
 * A member can be either a student or a resident
 */
public abstract class Member implements Library{
    /**
     * An initial sum of money the member has
     */
    private float wallet;
    private Member memberState;
    private int memberId;
    private boolean borrow = true;

    public Member(int memberId, float wallet, Member memberState) {
        this.wallet = wallet;
        this.memberState = memberState;
        this.memberId = memberId;
    }

    public void changeState(Member state) {
        this.memberState = state;
    }

    public Library getState() {
        return this.memberState;
    }

    @Override
    public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        return this.memberState.borrowBook(isbnCode,member,borrowedAt);
    }
    @Override
    public void returnBook( Book book, Member member) {
        this.memberState.returnBook(book,member);
    }

    /**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public abstract void payBook(int numberOfDays);

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }
}
