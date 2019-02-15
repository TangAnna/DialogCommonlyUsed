package com.example.tang.dialogcommonlyused;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tang.dialogcommonlyused.dialog.DialogInoutPayPwd;
import com.example.tang.dialogcommonlyused.utils.ToastUtils;
import com.example.tang.dialogcommonlyused.widget.MyToolbar;

/**
 * @author TangAnna
 * @description: 支付密码弹窗
 * @date :${DATA} 14:05
 */
public class PayPwdActivity extends AppCompatActivity implements View.OnClickListener, DialogInoutPayPwd.OnInputFinishListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pwd);
        findViewById(R.id.btn_pay_confirm).setOnClickListener(this);
        MyToolbar toolbar = findViewById(R.id.toolbar_pay);
        toolbar.setTitle("支付密码的弹窗");
    }

    @Override
    public void onClick(View v) {
        DialogInoutPayPwd dialogInoutPayPwd = new DialogInoutPayPwd(this);
        dialogInoutPayPwd.showDilaog(getSupportFragmentManager(), "PayPwdActivity");

    }

    @Override
    public void onFinish(String result) {
        ToastUtils.showToast(result);
    }
}
