package com.merlin.view.bar;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
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
import com.merlin.view.bar.model.Bar;
import com.merlin.view.bar.model.Menu;
import com.merlin.view.recycler.MRecyclerView;
import com.merlin.view.recycler.RecyclerViewHolder;
import com.merlin.view.recycler.adapter.OnBindDataListener;
import com.merlin.view.recycler.adapter.RecyclerAdapter;

/**
 * Created by ncm on 2017/4/12.
 */

public class MBarView extends RelativeLayout {

    public MBarView(Context context) {
        super(context);
    }

    public MBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ViewBarBinding binding;
    private Dialog moreDialog;
    private Bar bar;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        binding = DataBindingUtil.bind(this);
    }

    public void apply(Bar bar) {
        this.bar = bar;
        binding.setModel(bar);
    }

    public Bar model() {
        return bar;
    }

    public void showMoreTopRight() {
        showMore(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, getBottom(), Gravity.TOP | Gravity.RIGHT, true, 0);
    }

    public void showMoreTopMiddle() {
        showMore(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, getBottom(), Gravity.TOP | Gravity.CENTER, true, 0);
    }

    public void showMoreMiddle() {
        showMore(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0, Gravity.CENTER, true, 0);
    }

    public void hideMore() {
        if (moreDialog != null && moreDialog.isShowing()) {
            moreDialog.dismiss();
        }
    }

    public void showMore(int width, int height, int x, int y, int gravity, boolean cancelable, float alpha) {
        if (MVerify.isEmpty(bar.more())) {
            return;
        }
        if (moreDialog == null) {
            moreDialog = UiUtil.showDialog(getContext(), getMenuMoreView(alpha), width, height, x, y, 0, R.style.anim_from_bottom, gravity, cancelable);
        } else {
            moreDialog.show();
        }
    }

    private View getMenuMoreView(float alpha) {
        MRecyclerView recyclerView = new MRecyclerView(getContext());
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.set(MRecyclerView.MODE_LIST, LinearLayoutManager.VERTICAL, 0, MUtil.dp2px(1), 0, 0, 0xffffffff);

        RecyclerAdapter adapter = new RecyclerAdapter(true);
        adapter.add(R.layout.view_bar_menu, listener, bar.more());
        recyclerView.setBackgroundColor(bar.getBgColorMore());
        recyclerView.setAlpha(alpha <= 0 ? 0.8f : alpha);
        return recyclerView;
    }

    private OnBindDataListener<Menu> listener = new OnBindDataListener<Menu>() {
        @Override
        public void onBindData(RecyclerViewHolder holder, int position, Menu menu) {
            ViewBarMenuBinding binding = (ViewBarMenuBinding) holder.getBinding();
            binding.setModel(menu);
            binding.executePendingBindings();
        }
    };

}
