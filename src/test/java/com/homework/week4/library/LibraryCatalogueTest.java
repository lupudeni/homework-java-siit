package com.homework.week4.library;

import com.homework.util.ActionStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)

public class LibraryCatalogueTest {
    private LibraryCatalogue sut;
    private List<Book> bookList;

    @Before
    public void setup() {
        bookList = new ArrayList<>();
        sut = new LibraryCatalogue(bookList);
    }

    // addNovel(String name, int pages, String type)

    @Test
    public void given_String_Name_And_Type_And_Positive_Pages_When_Add_Novel_Then_Novel_Is_Added_To_BookList_And_Return_Success_Status() {
        //Given
        String name = "Love in the Time of Cholera";
        int pages = 348;
        String type = "Magical Realism";

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).size().isEqualTo(1);
        assertThat(bookList.get(0)).isEqualTo(new Novel(name, pages, type));
        assertThat(status).isEqualTo(ActionStatus.SUCCESS);

    }

    @Test
    public void given_String_Name_And_Type_And_Negative_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "Love in the Time of Cholera";
        int pages = -348;
        String type = "Magical Realism";

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Null_Name_And_Type_And_Positive_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = null;
        int pages = 348;
        String type = null;

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Null_Name_And_Type_And_Negative_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = null;
        int pages = -348;
        String type = null;

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Empty_Name_And_Type_And_Positive_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "";
        int pages = 348;
        String type = "";

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Empty_Name_And_Type_And_Negative_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "";
        int pages = -348;
        String type = "";

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Blank_Name_And_Type_And_Positive_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "    ";
        int pages = 348;
        String type = "  ";

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Blank_Name_And_Type_And_Negative_Pages_When_Add_Novel_Then_Novel_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "    ";
        int pages = -348;
        String type = "  ";

        //When
        String status = sut.addNovel(name, pages, type);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

//    addAlbum(String name, int pages, String paperQuality)

    @Test
    public void given_String_Name_And_Type_And_Positive_Pages_When_Add_Album_Then_Album_Is_Added_To_BookList_And_Return_Success_Status() {
        //Given
        String name = "Leonardo Da Vinci ";
        int pages = 500;
        String paperQuality = "Glossy";

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).size().isEqualTo(1);
        assertThat(bookList.get(0)).isEqualTo(new Album(name, pages, paperQuality));
        assertThat(status).isEqualTo(ActionStatus.SUCCESS);
    }

    @Test
    public void given_String_Name_And_Type_And_Negative_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "Leonardo Da Vinci ";
        int pages = -500;
        String paperQuality = "Glossy";

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Null_Name_And_Type_And_Positive_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = null;
        int pages = 500;
        String paperQuality = null;

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Null_Name_And_Type_And_Negative_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = null;
        int pages = -500;
        String paperQuality = null;

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Empty_Name_And_Type_And_Positive_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "";
        int pages = 500;
        String paperQuality = "";

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Empty_Name_And_Type_And_Negative_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "";
        int pages = -500;
        String paperQuality = "";

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Blank_Name_And_Type_And_Positive_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "    ";
        int pages = 500;
        String paperQuality = "  ";

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Blank_Name_And_Type_And_Negative_Pages_When_Add_Album_Then_Album_Is_Not_Added_To_BookList_And_Return_Fail_Status() {
        //Given
        String name = "    ";
        int pages = -500;
        String paperQuality = "  ";

        //When
        String status = sut.addAlbum(name, pages, paperQuality);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    //deleteBook(int number)

    @Test
    public void given_In_Bounds_Number_When_Delete_Book_Then_Book_Is_Removed_From_BookList_And_Return_Success_Status() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);
        int number = 1;

        //When
        String status = sut.deleteBook(number);

        //Then
        assertThat(bookList).isEmpty();
        assertThat(status).isEqualTo(ActionStatus.SUCCESS);
    }

    @Test
    public void given_Out_Of_Bounds_Positive_Number_When_Delete_Book_Then_Book_Is_Not_Removed_From_BookList_And_Return_Not_Found_Status() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);
        int number = 5;

        //When
        String status = sut.deleteBook(number);

        //Then
        assertThat(bookList).size().isEqualTo(1);
        assertThat(status).isEqualTo(ActionStatus.NOT_FOUND);
    }

    @Test
    public void given_Out_Of_Bounds_Negative_Number_When_Delete_Book_Then_Book_Is_Not_Removed_From_BookList_And_Return_Not_Found_Status() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);
        int number = -5;

        //When
        String status = sut.deleteBook(number);

        //Then
        assertThat(bookList).size().isEqualTo(1);
        assertThat(status).isEqualTo(ActionStatus.NOT_FOUND);
    }

    //displayDetails(int number)

    @Test
    public void given_In_Bounds_Number_For_Novel_When_Display_Details_Then_Return_Novel_Details() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);

        Album album = new Album("Gustav Klimt", 60, "Glossy paper");
        bookList.add(album);

        int number = 1;

        //When
        String details = sut.displayDetails(number);

        //Then
        assertThat(details).isEqualTo("Novel name: " + novel.getName() +
                "\nNumber of pages: " + novel.getPages() +
                "\nNovel type: " + novel.getType());
    }

    @Test
    public void given_In_Bounds_Number_For_Album_When_Display_Details_Then_Return_Album_Details() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);

        Album album = new Album("Gustav Klimt", 60, "Glossy paper");
        bookList.add(album);

        int number = 2;

        //When
        String details = sut.displayDetails(number);

        //Then
        assertThat(details).isEqualTo("Album name: " + album.getName() +
                "\nNumber of pages: " + album.getPages() +
                "\nPaper quality: " + album.getPaperQuality());
    }

    @Test
    public void given_Out_Of_Bounds_Positive_Number_For_Book_When_Display_Details_Then_Return_Not_Found_Status() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);

        Album album = new Album("Gustav Klimt", 60, "Glossy paper");
        bookList.add(album);

        int number = 3;

        //When
        String details = sut.displayDetails(number);

        //Then
        assertThat(details).isEqualTo(ActionStatus.NOT_FOUND);
    }

    @Test
    public void given_Out_Of_Bounds_Negative_Number_For_Book_When_Display_Details_Then_Return_Not_Found_Status() {
        //Given
        Novel novel = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(novel);

        Album album = new Album("Gustav Klimt", 60, "Glossy paper");
        bookList.add(album);

        int number = -1;

        //When
        String details = sut.displayDetails(number);

        //Then
        assertThat(details).isEqualTo(ActionStatus.NOT_FOUND);
    }

}
