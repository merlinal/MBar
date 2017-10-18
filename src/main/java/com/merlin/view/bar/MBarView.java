package com.merlin.view.bar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.merlin.core.util.MUtil;
import com.merlin.core.util.MVerify;
import com.merlin.core.util.UiUtil;
import com.merlin.view.bar.databinding.ViewBarBinding;
import com.merlin.view.bar.databinding.ViewBarMenuBinding;
import com.merlin.view.bar.model.Menu;
import com.merlin.view.recycler.MRecyclerView;
import com.merlin.view.recycler.RecyclerViewHolder;
import com.merlin.view.recycler.adapter.AbstractRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by ncm on 2017/4/12.
 */

public class MBarView extends RelativeLayout {

    public MBarView(Context context) {
        this(context, null);
    }

    public MBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MBarView);

        bgColor = a.getColor(R.styleable.MBarView_bgColor, MUtil.color(R.color.primary));
        bgColorPopup = a.getColor(R.styleable.MBarView_bgColor, MUtil.color(R.color.primary));
        bgResourcePopup = a.getResourceId(R.styleable.MBarView_bgResourcePopup, R.drawable.bg_popup_menu_drop);
        dividerColor = a.getColor(R.styleable.MBarView_more_dividerColor, MUtil.color(R.color.divider));
        dividerHeight = (int) a.getDimension(R.styleable.MBarView_more_dividerHeight, 0);
        popupAlpha = a.getFloat(R.styleable.MBarView_more_alpha, 1f);
        popupWidth = (int) a.getDimension(R.styleable.MBarView_more_width, 0);
        if (popupWidth == 0) {
            popupWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        popupMaxHeight = (int) a.getDimension(R.styleable.MBarView_more_maxHeight, 0);
        itemHeight = (int) a.getDimension(R.styleable.MBarView_more_itemHeight, 0);

        left = Menu.newInst()
                .iconLeftId(a.getResourceId(R.styleable.MBarView_left_iconLeft, 0))
                .iconRightId(a.getResourceId(R.styleable.MBarView_left_iconRight, 0))
                .textId(a.getResourceId(R.styleable.MBarView_left_text, 0))
                .textColor(a.getColor(R.styleable.MBarView_left_textColor, MUtil.color(R.color.white)))
                .textSize(a.getDimension(R.styleable.MBarView_left_textSize, MUtil.dimen(R.dimen.font_normal)))
                .descId(a.getResourceId(R.styleable.MBarView_left_desc, 0))
                .descColor(a.getColor(R.styleable.MBarView_left_descColor, MUtil.color(R.color.white)))
                .descSize(a.getDimension(R.styleable.MBarView_left_descSize, MUtil.dimen(R.dimen.font_small)));
        middle = Menu.newInst()
                .iconLeftId(a.getResourceId(R.styleable.MBarView_middle_iconLeft, 0))
                .iconRightId(a.getResourceId(R.styleable.MBarView_middle_iconRight, 0))
                .textId(a.getResourceId(R.styleable.MBarView_middle_text, 0))
                .textColor(a.getColor(R.styleable.MBarView_middle_textColor, MUtil.color(R.color.white)))
                .textSize(a.getDimension(R.styleable.MBarView_middle_textSize, MUtil.dimen(R.dimen.font_large)))
                .descId(a.getResourceId(R.styleable.MBarView_middle_desc, 0))
                .descColor(a.getColor(R.styleable.MBarView_middle_descColor, MUtil.color(R.color.white)))
                .descSize(a.getDimension(R.styleable.MBarView_middle_descSize, MUtil.dimen(R.dimen.font_small)));
        right1 = Menu.newInst()
                .iconLeftId(a.getResourceId(R.styleable.MBarView_right1_iconLeft, 0))
                .iconRightId(a.getResourceId(R.styleable.MBarView_right1_iconRight, 0))
                .textId(a.getResourceId(R.styleable.MBarView_right1_text, 0))
                .textColor(a.getColor(R.styleable.MBarView_right_textColor, MUtil.color(R.color.white)))
                .textSize(a.getDimension(R.styleable.MBarView_right_textSize, MUtil.dimen(R.dimen.font_normal)))
                .descId(a.getResourceId(R.styleable.MBarView_right1_desc, 0))
                .descColor(a.getColor(R.styleable.MBarView_right_descColor, MUtil.color(R.color.white)))
                .descSize(a.getDimension(R.styleable.MBarView_right_descSize, MUtil.dimen(R.dimen.font_small)));
        right2 = right1.clone()
                .iconLeftId(a.getResourceId(R.styleable.MBarView_right2_iconLeft, 0))
                .iconRightId(a.getResourceId(R.styleable.MBarView_right2_iconRight, 0))
                .textId(a.getResourceId(R.styleable.MBarView_right2_text, 0))
                .descId(a.getResourceId(R.styleable.MBarView_right2_desc, 0));
        more = Menu.newInst()
                .textColor(a.getColor(R.styleable.MBarView_right_textColor, MUtil.color(R.color.white)))
                .textSize(a.getDimension(R.styleable.MBarView_right_textSize, MUtil.dimen(R.dimen.font_normal)))
                .descColor(a.getColor(R.styleable.MBarView_right_descColor, MUtil.color(R.color.white)))
                .descSize(a.getDimension(R.styleable.MBarView_right_descSize, MUtil.dimen(R.dimen.font_small)));
        moreList = new ArrayList<>();

        binding = DataBindingUtil.inflate(MUtil.inflater(), R.layout.view_bar, this, true);
        binding.setBgColor(bgColor);
        binding.setLeft(left);
        binding.setMiddle(middle);
        binding.setRight1(right1);
        binding.setRight2(right2);
    }

    private SparseArray<Dialog> dialogs = new SparseArray(4);

    private int bgColor;
    private int bgColorPopup;
    private int bgResourcePopup;
    private int dividerColor;
    private int dividerHeight;
    private int popupWidth;
    private int popupMaxHeight;
    private float popupAlpha;
    private int itemHeight;

    private Menu left;
    private Menu middle;
    private Menu right1;
    private Menu right2;
    private Menu more;
    private ArrayList<Menu> moreList;

    private ViewBarBinding binding;

    public void showMoreTopLeft(boolean cancelable) {
        showMore(0, popupWidth, WindowManager.LayoutParams.WRAP_CONTENT, 0, getBottom(), Gravity.TOP | Gravity.LEFT, R.style.anim_top_left, cancelable);
    }

    public void showMoreTopRight(boolean cancelable) {
        showMore(1, popupWidth, WindowManager.LayoutParams.WRAP_CONTENT, 0, getBottom(), Gravity.TOP | Gravity.RIGHT, R.style.anim_top_right, cancelable);
    }

    public void showMoreTopMiddle(boolean cancelable) {
        showMore(2, popupWidth, WindowManager.LayoutParams.WRAP_CONTENT, 0, getBottom(), Gravity.TOP | Gravity.CENTER, R.style.anim_top_middle, cancelable);
    }

    public void showMoreMiddle(boolean cancelable) {
        showMore(3, popupWidth, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, Gravity.CENTER, R.style.anim_middle, cancelable);
    }

    public void hideMore() {
        for (int i = 0; i < dialogs.size(); i++) {
            Dialog dialog = dialogs.valueAt(i);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void onBackPressed(final Activity activity) {
        if (activity != null) {
            left.listener(new MenuListener() {
                @Override
                public void onClickMenu(Menu menu) {
                    activity.onBackPressed();
                }
            });
        }
    }

    private void showMore(int index, int width, int height, int x, int y, int gravity, int anim, boolean cancelable) {
        if (MVerify.isEmpty(moreList)) {
            return;
        }
        Dialog dialog = dialogs.get(index);
        if (dialog == null) {
            dialog = UiUtil.showDialog(getContext(), getMenuMoreView(), width, height, x, y, 0, anim, gravity, cancelable);
            dialogs.put(index, dialog);
        } else {
            dialog.show();
        }
    }

    private View getMenuMoreView() {
        final MRecyclerView recyclerView = new MRecyclerView(getContext());
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        if (bgResourcePopup != 0) {
            recyclerView.setBackgroundResource(bgResourcePopup);
        } else {
            recyclerView.setBackgroundColor(bgColorPopup);
        }
        recyclerView.setAlpha(popupAlpha);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.set(MRecyclerView.MODE_LIST, LinearLayoutManager.VERTICAL, 0, dividerHeight, 0, 0, dividerColor);
        //设置最大高度
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (popupMaxHeight == 0) {
                    popupMaxHeight = getWidth();
                }
                if (v.getHeight() > popupMaxHeight) {
                    v.getLayoutParams().height = popupMaxHeight;
                    v.setLayoutParams(v.getLayoutParams());
                }
            }
        });

        recyclerView.setAdapter(new AbstractRecyclerAdapter<Menu>(moreList) {

            @Override
            public ViewDataBinding getItemBinding(ViewGroup parent, int viewType) {
                return DataBindingUtil.inflate(MUtil.inflater(), R.layout.view_bar_menu, parent, false);
            }

            @Override
            public void onBindViewHolder(RecyclerViewHolder holder, int position) {
                ViewBarMenuBinding binding = (ViewBarMenuBinding) holder.getBinding();
                binding.setModel(getData(position));
                if (itemHeight != 0) {
                    binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight));
                }
                binding.executePendingBindings();
            }
        });

        return recyclerView;
    }

    public Menu more() {
        Menu menu = more.clone();
        moreList.add(menu);
        return menu;
    }

    public void clearMore() {
        moreList.clear();
    }

    public Menu left() {
        return left;
    }

    public Menu middle() {
        return middle;
    }

    public Menu right1() {
        return right1;
    }

    public Menu right2() {
        return right2;
    }
}
