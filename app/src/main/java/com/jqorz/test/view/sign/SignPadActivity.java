package com.jqorz.test.view.sign;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.view.sign.signpad.views.SignaturePad;

/**
 * 测试手写轨迹的View
 */
public class SignPadActivity extends AppCompatActivity {

    SignaturePad paintView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        paintView=findViewById(R.id.signature_pad);
        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });
        //        getSignatureBitmap() - A signature bitmap with a white background.
        //        getTransparentSignatureBitmap() - A signature bitmap with a transparent background.
        //        getSignatureSvg() - A signature Scalable Vector Graphics document.
        paintView.setMinWidth(2.5f);
        paintView.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {

            }
        });

    }
}
