package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="book_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "book_price")
    private double bookPrice;
    @JoinColumn(name = "book_type",referencedColumnName = "")
    private String bookType;
}
