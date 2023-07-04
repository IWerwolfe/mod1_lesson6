package org.example.web.dto;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String author;
    private String title;
    private Integer size;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
