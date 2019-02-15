package com.example.tang.dialogcommonlyused.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tang.dialogcommonlyused.R;

/**
 * @author TangAnna
 * @description: toolbar
 * @date :${DATA} 15:01
 */
public class MyToolbar extends RelativeLayout {

    private TextView mTvTitle;
    private ImageView mIvLeft;

    public MyToolbar(Context context) {
        super(context);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        inflate(context, R.layout.layout_toolbar, this);
        mTvTitle = findViewById(R.id.tv_toolbar_title);
        mIvLeft = findViewById(R.id.iv_toolbar_left);
        mIvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }

    /**
     * 设置title
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    /**
     * 隐藏左侧的关闭按钮
     */
    public void setHintLeft() {
        mIvLeft.setVisibility(GONE);
    }
}
