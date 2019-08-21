package com.jqorz.test.hfutwlan;
import android.view.View.*;
import android.app.*;
import android.view.*;
import android.os.*;

import com.jqorz.test.R;

public class Surfing extends Activity implements OnClickListener
{
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除标题栏
		super.setContentView(R.layout.activity_htutwlan_surfing);
	}
	@Override
	public void onClick(View p1)
	{
		
	}
}
