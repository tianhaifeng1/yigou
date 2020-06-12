package com.xzte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DateActivity extends AppCompatActivity {

    Button button1;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        button1 = findViewById(R.id.button2);

        getData();

//        options1Items.add("1");
//        options1Items.add("2");
//
//        //选项2
//        ArrayList<String> options2Items_01 = new ArrayList<>();
//        options2Items_01.add("12:00~12.30");
//        options2Items_01.add("12:30~13.00");
//        options2Items_01.add("13:00~13.30");
//        options2Items_01.add("13:30~14.00");
//        ArrayList<String> options2Items_02 = new ArrayList<>();
//        options2Items_02.add("08:30~09.00");
//        options2Items_02.add("09:00~09.30");
//        options2Items_02.add("09:30~10.00");
//        options2Items_02.add("10:00~10.30");
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(DateActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 , View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1)
                                + options2Items.get(options1).get(option2);
                        button1.setText(tx);
                        getData();
//                        tvOptions.setText(tx);
                    }
                }).setTitleText("请选择配送时间")
                        .build();
                pvOptions.setPicker(options1Items, options2Items);
                pvOptions.show();
            }
        });


    }

    private void getData() {
        Date curDate = new Date(System.currentTimeMillis());
        SimpleDateFormat format1 = new SimpleDateFormat("dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
//        Toast.makeText(DateActivity.this,format1.format(curDate)+","+format2.format(curDate),Toast.LENGTH_SHORT).show();
    }

    private String getTime() {//可根据需要自行截取数据显示
        Date curDate = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(curDate);
    }

}
