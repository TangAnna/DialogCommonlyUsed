package com.example.tang.dialogcommonlyused.dialog;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.tang.dialogcommonlyused.R;


/**
 * @author TangAnna
 * @description: 统一的提示弹窗
 * @date :${DATA} 11:12
 */
@SuppressLint("ValidFragment")
public class DialogComment extends BaseFragmentDialog implements View.OnClickListener {

    /**
     * hint文字
     */
    private String mHintText;
    private DialogCommentInterfaca mDialogCommentInterfaca;

    private TextView mTvHint;
    private TextView mTvConfirm, mTvCancel;

    @Override
    public int bindView() {
        return R.layout.dialog_comment;
    }

    public DialogComment(String hintText, DialogCommentInterfaca dialogCommentInterfaca) {
        mHintText = hintText;
        mDialogCommentInterfaca = dialogCommentInterfaca;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mTvHint = view.findViewById(R.id.tv_dialog_comment_hint);
        mTvConfirm = view.findViewById(R.id.tv_dialog_comment_confirm);
        mTvCancel = view.findViewById(R.id.tv_dialog_comment_cancel);
    }


    @Override
    public void initData() {
        super.initData();
        if (!TextUtils.isEmpty(mHintText)) {
            mTvHint.setText(mHintText);
        }
    }

    @Override
    public void setListener(View view) {
        super.setListener(view);
        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_comment_confirm://确认
                if (mDialogCommentInterfaca != null) {
                    mDialogCommentInterfaca.onConfirmListener();
                }

                break;
            case R.id.tv_dialog_comment_cancel://取消
                if (mCancelInterface != null) {
                    mCancelInterface.onCancelListener();
                }
                break;
        }
        dismiss();
    }

    public interface DialogCommentInterfaca {
        void onConfirmListener();
    }

    private DialogCancelInterface mCancelInterface;

    /**
     * 设置取消的点击事件
     *
     * @param cancelInterface
     */
    public void setCancelInterface(DialogCancelInterface cancelInterface) {
        mCancelInterface = cancelInterface;
    }

    public interface DialogCancelInterface {
        void onCancelListener();
    }
}
