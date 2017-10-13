package com.merlin.view.bar.model;

import android.app.Activity;
import android.databinding.BaseObservable;

import com.merlin.core.util.MUtil;
import com.merlin.view.bar.BarConfig;
import com.merlin.view.bar.MenuListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ncm on 2017/4/12.
 */

public class Bar extends BaseObservable implements Serializable {

    public final static int TYPE_LEFT = 1;
    public final static int TYPE_MIDDLE = 2;
    public final static int TYPE_RIGHT1 = 3;
    public final static int TYPE_RIGHT2 = 4;
    public final static int TYPE_MORE = 5;

    private int bgColorBar;
    private int bgColorMore;
    private Activity activity;
    private Menu left;
    private Menu middle;
    private Menu right1;
    private Menu right2;
    private ArrayList<Menu> moreList;

    private Bar() {
    }

    public static Bar newInstance() {
        return new Bar();
    }

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
        middle().text(title);
    }

//    public void addMore(Menu menuVo) {
//        moreList.add(menuVo);
//    }

//    private void setActivity(Activity activity) {
//        this.activity = activity;
//    }
//
//    private void setLeft(Menu left) {
//        this.left = left;
//    }
//
//    private void setMiddle(Menu middle) {
//        this.middle = middle;
//    }
//
//    private void setRight1(Menu right1) {
//        this.right1 = right1;
//    }
//
//    private void setRight2(Menu right2) {
//        this.right2 = right2;
//    }

    public Bar bgColorBar(int bgColorBar) {
        this.bgColorBar = bgColorBar;
        return this;
    }

    public Bar bgColorMore(int bgColorMore) {
        this.bgColorMore = bgColorMore;
        return this;
    }

    private Activity getActivity() {
        return activity;
    }

    public Bar activity(Activity activity) {
        this.activity = activity;
        return this;
    }

//    public Menu getLeft() {
//        return left;
//    }
//
//    public Menu getMiddle() {
//        return middle;
//    }
//
//    public Menu getRight1() {
//        return right1;
//    }
//
//    public Menu getRight2() {
//        return right2;
//    }

//    public Menu getMore(int index) {
//        if (moreList != null && index > -1 && index < moreList.size()) {
//            return moreList.get(index);
//        }
//        return null;
//    }
//
//    public ArrayList<Menu> getMore() {
//        return moreList;
//    }

    public int getBgColorBar() {
        return bgColorBar;
    }

    public int getBgColorMore() {
        return bgColorMore;
    }

//***************************************左边按钮***************************************

    public Menu left() {
        if (left == null) {
            left = BarConfig.inst().get(TYPE_LEFT);
            left.listener(new MenuListener() {
                @Override
                public void onClickMenu(Menu menu) {
                    if (activity != null) {
                        activity.onBackPressed();
                    }
                }
            });
        }
        return left;
    }

    public Menu left(int iconId) {
        left().iconId(iconId);
        return left;
    }

    public Menu left(long id, int iconId, String text, MenuListener listener) {
        left().iconId(iconId).text(text).listener(listener);
        return left;
    }

//***************************************中间按钮***************************************

    public Menu middle() {
        if (middle == null) {
            middle = BarConfig.inst().get(TYPE_MIDDLE);
        }
        return middle;
    }

    public Menu middle(String text) {
        middle().text(text);
        return middle;
    }

    public Menu middle(int textId) {
        middle().text(MUtil.string(textId));
        return middle;
    }

    public Menu middle(long id, int iconId, String text, MenuListener listener) {
        middle().id(id).iconId(iconId).text(text).listener(listener);
        return middle;
    }

//***************************************最右边按钮***************************************

    public Menu right1() {
        if (right1 == null) {
            right1 = BarConfig.inst().get(TYPE_RIGHT1);
        }
        return right1;
    }

    public Menu right1(int iconId) {
        right1().iconId(iconId);
        return right1;
    }

    public Menu right1(String text) {
        right1().text(text);
        return right1;
    }

    public Menu right1(long id, int iconId, String text, MenuListener listener) {
        right1().iconId(iconId).text(text).listener(listener);
        return right1;
    }

//***************************************次右边按钮***************************************

    public Menu right2() {
        if (right2 == null) {
            right2 = BarConfig.inst().get(TYPE_RIGHT2);
        }
        return right2;
    }

    public Menu right2(int iconId) {
        right2().iconId(iconId);
        return right2;
    }

    public Menu right2(String text) {
        right2().text(text);
        return right2;
    }

    public Menu right2(long id, int iconId, String text, MenuListener listener) {
        right2().id(id).iconId(iconId).text(text).listener(listener);
        return right2;
    }

//***************************************弹出框***************************************

    public ArrayList<Menu> more() {
        if (moreList == null) {
            moreList = new ArrayList<>();
        }
        return moreList;
    }

    public ArrayList<Menu> more(long id, int iconId, String text, MenuListener listener) {
        more().add(BarConfig.inst().get(TYPE_MORE).id(id).iconId(iconId).text(text).listener(listener));
        return moreList;
    }

    public Menu more(int index) {
        if (index < 0 || index >= more().size()) {
            return null;
        }
        return moreList.get(index);
    }

    public ArrayList<Menu> moreReset(ArrayList<Menu> menus) {
        more().clear();
        moreList.addAll(menus);
        return moreList;
    }

    public ArrayList<Menu> moreRemove(int index) {
        if (index > -1 && index < more().size()) {
            moreList.remove(index);
        }
        return moreList;
    }


//    public static class Builder {
//
//        private Bar barVo = new Bar();
//        private Menu menuVo;
//        private int type;
//
//        public Bar() {
//            barVo = new Bar();
//            barVo.setBgColorBar(BarConfig.inst().getBgColorBar());
//            barVo.setBgColorMore(BarConfig.inst().getBgColorMore());
//        }
//
//        public Bar build() {
//            save();
//            barVo.check();
//            if (barVo.getActivity() != null && barVo.getLeft().getListener() == null) {
//                barVo.getLeft().listener(new MenuListener() {
//                    @Override
//                    public void onClickMenu(Menu menuVo) {
//                        barVo.getActivity().onBackPressed();
//                    }
//                });
//            }
//
//            return barVo;
//        }
//
//        public Bar setActivity(Activity activity) {
//            barVo.setActivity(activity);
//            return this;
//        }
//
//        public Bar left() {
//            switchType(Bar.TYPE_LEFT);
//            return this;
//        }
//
//        public Bar left(int iconId) {
//            switchType(Bar.TYPE_LEFT).set(iconId, null);
//            return this;
//        }
//
//        public Bar left(long id, int iconId, String text, MenuListener listener) {
//            switchType(Bar.TYPE_LEFT).set(iconId, text).set(listener).set(id);
//            return this;
//        }
//
//        public Bar middle() {
//            switchType(Bar.TYPE_MIDDLE);
//            return this;
//        }
//
//        public Bar middle(String text) {
//            switchType(Bar.TYPE_MIDDLE).set(0, text);
//            return this;
//        }
//
//        public Bar middle(int textId) {
//            switchType(Bar.TYPE_MIDDLE).set(0, MUtil.string(textId));
//            return this;
//        }
//
//        public Bar middle(long id, int iconId, String text, MenuListener listener) {
//            switchType(Bar.TYPE_MIDDLE).set(iconId, text).set(listener).set(id);
//            return this;
//        }
//
//        public Bar right1(long id, int iconId, MenuListener listener) {
//            switchType(Bar.TYPE_RIGHT1).set(iconId, null).set(listener).set(id);
//            return this;
//        }
//
//        public Bar right1(long id, int iconId, String text, MenuListener listener) {
//            switchType(Bar.TYPE_RIGHT1).set(iconId, text).set(listener).set(id);
//            return this;
//        }
//
//        public Bar right1(long id, int iconId, int textId, MenuListener listener) {
//            switchType(Bar.TYPE_RIGHT1).set(iconId, textId).set(listener).set(id);
//            return this;
//        }
//
//        public Bar right2(long id, int iconId, MenuListener listener) {
//            switchType(Bar.TYPE_RIGHT2).set(iconId, null).set(listener).set(id);
//            return this;
//        }
//
//        public Bar right2(long id, int iconId, String text, MenuListener listener) {
//            switchType(Bar.TYPE_RIGHT2).set(iconId, text).set(listener).set(id);
//            return this;
//        }
//
//        public Bar right2(long id, int iconId, int textId, MenuListener listener) {
//            switchType(Bar.TYPE_RIGHT2).set(iconId, textId).set(listener).set(id);
//            return this;
//        }
//
//        public Bar more() {
//            switchType(Bar.TYPE_MORE);
//            return this;
//        }
//
//        public Bar more(long id, int iconId, String text, MenuListener listener) {
//            switchType(Bar.TYPE_MORE).set(iconId, text).set(listener).set(id);
//            return this;
//        }
//
//        private Builder switchType(int targetType) {
//            if (type > 0) {
//                if (targetType != type || targetType == Bar.TYPE_MORE) {
//                    save();
//                }
//            }
//            type = targetType;
//            menuVo = BarConfig.inst().get(type);
//            return this;
//        }
//
//        private void save() {
//            switch (type) {
//                case Bar.TYPE_LEFT:
//                    barVo.setLeft(menuVo);
//                    break;
//                case Bar.TYPE_MIDDLE:
//                    barVo.setMiddle(menuVo);
//                    break;
//                case Bar.TYPE_RIGHT1:
//                    barVo.setRight1(menuVo);
//                    break;
//                case Bar.TYPE_RIGHT2:
//                    barVo.setRight2(menuVo);
//                    break;
//                case Bar.TYPE_MORE:
//                    barVo.addMore(menuVo);
//                    break;
//            }
//        }
//
//        public Bar set(long id) {
//            menuVo.id(id);
//            return this;
//        }
//
//        public Bar set(int iconId, String text) {
//            menuVo.iconId(iconId).text(text);
//            return this;
//        }
//
//        public Bar set(int iconId, int textId) {
//            menuVo.iconId(iconId).text(MUtil.string(textId));
//            return this;
//        }
//
//        public Bar set(boolean isNotice, String notice) {
//            menuVo.isNotice(isNotice).notice(notice);
//            return this;
//        }
//
//        public Bar setText(String text, int descColor, int descSize) {
//            menuVo.text(text).textColor(descColor).textSize(descSize);
//            return this;
//        }
//
//        public Bar setDesc(String desc, int descColor, int descSize) {
//            menuVo.desc(desc).descColor(descColor).descSize(descSize);
//            return this;
//        }
//
//        public Bar set(int iconId, String text, int textColor, int textSize, String desc, int descColor, int descSize, boolean isNotice, String notice, MenuListener listener) {
//            menuVo.set(iconId, text, textColor, textSize, desc, descColor, descSize, isNotice, notice, listener);
////            menuBuilder.iconId(iconId).listener(listener)
////                    .text(text).textColor(textColor).textSize(textSize)
////                    .desc(desc).descColor(descColor).descSize(descSize)
////                    .isNotice(isNotice).noticeCount(noticeCount);
//            return this;
//        }
//
//        public Bar set(MenuListener listener) {
//            menuVo.listener(listener);
//            return this;
//        }
//
//    }

}
