package fr.d2factory.libraryapp.townsville_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.ui.library.BooksAdapter;

public class LibraryActivity extends AppCompatActivity {
    NestedScrollView booksScV;
    RecyclerView mainActivityRecyclerView;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        booksScV = findViewById(R.id.navigation_dashboard_scroll_view);

    }

    private void iniRecycleView(View view) {
        // 1. get a reference to mainActivityRecyclerView
        mainActivityRecyclerView = view.findViewById(R.id.books_recycler_view);
        // this is data fr  o recycler view
        /**
         * Add data here.
         */
        getBooks();

        // 2. set layoutManger
        mainActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
        //BooksAdapter mAdapter = new BooksAdapter(tasks);
        // 4. set adapter
        //mainActivityRecyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mainActivityRecyclerView.setHasFixedSize(true);

        mainActivityRecyclerView.setAdapter(new BooksAdapter(books, new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book item) {
                Toast.makeText(getApplicationContext(), "book Clicked", Toast.LENGTH_LONG).show();

            }
        }));
    }

    private void getBooks() {
    }

}
