package com.jqorz.common.hfutwlan;
import android.view.View.*;
import android.app.*;
import android.view.*;
import android.os.*;

import com.jqorz.common.R;

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
