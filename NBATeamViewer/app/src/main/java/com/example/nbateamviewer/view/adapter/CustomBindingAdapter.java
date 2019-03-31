package com.example.nbateamviewer.view.adapter;

import android.view.View;
import android.databinding.BindingAdapter;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
