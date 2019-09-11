
public class creditCard extends Card implements SaveAndWithdraw {

    public creditCard(double remainmoney, String number, String type) {
        super(remainmoney, number, type);
    }

    public void withdraw(double money) {
        if (remainmoney + 2000 >= money) {
            remainmoney -= money;
            System.out.println("取钱成功");
        } else {
            System.out.println("超额透支");
        }

    }

}