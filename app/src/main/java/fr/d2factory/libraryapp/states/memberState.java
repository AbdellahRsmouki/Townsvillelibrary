package fr.d2factory.libraryapp.states;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.member.Member;

interface memberState {



    void borrow(long isbnCode, Member member, LocalDate borrowedAt);
    void returnBook(Book book, Member member);

}
