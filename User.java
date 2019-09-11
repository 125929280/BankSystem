import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class User {
    private String name;
    private String password;
    public Card[] cardSet = new Card[100];

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void register(User user) {
        // 写文件
    }

    // public void login(String name, String password) {
    // // 读文件
    // }

    public void showInfo() {
        // 读文件
        for (int i = 0; cardSet[i] != null; i++) {
            System.out.println("卡号:" + cardSet[i].number + "\t余额" + cardSet[i].remainmoney);
        }
    }

}