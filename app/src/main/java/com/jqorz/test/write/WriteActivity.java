package com.jqorz.test.write;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.write.write.WriteView;
import com.jqorz.test.write.write.WriteView1;
import com.jqorz.test.write.write.WriteView2;

public class WriteActivity extends AppCompatActivity {
    WriteView mWriteView;
    WriteView1 mWriteView1;
    WriteView2 mWriteView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        mWriteView = findViewById(R.id.mWriteView);
        mWriteView1 = findViewById(R.id.mWriteView1);
        mWriteView2 = findViewById(R.id.mWriteView2);

    }

    public void onClickClearBtn(View view) {
        mWriteView.clear();
        mWriteView1.clear();
        mWriteView2.clear();
    }
}
