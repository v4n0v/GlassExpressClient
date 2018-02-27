package ru.glassexpress.objects.builders;

import ru.glassexpress.objects.TableGoodsInStockRow;

public class TableGoodsBuilder {


    public TableGoodsBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TableGoodsBuilder setGlassType(int glassType) {
        this.glassType = glassType;
        return this;
    }

    public TableGoodsBuilder setGlassOption(int glassOption) {
        this.glassOption = glassOption;
        return this;
    }

    public TableGoodsBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TableGoodsBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public TableGoodsBuilder setPriceIn(float priceIn) {
        this.priceIn = priceIn;
        return this;
    }

    public TableGoodsBuilder setInsertPrice(float insertPrice) {
        this.insertPrice = insertPrice;
        return this;
    }

    public TableGoodsBuilder setCountWh1(int countWh1) {
        this.countWh1 = countWh1;
        return this;
    }

    public TableGoodsBuilder setCountWh2(int countWh2) {
        this.countWh2 = countWh2;
        return this;
    }

    public TableGoodsBuilder setCountRemainder(int countRemainder) {
        this.countRemainder = countRemainder;
        return this;
    }

    private int id;
    private int glassType;
    private int glassOption;
    private String description;
    private float price;
    private float priceIn;
    private float insertPrice;
    private int countWh1;
    private int countWh2;
    private int countRemainder;

    public TableGoodsInStockRow build() {
        TableGoodsInStockRow table = new TableGoodsInStockRow();
        table.setId(id);
        table.setGlassType(glassType);
        table.setGlassOption(glassOption);
        table.setDescription(description);
        table.setPrice(price);
        table.setPriceIn(priceIn);
        table.setInsertPrice(insertPrice);
        table.setCountRemainder(countRemainder);
        table.setCountWh1(countWh1);
        table.setCountWh2(countWh2);
        return table;
        }


}
