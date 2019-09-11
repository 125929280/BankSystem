
public class normalCard extends Card implements SaveAndWithdraw {

    public normalCard(double remainmoney, String number, String type) {
        super(remainmoney, number, type);
    }

    public void withdraw(double money) {
        if (remainmoney >= money) {
            remainmoney -= money;
            System.out.println("取钱成功");
        } else
            System.out.println("余额不足");
    }

}