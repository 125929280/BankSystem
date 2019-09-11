import java.util.*;

public abstract class Card implements SaveAndWithdraw {

    public double remainmoney;// 余额
    public String number;// 卡号
    public String type;

    public Card() {

    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setRemainmoney(double money) {
        this.remainmoney = money;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public Card(double remainmoney, String number, String type) {
        this.remainmoney = remainmoney;
        this.number = number;
        this.type = type;
    }

    public double getRemainmoney() {
        return remainmoney;
    }

    public void save(double money) {
        this.remainmoney += money;
        System.out.println("存钱成功!");
    }

    public abstract void withdraw(double money);

}
