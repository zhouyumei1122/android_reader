package com.myreader.ui.activity;

import android.content.Context;
import android.content.Intent;


import com.myreader.R;
import com.myreader.base.BaseActivity;
import com.myreader.component.AppComponent;
import com.myreader.view.ProgressWebView;

import butterknife.Bind;

public class FeedbackActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, FeedbackActivity.class));
    }

    @Bind(R.id.feedbackView)
    ProgressWebView feedbackView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("反馈建议");
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        feedbackView.loadUrl("https://github.com/JustWayward/BookReader/issues/new");
    }
}
