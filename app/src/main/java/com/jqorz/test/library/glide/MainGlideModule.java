package com.jqorz.test.library.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020-02-18
 *     desc   : 管理GlideModule的主类，必须放到app中，lib中请使用LibraryGlideModule
 *     version: 1.0
 * </pre>
 */
@GlideModule
public class MainGlideModule extends AppGlideModule {
    static {
        Log.i("jqjq", "MainGlideModule");
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        Log.i("jqjq", "MainGlideModule applyOptions");
        super.applyOptions(context, builder);
    }
}
