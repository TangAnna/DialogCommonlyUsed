package com.example.tang.dialogcommonlyused;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tang.dialogcommonlyused.dialog.DialogChooseDate;
import com.example.tang.dialogcommonlyused.widget.MyToolbar;

/**
 * 选择日期的弹窗
 */
public class DateActivity extends AppCompatActivity implements View.OnClickListener, DialogChooseDate.DialogChooseTime {

    private TextView mTvDate;
    private TextView mTvBirthday;
    private TextView mTvLunarCalendar;
    private boolean tag;//用于区分结果  true:操作时间  false 操作生日

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        findViewById(R.id.layout_date).setOnClickListener(this);
        findViewById(R.id.layout_birthday).setOnClickListener(this);
        findViewById(R.id.layout_lunarCalendar).setOnClickListener(this);

        mTvDate = findViewById(R.id.tv_date);
        mTvBirthday = findViewById(R.id.tv_birthday);
        mTvLunarCalendar = findViewById(R.id.tv_lunarCalendar);

        MyToolbar toolbar = findViewById(R.id.toolbar_date);
        toolbar.setTitle("选择日期");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_date://没有限制条件的日期
                tag = true;
                DialogChooseDate dialogChooseDate = new DialogChooseDate(this, true);
                dialogChooseDate.showDilaog(getSupportFragmentManager(), "DateActivity");
                break;
            case R.id.layout_birthday://生日只能是当前时间以前的时间
                tag = false;
                DialogChooseDate dialogChooseDate2 = new DialogChooseDate(this, false);
                dialogChooseDate2.showDilaog(getSupportFragmentManager(), "DateActivity");
                break;
            case R.id.layout_lunarCalendar://选择阴历

                break;
        }
    }

    @Override
    public void onTimeResult(String year, String month, String day) {
        if (tag) {
            mTvDate.setText(year + month + day);
        } else {
            mTvBirthday.setText(year + month + day);
        }
    }
}
