package fr.d2factory.libraryapp.states;

import android.content.Context;
import android.widget.Toast;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Member;

public class LateMember implements Library {
    @Override
    public Book borrowBook(Context c, long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        Toast.makeText(c.getApplicationContext(), "You can't borrow", Toast.LENGTH_LONG).show();
        throw new HasLateBooksException("You Have to return the borrowed book first");
    }

    @Override
    public void returnBook(Context c, Book book, Member member) {
        Toast.makeText(c.getApplicationContext(), "book returned", Toast.LENGTH_LONG).show();

    }
}
