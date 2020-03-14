package com.homework.week4;

public class Album extends Book {
    private String paperQuality;

    public Album() {
        super();
        this.paperQuality = "Unknown quality";
    }

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
