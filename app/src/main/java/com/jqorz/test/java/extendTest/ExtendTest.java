package com.jqorz.test.java.extendTest;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/8/5
 */
public class ExtendTest {

    public static void main(String[] args) {
        Man male = new Male();
        if (male instanceof Male) {

        }
    }

    private interface Life {
        void eat();
    }

    private static class Male extends Man {

    }

    private static class Man implements Life {

        @Override
        public void eat() {
            System.out.println("eat");
        }
    }
}
