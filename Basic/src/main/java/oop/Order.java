package oop;

/**
 * getter & setter
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/2-18:35
 */
public class Order {
    Long amount;
    String payeeUserId;
    String payerUserId;

    public Order() {
    }

    public Order(Long amount, String payeeUserId) {
        this.amount = amount;
        this.payeeUserId = payeeUserId;
    }

    /**
     * 构造方法的重载
     *
     * @param amount
     * @param payeeUserId
     * @param payerUserId
     */
    public Order(Long amount, String payeeUserId, String payerUserId) {
        // 常用方式
//        this.amount = amount;
//        this.payeeUserId = payeeUserId;
//        this.payerUserId = payerUserId;

        // 调用上面的两参数构造器
        this(amount, payeeUserId);
        // 再加上缺失参数的构造器
        this.payerUserId = payerUserId;

    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
//        this 标志对象本身
        this.amount = amount;
    }

//    public void setAmount(Long amount) {
//        /**
//         * 按照就近原则,这里 amount 就等于传入的 amount 值,对象内的属性 amount 值未发生改变
//         */
//        amount = amount;
//    }
//
//    public void setAmount(Long amt) {
//        /**
//         * 按照就近原则,这里 amount 就是属性 amount ,所以传入的 amt 值会改变属性内的 amount 值
//         */
//        amount = amt;
//    }

    public String getPayeeUserId() {
        return payeeUserId;
    }

    public void setPayeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public String getPayerUserId() {
        return payerUserId;
    }

    public void setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
    }
}
