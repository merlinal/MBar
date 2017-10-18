package com.merlin.view.bar.model;

import android.databinding.BaseObservable;

import com.merlin.core.util.MUtil;
import com.merlin.core.util.MVerify;
import com.merlin.view.bar.MenuListener;

import java.io.Serializable;

/**
 * Created by ncm on 2017/4/12.
 */

public class Menu extends BaseObservable implements Serializable {

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

    private boolean isNotice;
    private String notice;

    private int iconLeftId;
    private int iconRightId;

    private MenuListener listener;

    public static Menu newInst() {
        return new Menu();
    }

    public Menu clone() {
        return Menu.newInst().set(id, iconLeftId, iconRightId, 0, textColor, textSize,
                0, descColor, descSize, false, null, listener);
    }

    public boolean isHide() {
        return MVerify.isBlank(text) && MVerify.isBlank(desc) && iconLeftId == 0 && iconRightId == 0;
    }

    public Menu set(long id, int iconLeftId, int iconRightId, int textId, int textColor, float textSize,
                    int descId, int descColor, float descSize, boolean isNotice, String notice, MenuListener listener) {
        id(id).listener(listener).
                iconLeftId(iconLeftId).iconRightId(iconRightId)
                .textId(textId).textColor(textColor).textSize(textSize)
                .descId(descId).descColor(descColor).descSize(descSize)
                .isNotice(isNotice).notice(notice);
        return this;
    }

    public Menu id(long id) {
        this.id = id;
        return this;
    }

    public Menu textId(int textId) {
        if (textId != 0) {
            this.text = MUtil.string(textId);
        }
        return this;
    }

    public Menu text(String text) {
        this.text = text;
        return this;
    }

    public Menu textColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public Menu textSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public Menu descId(int descId) {
        if (descId != 0) {
            this.desc = MUtil.string(descId);
        }
        return this;
    }

    public Menu desc(String desc) {
        this.desc = desc;
        return this;
    }

    public Menu descColor(int descColor) {
        this.descColor = descColor;
        return this;
    }

    public Menu descSize(float descSize) {
        this.descSize = descSize;
        return this;
    }

    public Menu iconLeftId(int iconLeftId) {
        this.iconLeftId = iconLeftId;
        return this;
    }

    public Menu iconRightId(int iconRightId) {
        this.iconRightId = iconRightId;
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

    public int getIconLeftId() {
        return iconLeftId;
    }

    public int getIconRightId() {
        return iconRightId;
    }
}
