package fr.d2factory.libraryapp.book;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.d2factory.libraryapp.townsville_library.R;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private static final String TAG = "BookRepository";
    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){
        //TODO implement the missing feature
    }

    public Book findBook(long isbnCode) {
        //TODO implement the missing feature
        return null;
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt){
        //TODO implement the missing feature
    }

    public LocalDate findBorrowedBookDate(Book book) {
        //TODO implement the missing feature
        return null;
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
                Log.i(TAG, "book number " + i + " : " + row);
                JSONObject isbn = (JSONObject) row.get("isbn");
                ISBN iSBN = new ISBN((Long) isbn.getOrDefault("isbnCode",00000));
                availableBooks.put(iSBN,
                        new Book(
                                row.getOrDefault("title","No title").toString()
                                , row.getOrDefault("author", "Author not mentioned").toString()
                                , iSBN
                        )
                );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
