package com.merlin.view.bar;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.merlin.core.context.AppContext;
import com.merlin.core.network.NetWorkListener;
import com.merlin.core.network.NetWorkType;
import com.merlin.core.util.UiUtil;
import com.merlin.core.util.Util;
import com.merlin.core.util.ValiUtil;
import com.merlin.view.bar.databinding.ViewBarBinding;
import com.merlin.view.bar.databinding.ViewBarMenuBinding;
import com.merlin.view.bar.model.Bar;
import com.merlin.view.bar.model.Menu;
import com.merlin.view.recycler.AbstractRecyclerAdapter;
import com.merlin.view.recycler.MRecyclerView;
import com.merlin.view.recycler.RecyclerViewHolder;

/**
 * Created by ncm on 2017/4/12.
 */

public class MBarView extends RelativeLayout implements NetWorkListener {

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
    public void onNetWorkChanged(NetWorkType type) {
        bar.setTitle(type.name());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        binding = DataBindingUtil.bind(this);
        AppContext.inst().addNetWorkListener(this);
    }

    public void apply(Bar bar) {
        this.bar = bar;
        binding.setModel(bar);
    }

    public void showMoreTopRight() {
        showMore(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, getBottom(), Gravity.TOP | Gravity.RIGHT, true, 0);
    }

    public void showMoreTopMiddle() {
        showMore(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, getBottom(), Gravity.TOP | Gravity.CENTER, true, 0);
    }

    public void showMoreMiddle() {
        showMore(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0, Gravity.CENTER, true, 0);
    }

    public void hideMore() {
        if (moreDialog == null && moreDialog.isShowing()) {
            moreDialog.dismiss();
        }
    }

    public void showMore(int width, int height, int x, int y, int gravity, boolean cancelable, float alpha) {
        if (ValiUtil.isEmpty(bar.getMoreList())) {
            return;
        }
        if (moreDialog == null) {
            moreDialog = UiUtil.showDialog(getContext(), getMenuMoreView(alpha), width, height, x, y, 0, R.style.anim_alpha_scale, gravity, cancelable);
        } else {
            Window window = moreDialog.getWindow();
            if (gravity != 0) {
                window.setGravity(gravity);  //此处可以设置dialog显示的位置
            }
            WindowManager.LayoutParams params = window.getAttributes();
            params.x = x;
            params.y = y;
            moreDialog.getWindow().setAttributes(params);
            moreDialog.show();
        }
    }

    private View getMenuMoreView(float alpha) {
        MRecyclerView recyclerView = new MRecyclerView(getContext());
        recyclerView.set(MRecyclerView.MODE_LIST, LinearLayoutManager.VERTICAL, 0, Util.dp2px(1), 0, 0, 0xffffffff);
        recyclerView.setAdapter(new AbstractRecyclerAdapter<Menu>(bar.getMoreList()) {
            @Override
            public ViewDataBinding getItemBinding(ViewGroup parent, int viewType) {
                return DataBindingUtil.inflate(Util.inflater(), R.layout.view_bar_menu, parent, false);
            }

            @Override
            public void onBindViewHolder(RecyclerViewHolder holder, int position) {
                ViewBarMenuBinding binding = (ViewBarMenuBinding) holder.getBinding();
                binding.setModel(getData(position));
            }
        });
        recyclerView.setBackgroundColor(bar.bgColorBar);
        if (alpha <= 0) {
            alpha = 0.8f;
        }
        recyclerView.setAlpha(alpha);
        return recyclerView;
    }

}
