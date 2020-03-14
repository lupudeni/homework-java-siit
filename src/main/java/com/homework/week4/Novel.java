package com.homework.week4;

public class Novel extends Book {
    private String type;

    public Novel() {
        super();
        this.type = "Unknown genre";
    }

    public Novel(String name, int pages, String type) {
        super(name, pages);
        this.type = type;
    }

    @Override
    public String getDetails() {
        return "Novel name: " + getName() +
                "\nNumber of pages: " + getPages() +
                "\nNovel type: " + type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
