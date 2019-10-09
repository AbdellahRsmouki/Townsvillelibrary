package fr.d2factory.libraryapp.ui.library;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.townsville_library.R;


public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static List<Book> books;
    private final OnItemClickListener listener;


    private Book mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;
    private static View mView;

    public BooksAdapter(List<Book> books, OnItemClickListener listener) {

        this.books = books;
        this.listener = listener;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RecyclerView.ViewHolder viewHolder;

            mView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_books_list, null);
            // create ViewHolder
            viewHolder = new BooksAdapter.ViewHolder(mView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
          ViewHolder vh = (ViewHolder) viewHolder;
          Book book = books.get(position);
        vh.bind(book, listener);
        vh.bookTitle.setText(book.getTitle());
        vh.bookAuthor.setText(book.getAuthor());
        vh.bookISBN.setText(book.getIsbn().getIsbnCode()+"");
        BookRepository br = BookRepository.getBookRepository();
        if (br.isBorrowedBook(book))
            vh.bookState.setBackgroundColor(Color.parseColor("#FF6347"));
    }

    public interface OnItemClickListener {
        void onItemClick(Book item);
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookISBN;
        private ImageView bookState;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            bookTitle = itemLayoutView.findViewById(R.id.book_title);
            bookAuthor = itemLayoutView.findViewById(R.id.book_author);
            bookISBN = itemLayoutView.findViewById(R.id.book_isbnCode);
            bookState = itemLayoutView.findViewById(R.id.book_state_icon);
        }
        public void bind(final Book bk, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(bk);
                }
            });
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return books!=null? books.size():0;
    }


}


