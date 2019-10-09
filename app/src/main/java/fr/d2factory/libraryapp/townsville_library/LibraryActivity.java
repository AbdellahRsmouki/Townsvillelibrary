package fr.d2factory.libraryapp.townsville_library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Student;
import fr.d2factory.libraryapp.ui.library.BooksAdapter;

import static androidx.core.os.LocaleListCompat.create;

public class LibraryActivity extends AppCompatActivity {
    private static final String TAG = "LibraryActivity";
    NestedScrollView booksScV;
    RecyclerView mainActivityRecyclerView;
    private ArrayList<Book> books = new ArrayList<>();
    BookRepository br;
    private String memberType = "MEMBER";
    private String member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        booksScV = findViewById(R.id.navigation_dashboard_scroll_view);
        member = getIntent().getStringExtra(memberType);
        try {
            iniRecycleView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniRecycleView() throws IOException {
        // 1. get a reference to mainActivityRecyclerView
        mainActivityRecyclerView = findViewById(R.id.books_recycler_view);
        // this is data fr  o recycler view
        /**
         * Add data here.
         */
        Log.i(TAG, "iniRecycleView: ...");
        br = BookRepository.getBookRepository();
        updateBooksList();

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
                if (member.equals(getResources().getString(R.string.student))) {
                    Dialog dialog = createDialog();
                    dialog.show();
                }else{

                }
            }
        }));
    }

    private void updateBooksList() {
        //books = br.getBooks(this);
        br.loadJSONFromAsset(this);
        books.addAll(br.getAvailableBooks());
    }

    public Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrow a book");

        LinearLayout inputGroup= new LinearLayout(this);
        inputGroup.setOrientation(LinearLayout.VERTICAL);

        final EditText memberId = new EditText(this);
        memberId.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_NUMBER);
        memberId.setHint(R.string.memberId);
        inputGroup.addView(memberId);

        final EditText wallet = new EditText(this);
        wallet.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_NUMBER);
        wallet.setHint(R.string.wallet);
        inputGroup.addView(wallet);

        builder.setView(inputGroup);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "book borrowed", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

}
