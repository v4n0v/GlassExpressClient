package ru.glassexpress.objects;

public class CarMarks {

    Integer[] ids;
    String[] markTitles;

    public String[] getMarkTitles() {

        return markTitles;
    }

    public Integer[] getIds() {
        return ids;
    }


    public CarMarks() {
    }

    public CarMarks(Integer[] ids,
                    String[] markTitles) {
        this.ids = ids;
        this.markTitles = markTitles;
    }
}
