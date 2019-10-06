package fr.d2factory.libraryapp.states;

import android.content.Context;
import android.widget.Toast;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Member;

public class NormalMemberState implements Library {

    @Override
    public Book borrowBook(Context c, long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        Toast.makeText(c.getApplicationContext(), "book borrowed", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public void returnBook(Context c, Book book, Member member) {
        Toast.makeText(c.getApplicationContext(), "book return", Toast.LENGTH_LONG).show();

    }
}
