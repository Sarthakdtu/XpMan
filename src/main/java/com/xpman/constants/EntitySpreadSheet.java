package com.xpman.constants;

public enum EntitySpreadSheet {
    ACCOUNT("Account", "B"),
    CATEGORY("Category", "B"),
    SUBCATEGORY("SubCategory", "C"),
    EXPENSE("Expense", "F");

    private final String sheet;
    private final String range;

    EntitySpreadSheet(String sheet, String range){
        this.sheet = sheet;
        this.range = range;
    }

    public String getSheet() {
        return sheet;
    }

    public String getRange() {
        return range;
    }
}
