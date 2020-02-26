package com.itcodai.course01.common;

import lombok.Data;

@Data
public class ExceptionJsonResult {

        /**
         * 异常码
         */
        protected String code;

        /**
         * 异常信息
         */
        protected String msg;

        public ExceptionJsonResult() {
            this.code = "200";
            this.msg = "操作成功";
        }

        public ExceptionJsonResult(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        // get set
}
