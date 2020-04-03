package com.homework.week4.library;


public class Album extends Book {
    private String paperQuality;

    public Album(String name, int pages, String paperQuality) {
        super(name, pages);
        this.paperQuality = paperQuality;
    }

    @Override
    public String getDetails() {
        return "Album name: " + getName() +
                "\nNumber of pages: " + getPages() +
                "\nPaper quality: " + paperQuality;
    }

    public String getPaperQuality() {
        return paperQuality;
    }

    public void setPaperQuality(String paperQuality) {
        this.paperQuality = paperQuality;
    }
}
