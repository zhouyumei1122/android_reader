package com.myreader.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.myreader.R;
import com.myreader.common.OnRvItemClickListener;
import com.myreader.entity.BooksByTag;
import com.myreader.util.Constant;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @date 16/8/7.
 */
public class BooksByTagAdapter extends EasyRVAdapter<BooksByTag.TagBook> {

    private OnRvItemClickListener itemClickListener;

    public BooksByTagAdapter(Context context, List<BooksByTag.TagBook> list,
                             OnRvItemClickListener listener) {
        super(context, list, R.layout.item_tag_book_list);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final BooksByTag.TagBook item) {
        StringBuffer sbTags = new StringBuffer();
        for (String tag : item.tags) {
            if (!TextUtils.isEmpty(tag)) {
                sbTags.append(tag);
                sbTags.append(" | ");
            }
        }

        holder.setRoundImageUrl(R.id.ivBookCover, Constant.IMG_BASE_URL + item.cover, R.drawable.cover_default)
                .setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvShortIntro, item.shortIntro)
                .setText(R.id.tvTags, (item.tags.size() == 0 ? "" : sbTags.substring(0, sbTags
                        .lastIndexOf(" | "))));

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder.getItemView(), position, item);
            }
        });
    }
}
