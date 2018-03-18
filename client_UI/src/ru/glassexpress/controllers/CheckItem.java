package ru.glassexpress.controllers;

public class CheckItem {
    boolean selected;
    String displayName;
    String internalName;

    public boolean isChecked() {
        return selected;
    }

    public void setChecked(boolean checked) {
        this.selected = checked;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }
}