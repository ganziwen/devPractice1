package com.example.autoframework.paramholder;

import com.example.autoframework.model.NewUser;
import com.example.autoframework.model.OldUser;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AutoParamsHolder
 * @Description
 * @date 2021/12/23 12:25
 */
public class AutoParamsHolder implements ParamHolder {
    public NewUser payer;
    public OldUser payer1;
    public NewUser payee;
    public String orderId;
    public Long amount;

    private AutoParamsHolder() {

    }


    private static class ClassHolder {
        private static final AutoParamsHolder HOLDER = new AutoParamsHolder();
    }

    public static AutoParamsHolder of() {
        return ClassHolder.HOLDER;
    }


    public static AutoParamsHolder getForTestCreateOrder() {
        AutoParamsHolder holder = AutoParamsHolder.of();
        holder.orderId = "1234";
        holder.amount = 666L;
        holder.payee = new NewUser();
        holder.payer = new NewUser();
        return holder;
    }
}
