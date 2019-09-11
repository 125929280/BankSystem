import java.util.*;
import java.io.*;

public class Mgr {
    static Scanner input = new Scanner(System.in);
    static String name, password, number;
    static double money;
    static public User[] userSet = new User[100];
    static File file = new File("test.txt");
    static int times = 3;

    public static void startMenu() {
        // 读文件
        toArray();
        System.out.println("银行系统");
        System.out.println("1:用户登录\t2:用户注册\t3:退出");
        int choice = input.nextInt();
        while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("输入错误,请重新输入:");
            choice = input.nextInt();
        }
        switch (choice) {
        case 1:
            login();
            break;
        case 2:
            register();
            break;
        case 3:
            System.out.println("感谢使用");
            System.exit(0);
        default:
            break;
        }
    }

    public static void login() {
        int i = 0;
        try {
            System.out.print("请输入用户名:");
            name = input.next();
            for (; i < userSet.length; i++) {
                if (userSet[i].getName().equals(name)) {
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("查无此人");
            returnMenu();
        }

        System.out.print("请输入密码:");
        password = input.next();
        while (times != 1) {

            if (userSet[i].getPassword().equals(password)) {
                System.out.println("请选择:1.存钱\t2.取钱");
                int choice = input.nextInt();
                switch (choice) {
                case 1:
                    save(userSet[i]);
                    break;
                case 2:
                    withdraw(userSet[i]);
                    break;
                default:
                    break;
                }
                // userSet[i].showInfo();
            } else {
                System.out.println("密码错误");
                System.out.println("还有" + (--times) + "次机会,请再次输入密码:");
                password = input.next();
                // returnMenu();
            }
        }
        System.out.println("密码错误次数达到3次,退出系统!");
        // returnMenu();
        System.exit(0);
    }

    public static void register() {
        addCard(addUser());
        toTxt();
        returnMenu();
    }

    public static void save(User now) {
        System.out.println("请输入卡号:");
        number = input.next();
        boolean flag = true;
        int j = 0;
        while (flag) {
            for (j = 0; now.cardSet[j] != null; j++) {
                if (now.cardSet[j].number.equals(number)) {
                    flag = false;
                    break;
                }
            }
            if (now.cardSet[j] == null) {
                System.out.println("查无此卡,请重新输入:");
                number = input.next();
                // flag = true;
            }
        }
        System.out.println("请输入金额:");
        flag = true;
        while (flag) {
            try {
                money = input.nextDouble();
                now.cardSet[j].save(money);
                showInfo(now.cardSet[j]);
                toTxt();
                flag = false;
                returnMenu();

            } catch (InputMismatchException e) {
                System.out.println("输入错误,请重新输入:");
                input.next();
                // money = input.nextDouble();
            }
        }

        // System.out.println("存钱成功");

    }

    public static void withdraw(User now) {
        System.out.println("请输入卡号:");
        number = input.next();
        boolean flag = true;
        int j = 0;
        while (flag) {
            for (j = 0; now.cardSet[j] != null; j++) {
                if (now.cardSet[j].number.equals(number)) {
                    flag = false;
                    break;
                }
            }
            if (now.cardSet[j] == null) {
                System.out.println("查无此卡,请重新输入:");
                number = input.next();
                // flag = true;
            }
        }

        System.out.println("请输入金额:");
        flag = true;
        while (flag) {
            try {
                money = input.nextDouble();
                now.cardSet[j].withdraw(money);
                showInfo(now.cardSet[j]);
                toTxt();

                flag = false;
                returnMenu();
            } catch (InputMismatchException e) {
                System.out.println("输入错误,请重新输入:");
                input.next();
                // money = input.nextDouble();
            }
        }
    }

    public static void returnMenu() {
        System.out.println("输入0返回主菜单:");
        int choice = input.nextInt();
        while (choice != 0) {
            System.out.println("输入错误");
            choice = input.nextInt();
        }
        startMenu();
    }

    public static String deleteString(String origin, int count) {// 去除字符串origin中的前count个字符
        if (origin == null || origin.length() < count) {
            return null;
        }
        char[] arr = origin.toCharArray();
        char[] ret = new char[arr.length - count];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i + count];
        }
        return String.copyValueOf(ret);
    }

    public static User addUser() {
        System.out.println("输入用户名:(长度为3~10字符)");
        String name = input.next();
        int i = 0;
        boolean flag = false;
        while (true) {
            for (; userSet[i] != null && userSet[i].getName() != null; i++) {
                // System.out.println("dick" + i);
                if (userSet[i].getName().equals(name)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("该用户已存在,请重新输入新用户名");
                name = input.next();
                flag = false;
            } else
                break;
        }
        while (name.length() < 3 || name.length() > 10) {
            System.out.println("用户名不符合规范,请重新输入:");
            name = input.next();
        }
        System.out.println("请输入密码:");
        String password = input.next();

        System.out.println("请再次输入密码:");
        String pwd = input.next();
        while (password.equals(pwd) == false) {
            System.out.println("两次密码不一致,请重新输入");
            pwd = input.next();
        }
        int index = 0;
        for (; index < userSet.length; index++) {
            if (userSet[index] == null || userSet[index].getName() == null) {
                break;
            }
        }
        userSet[index] = new User(name, password);
        return userSet[index];
    }

    public static void addCard(User user) {
        System.out.println("请输入卡号:(0表示停止输入)");
        String number = input.next();
        while (number.equals("0") == false) {
            boolean flag = false;
            while (true) {
                for (int i = 0; userSet[i] != null; i++) {
                    for (int j = 0; userSet[i].cardSet[j] != null; j++) {
                        if (userSet[i].cardSet[j].getNumber().equals(number)) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag == true) {
                    System.out.println("该卡号已存在,请重新输入:");
                    number = input.next();
                    flag = false;
                } else
                    break;
            }
            int index = 0;
            for (; user.cardSet[index] != null; index++) {
            }
            System.out.println("请输入卡的类型:1.普通卡\t2.信用卡");
            int choice = input.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("输入错误,请重新输入:");
                choice = input.nextInt();
            }
            if (choice == 1) {
                System.out.println("请输入卡的余额(>=0):");
                double money = input.nextDouble();
                while (money < 0) {
                    System.out.println("输入余额不符合规范,请重新输入:");
                    money = input.nextDouble();
                }
                user.cardSet[index] = new normalCard(money, number, "普通卡");
            } else if (choice == 2) {
                System.out.println("请输入卡的余额(>= -2000):");
                double money = input.nextDouble();
                while (money < -2000) {
                    System.out.println("输入余额不符合规范,请重新输入:");
                    money = input.nextDouble();
                }
                user.cardSet[index] = new normalCard(money, number, "信用卡");
            }
            toTxt();
            System.out.println("注册成功");
            System.out.println("请输入卡号:(0表示停止输入)");
            number = input.next();
        }
    }

    public static void toTxt() {
        try {
            // System.out.println("fuck");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bwriter = new BufferedWriter(writer);
            for (int i = 0; userSet[i] != null && userSet[i].getName() != null; i++) {
                bwriter.write("name:" + userSet[i].getName() + "\n");
                bwriter.write("code:" + userSet[i].getPassword() + "\n");
                bwriter.flush();
                for (int j = 0; userSet[i].cardSet[j] != null; j++) {
                    bwriter.write("card:" + userSet[i].cardSet[j].getNumber() + "\n");
                    bwriter.write("type:" + userSet[i].cardSet[j].getType() + "\n");
                    bwriter.write("money:" + userSet[i].cardSet[j].getRemainmoney() + "\n");
                    bwriter.flush();
                }
            }

            bwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toArray() {
        try {// 开始时把所有东西导入数组
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            String record = new String();
            record = breader.readLine();// 读第一行
            String name, password, number, type;
            double money;
            for (int i = 0, j = 0; record != null; ++i) {
                record = deleteString(record, 5);
                name = record;
                record = breader.readLine();// 读第二行
                record = deleteString(record, 5);
                password = record;
                userSet[i] = new User(name, password);
                record = breader.readLine();// 读第三行
                while (record != null && record.indexOf("card:") == 0) {
                    record = deleteString(record, 5);
                    number = record;
                    record = breader.readLine();// 读第四行
                    record = deleteString(record, 5);
                    type = record;
                    record = breader.readLine();// 读第五行
                    record = deleteString(record, 6);
                    money = Double.parseDouble(record);
                    if (type.equals("普通卡"))
                        userSet[i].cardSet[j++] = new normalCard(money, number, type);
                    else
                        userSet[i].cardSet[j++] = new creditCard(money, number, type);
                    record = breader.readLine();// 读下一行
                    if (record == null)
                        break;
                }
                j = 0;
            }
        } catch (Exception e) {
        }
    }

    public static void showInfo() {
        for (int i = 0; userSet[i] != null && userSet[i].getName() != null; ++i)// 打印
        {
            System.out.println("name:" + userSet[i].getName());
            System.out.println("codes:" + userSet[i].getPassword());
            for (int j = 0; userSet[i].cardSet[j] != null; ++j) {
                System.out.println("card:" + userSet[i].cardSet[j].getNumber());
                System.out.println("type:" + userSet[i].cardSet[j].getType());
                System.out.println("money:" + userSet[i].cardSet[j].getRemainmoney());
            }
        }
    }

    public static void showInfo(Card card) {
        System.out.println("card:" + card.getNumber());
        System.out.println("type:" + card.getType());
        System.out.println("money:" + card.getRemainmoney());
    }
}