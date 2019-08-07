package com.harmony.cmb.core;

/**
 * @author wuxii
 */
public interface Status {

    int code();

    String text();

    default String qualify() {
        return code() + "." + text();
    }

    enum MyWordStatus implements Status {

        UNREAD(10, "learningWords"),
        READING(20, "reading"),
        FINISHED(30, "finish");

        private int code;
        private String status;

        MyWordStatus(int code, String status) {
            this.code = code;
            this.status = status;
        }

        @Override
        public int code() {
            return code;
        }

        @Override
        public String text() {
            return status;
        }

    }

}
