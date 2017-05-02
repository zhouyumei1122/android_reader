package com.myreader.ui.activity;

import android.app.Activity;
import android.content.Intent;


import com.myreader.R;
import com.myreader.base.BaseRVActivity;
import com.myreader.component.AppComponent;
import com.myreader.component.DaggerBookComponent;
import com.myreader.entity.BookSource;
import com.myreader.ui.contract.BookSourceContract;
import com.myreader.ui.easyadapter.BookSourceAdapter;
import com.myreader.ui.presenter.BookSourcePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @date 2016/9/8.
 */
public class BookSourceActivity extends BaseRVActivity<BookSource> implements BookSourceContract.View {

    public static final String INTENT_BOOK_ID = "bookId";

    public static void start(Activity activity, String bookId, int reqId) {
        activity.startActivityForResult(new Intent(activity, BookSourceActivity.class)
                .putExtra(INTENT_BOOK_ID, bookId), reqId);
    }

    @Inject
    BookSourcePresenter mPresenter;

    private String bookId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_recyclerview;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        bookId = getIntent().getStringExtra(INTENT_BOOK_ID);
        mCommonToolbar.setTitle("选择来源");
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    public void initDatas() {
        initAdapter(BookSourceAdapter.class, false, false);
    }

    @Override
    public void configViews() {
        mPresenter.attachView(this);
        mPresenter.getBookSource("summary", bookId);
    }

    @Override
    public void onItemClick(int position) {
        BookSource data = mAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra("source", data);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void showBookSource(List<BookSource> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
