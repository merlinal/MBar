package com.merlin.view.bar;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ncm on 17/2/20.
 */

public class VmHelper {

//    @BindingAdapter("app:url")
//    public static void bindImage(ImageView imageView, String url) {
//        if(!MVerify.isBlank(url)){
//            ImageWorker.display(BaseApi.getFullUrl(url), imageView, true);
//        }
//    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        if (resId != 0) {
            view.setImageResource(resId);
        }
    }

    @BindingAdapter("android:text")
    public static void setTextId(TextView view, int resId) {
        view.setText(resId);
    }

//    @BindingAdapter("app:emptyView")
//    public static <T> void setEmptyView(AdapterView adapterView, int viewId) {
//        View rootView = adapterView.getRootView();
//        View emptyView = rootView.findViewById(viewId);
//        if (emptyView != null) {
//            adapterView.setEmptyView(emptyView);
//        }
//    }
//
//    @BindingAdapter("app:emptyView")
//    public static <T> void setEmptyView(MRecyclerView recyclerView, int viewId) {
//        View rootView = recyclerView.getRootView();
//        View emptyView = rootView.findViewById(viewId);
//        if (emptyView != null) {
//            recyclerView.setEmptyView(emptyView);
//        }
//    }

}
