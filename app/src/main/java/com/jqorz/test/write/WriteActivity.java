package com.jqorz.test.write;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jqorz.test.R;
import com.jqorz.test.write.write.WriteView;
import com.jqorz.test.write.write.WriteView1;
import com.jqorz.test.write.write.WriteView2;
import com.jqorz.test.write.write.WriteView3;

public class WriteActivity extends AppCompatActivity {
    WriteView mWriteView;
    WriteView1 mWriteView1;
    WriteView2 mWriteView2;
    WriteView3 mWriteView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        mWriteView = findViewById(R.id.mWriteView);
        mWriteView1 = findViewById(R.id.mWriteView1);
        mWriteView2 = findViewById(R.id.mWriteView2);
        mWriteView3 = findViewById(R.id.mWriteView3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                onClickClearBtn();
                break;
        }
        return false;
    }

    public void onClickClearBtn() {
        mWriteView.clear();
        mWriteView1.clear();
        mWriteView2.clear();
        mWriteView3.clear();
    }
}
