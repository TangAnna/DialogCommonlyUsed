package com.example.tang.dialogcommonlyused.dialog;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.tang.dialogcommonlyused.R;
import com.example.tang.dialogcommonlyused.utils.CodeUtils;
import com.example.tang.dialogcommonlyused.utils.ToastUtils;
import com.example.tang.dialogcommonlyused.widget.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author TangAnna
 * @description: 选择时间
 * @date :${DATA} 15:03
 */
@SuppressLint("ValidFragment")
public class DialogChooseDate extends BaseFragmentDialog implements WheelView.OnSelectListener, View.OnClickListener {

    private WheelView mWvYear;
    private WheelView mWvMouth;
    private WheelView mWvDay;
    private TextView mTvConfirm;
    private int mCurrentYear; //当前年份
    private int mCurrentMonth;//当前月份
    private int mCurrentDay;//当前日期
    private int mDefaultYearIndex;//年份的默认选项是当前年份的下标
    private int mDefaultMonthIndex;
    private int mDefaultDayIndex;
    private static final String YEAR = "年";
    private static final String MONTH = "月";
    private static final String DAY = "日";
    private DialogChooseTime mDialogChooseTime;
    /**
     * true 没有选择的限制条件  false 只能选择当前时间以前是日期
     */
    private boolean canMoreCurrent;

    /**
     * @param dialogChooseTime
     * @param canMoreCurrent   选择日期是否可以大于当前日期  true 没有选择的限制条件  false 只能选择当前时间以前是日期
     */
    public DialogChooseDate(DialogChooseTime dialogChooseTime, boolean canMoreCurrent) {
        mDialogChooseTime = dialogChooseTime;
        this.canMoreCurrent = canMoreCurrent;
    }

    @Override
    public int bindView() {
        return R.layout.dialog_choose_time;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mWvYear = view.findViewById(R.id.wv_dialogChooseTime_year);
        mWvMouth = view.findViewById(R.id.wv_dialogChooseTime_mouth);
        mWvDay = view.findViewById(R.id.wv_dialogChooseTime_day);
        mTvConfirm = view.findViewById(R.id.tv_dialogChooseTime_confirm);
    }

    @Override
    public void initData() {
        super.initData();
        //获取当前年月日
        Calendar c = Calendar.getInstance();
        mCurrentYear = c.get(Calendar.YEAR);
        //Calendar获取到的月份从0开始计算  实际值要加一
        mCurrentMonth = c.get(Calendar.MONTH) + 1;
        mCurrentDay = c.get(Calendar.DATE);
    }

    @Override
    public void setData() {
        super.setData();
        mWvYear.setData(getYearData());
        mWvYear.setDefault(mDefaultYearIndex);

        mWvMouth.setData(getMonthData());
        mWvMouth.setDefault(mDefaultMonthIndex);

        mWvDay.setData(getDayData(mCurrentYear, mCurrentMonth));
        mWvDay.setDefault(mDefaultDayIndex);
    }

    @Override
    public void setListener(View view) {
        super.setListener(view);
        mWvYear.setOnSelectListener(this);
        mWvMouth.setOnSelectListener(this);
        mTvConfirm.setOnClickListener(this);
    }

    @Override
    protected int setAnimation() {
        return 0;
    }

    @Override
    protected int setGravity() {
        return Gravity.BOTTOM;
    }

    /**
     * 初始化年份数据
     *
     * @return
     */
    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = mCurrentYear; i > 1800; i--) {
            list.add(String.valueOf(i) + YEAR);
        }
        mDefaultYearIndex = list.indexOf(mCurrentYear + YEAR);
        return list;
    }

    /**
     * 初始化月份数据
     *
     * @return
     */
    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(String.valueOf(i) + MONTH);
        }
        mDefaultMonthIndex = list.indexOf(mCurrentMonth + MONTH);
        return list;
    }

    /**
     * 初始化日期数据
     *
     * @param year
     * @param month
     * @return
     */
    private ArrayList<String> getDayData(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < dayOfMonth + 1; i++) {
            list.add(String.valueOf(i) + DAY);
        }
        mDefaultDayIndex = list.indexOf(mCurrentDay + DAY);
        return list;
    }

    @Override
    public void endSelect(int id, String text) {
        if (!TextUtils.isEmpty(mWvYear.getSelectedText()) && !TextUtils.isEmpty(mWvMouth.getSelectedText())) {
            final String year = mWvYear.getSelectedText().replace(YEAR, "");
            final String month = mWvMouth.getSelectedText().replace(MONTH, "");
            mWvDay.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mWvDay.refreshData(getDayData(Integer.parseInt(year), Integer.parseInt(month)));
                        mWvDay.setDefault(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 滑动监听
     *
     * @param id
     * @param text
     */
    @Override
    public void selecting(int id, String text) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialogChooseTime_confirm://确认事件
                if (canMoreCurrent) {
                    if (mDialogChooseTime != null) {
                        mDialogChooseTime.onTimeResult(mWvYear.getSelectedText(), mWvMouth.getSelectedText(), mWvDay.getSelectedText());
                    }
                    dismiss();
                } else {
                    long chooseTime = CodeUtils.formatDate(mWvYear.getSelectedText() + mWvMouth.getSelectedText() + mWvDay.getSelectedText());
                    long time = new Date().getTime();
                    //判断选择的时间和当前时间的大小
                    if (chooseTime > (time / 1000)) {
                        ToastUtils.showToast(CodeUtils.getIdString(R.string.date_chooseError));
                    } else {
                        if (mDialogChooseTime != null) {
                            mDialogChooseTime.onTimeResult(mWvYear.getSelectedText(), mWvMouth.getSelectedText(), mWvDay.getSelectedText());
                        }
                        dismiss();
                    }
                }
                break;
        }
    }

    public interface DialogChooseTime {
        void onTimeResult(String year, String month, String day);
    }
}
