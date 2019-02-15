package com.example.tang.dialogcommonlyused.dialog;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import com.example.tang.dialogcommonlyused.R;
import com.example.tang.dialogcommonlyused.widget.PasswordInputView;


/**
 * @author TangAnna
 * @description: 输入支付密码弹窗
 * @date :${DATA} 14:46
 */
@SuppressLint("ValidFragment")
public class DialogInoutPayPwd extends BaseFragmentDialog implements PasswordInputView.OnFinishListener {


    private PasswordInputView mPasswordInputView;

    public DialogInoutPayPwd(OnInputFinishListener onInputFinishListener) {
        mOnInputFinishListener = onInputFinishListener;
    }

    @Override
    public void initView(View view) {

        mPasswordInputView = view.findViewById(R.id.piv_dialog_inputPayPwd_input);
    }

    @Override
    public void setListener(View view) {
        super.setListener(view);
        mPasswordInputView.setOnFinishListener(this);
    }

    @Override
    public int bindView() {
        return R.layout.dialog_input_pay_pwd;
    }


    private OnInputFinishListener mOnInputFinishListener;

    @Override
    public void setOnPasswordFinished() {
        if (!TextUtils.isEmpty(mPasswordInputView.getOriginText()) && mPasswordInputView.getOriginText().length() == 6) {
            if (mOnInputFinishListener != null) {
                mOnInputFinishListener.onFinish(mPasswordInputView.getOriginText());
            }
            dismiss();
        }

    }

    public interface OnInputFinishListener {
        void onFinish(String result);
    }
}
