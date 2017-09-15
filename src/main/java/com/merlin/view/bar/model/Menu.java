package com.merlin.view.bar.model;

import com.merlin.core.model.BaseModel;
import com.merlin.view.bar.MenuListener;

/**
 * Created by ncm on 2017/4/12.
 */

public class Menu extends BaseModel {

    private Menu() {
    }

    private long id;

    private int type;
    private int bgColor;

    private String text;
    private int textColor;
    private float textSize;

    private String desc;
    private int descColor;
    private float descSize;

    private int iconId;

    private boolean isNotice;
    private String notice;

    private MenuListener listener;


    public static Menu newInst() {
        return new Menu();
    }

    public Menu set(int iconId, String text, int textColor, int textSize, String desc, int descColor, int descSize, boolean isNotice, String notice, MenuListener listener) {
        iconId(iconId).listener(listener)
                .text(text).textColor(textColor).textSize(textSize)
                .desc(desc).descColor(descColor).descSize(descSize)
                .isNotice(isNotice).notice(notice);
        return this;
    }

    public Menu id(long id) {
        this.id = id;
        return this;
    }

    public Menu text(String text) {
        this.text = text;
        return this;
    }

    public Menu textColor(int textColor) {
        if (textColor != 0) {
            this.textColor = textColor;
        }
        return this;
    }

    public Menu textSize(float textSize) {
        if (textSize != 0) {
            this.textSize = textSize;
        }
        return this;
    }

    public Menu desc(String desc) {
        this.desc = desc;
        return this;
    }

    public Menu descColor(int descColor) {
        if (descColor != 0) {
            this.descColor = descColor;
        }
        return this;
    }

    public Menu descSize(float descSize) {
        if (descSize != 0) {
            this.descSize = descSize;
        }
        return this;
    }

    public Menu iconId(int iconId) {
        if (iconId != 0) {
            this.iconId = iconId;
        }
        return this;
    }

    public Menu isNotice(boolean isNotice) {
        this.isNotice = isNotice;
        return this;
    }

    public Menu notice(String notice) {
        this.notice = notice;
        return this;
    }

    public Menu type(int type) {
        this.type = type;
        return this;
    }

    public Menu bgColor(int color) {
        this.bgColor = color;
        return this;
    }

    public Menu listener(MenuListener listener) {
        this.listener = listener;
        return this;
    }

    public long getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public int getDescColor() {
        return descColor;
    }

    public float getDescSize() {
        return descSize;
    }

    public int getIconId() {
        return iconId;
    }

    public boolean isNotice() {
        return isNotice;
    }

    public String getNotice() {
        return notice;
    }

    public MenuListener getListener() {
        return listener;
    }

    public String getText() {
        return text;
    }

    public String getDesc() {
        return desc;
    }

}
