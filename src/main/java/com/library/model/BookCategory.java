package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory {
    @Id
    @Column(name = "book_type")
    private String bookType;
    @Column(name="book_description")
    private String bookDescription;
}
