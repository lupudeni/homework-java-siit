package com.homework.week4.library;

import com.homework.util.ActionStatus;

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalogue {
    private List<Book> bookList;

    public LibraryCatalogue() {
        bookList = new ArrayList<>();
    }

    public LibraryCatalogue(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void printList() {
        int i = 1;
        for (Book book : bookList) {
            System.out.println(i + ". " + book.getName());
            i++;
        }
    }

    String addNovel(String name, int pages, String type) {
        if (name == null || name.trim().isEmpty() || type == null || type.trim().isEmpty() || pages <= 0) {
            return ActionStatus.FAIL;
        }
        bookList.add(new Novel(name, pages, type));
        return ActionStatus.SUCCESS;
    }

    String addAlbum(String name, int pages, String paperQuality) {
        if (name == null || name.trim().isEmpty() || paperQuality == null || paperQuality.trim().isEmpty() || pages <= 0) {
            return ActionStatus.FAIL;
        }
        bookList.add(new Album(name, pages, paperQuality));
        return ActionStatus.SUCCESS;
    }

    String deleteBook(int number) {
        if ((number > 0) && (number <= (bookList.size()))) {
            bookList.remove((number - 1));
            return ActionStatus.SUCCESS;
        } else {
            return ActionStatus.NOT_FOUND;
        }
    }

    String displayDetails(int number) {
        if ((number > 0) && (number <= (bookList.size()))) {
            Book book = bookList.get(number - 1);
            return book.getDetails();
        } else {
            return ActionStatus.NOT_FOUND;
        }
    }
}

