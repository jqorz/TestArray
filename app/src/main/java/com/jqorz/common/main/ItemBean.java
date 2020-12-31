package com.jqorz.common.main;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/7/31
 */
public class ItemBean {
    private String text;
    private Runnable runnable;

    public ItemBean(String text, Runnable runnable) {
        this.text = text;
        this.runnable = runnable;
    }

    public String getText() {
        return text;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
