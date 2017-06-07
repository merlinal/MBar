package com.merlin.view.bar.model;

import android.app.Activity;

import com.merlin.core.model.BaseModel;
import com.merlin.core.util.Util;
import com.merlin.view.bar.BarConfig;
import com.merlin.view.bar.MenuListener;

import java.util.ArrayList;

/**
 * Created by ncm on 2017/4/12.
 */

public class Bar extends BaseModel {

    public final static int TYPE_LEFT = 1;
    public final static int TYPE_MIDDLE = 2;
    public final static int TYPE_RIGHT1 = 3;
    public final static int TYPE_RIGHT2 = 4;
    public final static int TYPE_MORE = 5;

    public Bar() {
        moreList = new ArrayList<>();
    }

    private int bgColorBar;
    private int bgColorMore;
    private Activity activity;
    private Menu left;
    private Menu middle;
    private Menu right1;
    private Menu right2;
    private ArrayList<Menu> moreList;

    private void check() {
        if (left == null) {
            left = BarConfig.inst().get(TYPE_LEFT);
        }
        if (middle == null) {
            middle = BarConfig.inst().get(TYPE_MIDDLE);
        }
        if (right1 == null) {
            right1 = BarConfig.inst().get(TYPE_RIGHT1);
        }
        if (right2 == null) {
            right2 = BarConfig.inst().get(TYPE_RIGHT2);
        }
        if (moreList == null) {
            moreList = new ArrayList<>();
        }
    }

    public void setTitle(String title) {
        middle.setText(title);
        middle.notifyChange();
    }

    public void addMore(Menu menuVo) {
        moreList.add(menuVo);
    }

    private void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void setLeft(Menu left) {
        this.left = left;
    }

    private void setMiddle(Menu middle) {
        this.middle = middle;
    }

    private void setRight1(Menu right1) {
        this.right1 = right1;
    }

    private void setRight2(Menu right2) {
        this.right2 = right2;
    }

    private void setBgColorBar(int bgColorBar) {
        this.bgColorBar = bgColorBar;
    }

    private void setBgColorMore(int bgColorMore) {
        this.bgColorMore = bgColorMore;
    }

    private Activity getActivity() {
        return activity;
    }

    public Menu getLeft() {
        return left;
    }

    public Menu getMiddle() {
        return middle;
    }

    public Menu getRight1() {
        return right1;
    }

    public Menu getRight2() {
        return right2;
    }

    public Menu getMore(int index) {
        if (moreList != null && index > -1 && index < moreList.size()) {
            return moreList.get(index);
        }
        return null;
    }

    public ArrayList<Menu> getMoreList() {
        return moreList;
    }

    public int getBgColorBar() {
        return bgColorBar;
    }

    public int getBgColorMore() {
        return bgColorMore;
    }

    public static class Builder {

        private Bar barVo = new Bar();
        private Menu menuVo;
        private int type;

        public Builder() {
            barVo = new Bar();
            barVo.setBgColorBar(BarConfig.inst().getBgColorBar());
            barVo.setBgColorMore(BarConfig.inst().getBgColorMore());
        }

        public Bar build() {
            save();
            barVo.check();
            if (barVo.getActivity() != null && barVo.getLeft().getListener() == null) {
                barVo.getLeft().listener(new MenuListener() {
                    @Override
                    public void onClickMenu(Menu menuVo) {
                        barVo.getActivity().onBackPressed();
                    }
                });
            }

            return barVo;
        }

        public Builder setActivity(Activity activity) {
            barVo.setActivity(activity);
            return this;
        }

        public Builder left() {
            switchType(Bar.TYPE_LEFT);
            return this;
        }

        public Builder left(int iconId) {
            switchType(Bar.TYPE_LEFT).set(iconId, null);
            return this;
        }

        public Builder left(long id, int iconId, String text, MenuListener listener) {
            switchType(Bar.TYPE_LEFT).set(iconId, text).set(listener).set(id);
            return this;
        }

        public Builder middle() {
            switchType(Bar.TYPE_MIDDLE);
            return this;
        }

        public Builder middle(String text) {
            switchType(Bar.TYPE_MIDDLE).set(0, text);
            return this;
        }

        public Builder middle(int textId) {
            switchType(Bar.TYPE_MIDDLE).set(0, Util.string(textId));
            return this;
        }

        public Builder middle(long id, int iconId, String text, MenuListener listener) {
            switchType(Bar.TYPE_MIDDLE).set(iconId, text).set(listener).set(id);
            return this;
        }

        public Builder right1(long id) {
            switchType(Bar.TYPE_RIGHT1).set(id);
            return this;
        }

        public Builder right1(long id, int iconId, String text, MenuListener listener) {
            switchType(Bar.TYPE_RIGHT1).set(iconId, text).set(listener).set(id);
            return this;
        }

        public Builder right1(long id, int iconId, int textId, MenuListener listener) {
            switchType(Bar.TYPE_RIGHT1).set(iconId, textId).set(listener).set(id);
            return this;
        }

        public Builder right2(long id) {
            switchType(Bar.TYPE_RIGHT2).set(id);
            return this;
        }

        public Builder right2(long id, int iconId, String text, MenuListener listener) {
            switchType(Bar.TYPE_RIGHT2).set(iconId, text).set(listener).set(id);
            return this;
        }

        public Builder right2(long id, int iconId, int textId, MenuListener listener) {
            switchType(Bar.TYPE_RIGHT2).set(iconId, textId).set(listener).set(id);
            return this;
        }

        public Builder more() {
            switchType(Bar.TYPE_MORE);
            return this;
        }

        public Builder more(long id, int iconId, String text, MenuListener listener) {
            switchType(Bar.TYPE_MORE).set(iconId, text).set(listener).set(id);
            return this;
        }

        private Builder switchType(int targetType) {
            if (type > 0) {
                if (targetType != type || targetType == Bar.TYPE_MORE) {
                    save();
                }
            }
            type = targetType;
            menuVo = BarConfig.inst().get(type);
            return this;
        }

        private void save() {
            switch (type) {
                case Bar.TYPE_LEFT:
                    barVo.setLeft(menuVo);
                    break;
                case Bar.TYPE_MIDDLE:
                    barVo.setMiddle(menuVo);
                    break;
                case Bar.TYPE_RIGHT1:
                    barVo.setRight1(menuVo);
                    break;
                case Bar.TYPE_RIGHT2:
                    barVo.setRight2(menuVo);
                    break;
                case Bar.TYPE_MORE:
                    barVo.addMore(menuVo);
                    break;
            }
        }

        public Builder set(long id) {
            menuVo.id(id);
            return this;
        }

        public Builder set(int iconId, String text) {
            menuVo.iconId(iconId).text(text);
            return this;
        }

        public Builder set(int iconId, int textId) {
            menuVo.iconId(iconId).text(Util.string(textId));
            return this;
        }

        public Builder set(boolean isNotice, String notice) {
            menuVo.isNotice(isNotice).notice(notice);
            return this;
        }

        public Builder setText(String text, int descColor, int descSize) {
            menuVo.text(text).textColor(descColor).textSize(descSize);
            return this;
        }

        public Builder setDesc(String desc, int descColor, int descSize) {
            menuVo.desc(desc).descColor(descColor).descSize(descSize);
            return this;
        }

        public Builder set(int iconId, String text, int textColor, int textSize, String desc, int descColor, int descSize, boolean isNotice, String notice, MenuListener listener) {
            menuVo.set(iconId, text, textColor, textSize, desc, descColor, descSize, isNotice, notice, listener);
//            menuBuilder.iconId(iconId).listener(listener)
//                    .text(text).textColor(textColor).textSize(textSize)
//                    .desc(desc).descColor(descColor).descSize(descSize)
//                    .isNotice(isNotice).noticeCount(noticeCount);
            return this;
        }

        public Builder set(MenuListener listener) {
            menuVo.listener(listener);
            return this;
        }

    }

}
