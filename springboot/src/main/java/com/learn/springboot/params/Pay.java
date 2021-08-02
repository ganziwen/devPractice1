package com.learn.springboot.params;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-19:48
 */
    @RestController
    @RequestMapping("/pay")
    public class Pay {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pay.class);


        /**
         * 自己实现
         *
         * @param payId
         * @return
         */
        @RequestMapping("/payWithParams2")
        public String pay1(String payId) {
            LOGGER.info("pay1 start,payId:{}", payId);
            try {
                if (Strings.isNullOrEmpty(payId)) {
                    throw new IllegalArgumentException("payId shpuld not be null or empty");
                }
                return String.format("%s-pay.success", payId);

            } catch (Exception e) {
                return "pay-error" + e.getMessage();
            }
        }

        /**
         * RequestParam
         * - required： true 为必填,false 为非必填
         * - defaultValue: 默认值
         *
         * @param payId
         * @return
         */
        @RequestMapping("/payWithRequestParam")
        public String pay2(@RequestParam(value = "payId", required = true, defaultValue = "666") String payId) {
            LOGGER.info("pay2 start,payId:{}", payId);
            return String.format("%s-pay.success", payId);
        }

        /**
         * PathVariable 指代的是 url 里面的参数,假设 url 里面的参数跟方法的参数一致,PathVariable 括号内的内容可以为空
         *
         * @param payId
         * @return
         */
        @RequestMapping("/payWithPathVariable/{payId}")
        public String pay3(@PathVariable("payId") String payId) {
            LOGGER.info("pay3 start,payId:{}", payId);
            return String.format("%s-pay.success", payId);
        }
    }
