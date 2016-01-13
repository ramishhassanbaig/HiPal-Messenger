package com.example.ramish.hipal_messenger.model;

/**
 * Created by ramish on 1/13/2016.
 */
public class SliderMenuListItem {
    private String title;
    private int icon;
    private int count;
    private boolean countVisible;

    public SliderMenuListItem(String title, int icon, int count, boolean countVisible) {
        this.title = title;
        this.icon = icon;
        this.count = count;
        this.countVisible = countVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCountVisible() {
        return countVisible;
    }

    public void setCountVisible(boolean countVisible) {
        this.countVisible = countVisible;
    }
}
