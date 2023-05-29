package org.gassion.LibrarySpringApp.model;

import lombok.Data;

@Data
public class BorrowedBook {
    private int bookID;
    private int personID;
}
