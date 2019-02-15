package com.example.tang.dialogcommonlyused;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private ArrayAdapter mAdapter;
    private String mTextList[] = new String[]{"支付密码弹窗", "选择日期弹窗", "选择城市弹窗"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.lv_main);
        mListView.setOnItemClickListener(this);

        mListView.setAdapter(mAdapter = new ArrayAdapter<String>(this, R.layout.item_main, mTextList));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://支付密码弹窗
                startActivity(new Intent(MainActivity.this, PayPwdActivity.class));
                break;
            case 1://选择日期弹窗
                startActivity(new Intent(MainActivity.this, DateActivity.class));
                break;

        }
    }
}
