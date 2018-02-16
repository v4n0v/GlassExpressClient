package ru.glassexpress;

public class Cars {

    Integer[] ids;
    String[] markTitles;

    public String[] getMarkTitles() {

        return markTitles;
    }

    public Integer[] getIds() {
        return ids;
    }


    public Cars() {
    }

    public Cars(Integer[] ids,
                String[] markTitles) {
        this.ids = ids;
        this.markTitles = markTitles;
    }
}
