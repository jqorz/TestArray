package com.jqorz.test.designmode;

/**
 * @author jqorz
 * @since 2021/8/29
 */
class InnerHolderSingle {
    private InnerHolderSingle() {
    }

    public static InnerHolderSingle getInstance() {
        return Holder.mInstance;
    }

    private static class Holder {
        private static final InnerHolderSingle mInstance = new InnerHolderSingle();
    }
}
