package fr.d2factory.libraryapp.book;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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

    public void getBooks(Context c) {
            //JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();

            try (FileReader reader = new FileReader("employees.json")) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                JSONArray booksList = (JSONArray) obj;
                System.out.println(booksList);

                //Iterate over employee array
                booksList
                    .forEach(emp -> parseEmployeeObject((JSONObject) emp));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
    }

    public String loadJSONFromAsset(Context c) {
        String json = null;
        try {
            InputStream is = c.getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
