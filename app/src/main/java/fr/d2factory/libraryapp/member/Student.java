package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.library.Library;

public class Student extends Member {
    public Student(int memberId, float wallet, Library memberState) {
        super(memberId, wallet, memberState);
    }

    @Override
    public void payBook(int numberOfDays) {

    }
}
