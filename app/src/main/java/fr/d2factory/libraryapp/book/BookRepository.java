package fr.d2factory.libraryapp.book;

import android.content.Context;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private static final String TAG = "BookRepository";
    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public boolean isBorrowedBook(Book book) {
        return borrowedBooks
                .entrySet()
                .stream()
                .filter(map -> map.getKey().equals(book))
                .count()>0;
    }


    public ArrayList<Book> getAvailableBooks() {
        if(availableBooks!=null) {
            ArrayList<Book> books = (ArrayList<Book>) availableBooks
                    .values()
                    .stream()
                    .collect(Collectors.toList());

            return books;
        }
        return null;
    }

    public void addBooks(List<Book> books){
        books.forEach(book -> {
            availableBooks.put(book.isbn,book);
        });
    }

    public Book findBook(long isbnCode) {
        return (Book) availableBooks
                .entrySet()
                .stream()
                .filter(map -> map.getKey().isbnCode==isbnCode) //filter by value
                .collect(Collectors.toSet());

    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt){
        borrowedBooks.put(book,borrowedAt);
    }

    public LocalDate findBorrowedBookDate(Book book) {
        return borrowedBooks
                .entrySet()
                .stream()
                .filter(map -> map.getKey() == book) //filter by value
                .findFirst()
                .get()
                .getValue();

    }

    public void loadJSONFromAsset() {
        String json = null;
        JSONParser jsonParser = new JSONParser();

        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            Object obj = jsonParser.parse(json);
            JSONArray booksList = (JSONArray) obj;

            //Iterate over books array
            for (int i = 0; i < booksList.size(); i++) {
                JSONObject row = (JSONObject) booksList.get(i);
                JSONObject isbn = (JSONObject) row.get("isbn");
                ISBN iSBN = new ISBN( Long.parseLong(isbn.getOrDefault("isbnCode",00000).toString()));
                Book book = new Book(
                        row.getOrDefault("title","No title").toString()
                        , row.getOrDefault("author", "Author not mentioned").toString()
                        , iSBN
                );
                Log.i(TAG, "book number " + i + " : " + book.isbn.isbnCode);
                availableBooks.put(iSBN,book);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void loadJSONFromAsset(Context c) {
        String json = null;
        JSONParser jsonParser = new JSONParser();

        try {
            InputStream is = c.getAssets().open("books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            Object obj = jsonParser.parse(json);
            JSONArray booksList = (JSONArray) obj;

            //Iterate over books array
            for (int i = 0; i < booksList.size(); i++) {
                JSONObject row = (JSONObject) booksList.get(i);
                JSONObject isbn = (JSONObject) row.get("isbn");
                ISBN iSBN = new ISBN( Long.parseLong(isbn.getOrDefault("isbnCode",00000).toString()));
                Book book = new Book(
                        row.getOrDefault("title","No title").toString()
                        , row.getOrDefault("author", "Author not mentioned").toString()
                        , iSBN
                );
                Log.i(TAG, "book number " + i + " : " + book.isbn.isbnCode);
                availableBooks.put(iSBN,book);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
