package fr.d2factory.libraryapp.library;

/**
 * This exception is thrown when a member don't have money to borrow a book
 */
public class HasNoMoneyLeftException extends RuntimeException{
    public HasNoMoneyLeftException(String message){
        super(message);
    }

}

