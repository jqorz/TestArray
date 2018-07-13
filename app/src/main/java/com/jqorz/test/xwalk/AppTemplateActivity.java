// Copyright (c) 2013 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.jqorz.test.xwalk;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xwalk.app.XWalkRuntimeActivityBase;

import java.util.Objects;

public class AppTemplateActivity extends XWalkRuntimeActivityBase {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void didTryLoadRuntimeView(View runtimeView) {
        if (runtimeView != null) {
            getRuntimeView().loadAppFromUrl("http://192.168.31.160:9022/dynamic/bk.interactive.04f587be-120d-441e-b7b5-27964960dfc0/index.html");
        } else {
            TextView msgText = new TextView(this);
            msgText.setText("Crosswalk failed to initialize.");
            msgText.setTextSize(36);
            msgText.setTextColor(Color.BLACK);
            setContentView(msgText);
        }
    }
}
