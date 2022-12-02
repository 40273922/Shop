package newshopping;

import org.jetbrains.annotations.Contract;

import java.sql.*;
import java.util.Scanner;

/**
 * &#064;program:
 * &#064;description:
 * &#064;author:XieMu
 * &#064;create:  -11-29 22:42
 *礼物管理类
 *
 * @author GK_L2
 */
public class GiftManagement {
    /**
     * 空构造方法
     *
     */
    @Contract ( pure = true )
    public GiftManagement() {
    }
    
    /**
     * 设置礼物管理类的数据信息
     */
    public void setData( String[] as , double[] ad , int[] ai , String[] as1 , int[] ai1 ) {
        goodsName 	= as;
        goodsPrice 	= ad;
        custNo 		= ai;
        custBirth	= as1;
        custScore 	= ai1;
    }
    
    /**
     * 返回上一级菜单，即二级菜单-真情回馈菜单
     *
     */
    public void returnLastMenu() throws Exception {
        
        System.out.print("\n\n请按'n'返回上一级菜单:");
        Scanner scanner = new Scanner(System.in);
        
        boolean flag = true;
        do {
            if ( "n".equals( scanner.next() )) {
                // 返回上一级菜单，这里新建了一个菜单对象，只是在用户看来其实还是同样的处理流程，
                // 不过对于程序来说却又开始了一个新的二级菜单处理流程
                Menu menu = new Menu();
                menu.setData(goodsName, goodsPrice, custNo, custBirth, custScore);
                menu.showCustMMenu();
            } else {
                System.out.print("输入错误, 请重新'n'返回上一级菜单：");
                flag = false;
            }
        }
        while (!flag);
    }
    
    /**
     * 生日问候
     *
     */
    public void sendBirthCust()  {
        
        System.out.println("购物管理系统 > 生日问候\n\n");
        System.out.print("请输入今天的日期(月/日<用两位表示>)：");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.println(s);
        Connection con ;
        Statement stmt ;
        ResultSet rs ;
        try {
            con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" );
            stmt = con.createStatement ( );
            rs = stmt.executeQuery ( "select goodsname from goods" );
            int i = 0;
            while ( rs.next () ){
                goodsName[i] = rs.getString("goodsname");
                i++;
            }
            rs = stmt.executeQuery ( "select goodsprice from goods" );
            int j = 0;
            while ( rs.next () ){
                goodsPrice[j] = rs.getDouble ("goodsprice");
                j++;
            }
            rs = stmt.executeQuery ( "select CustNo from jdbd");
            int q = 0;
            while ( rs.next () ){
                custNo[q] = rs.getInt ("CustNo");
                q++;
            }
            rs = stmt.executeQuery ( "select CustBirth from jdbd");
            int w = 0;
            while ( rs.next () ){
                custBirth[w] = rs.getString ("CustBirth");
                w++;
            }
            rs = stmt.executeQuery ( "select CustScore from jdbd");
            int e = 0;
            while ( rs.next () ){
                custScore[e] = rs.getInt ("CustScore");
                e++;
                StringBuilder s1   = new StringBuilder ( );
                boolean flag = false;
                for (int i4 = 0; i4 < custBirth.length; i4++) {
                    if (custBirth[i4] != null && custBirth[i4].equals(s)) {
                        s1.append ( custNo[ i4 ] ).append ( "\n" );
                        flag = true;
                    }
                }
                if (flag) {
                    System.out.println("过生日的会员是：");
                    System.out.println(s1);
                    System.out.println("恭喜！获赠MP3一个！");
                } else {
                    System.out.println("今天没有过生日的会员！");
                }
    
                // 返回上一级菜单
                returnLastMenu();
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        } catch ( Exception e ) {
            System.out.println ( "连接失败:" + e.getMessage ( ) );
        }
       
    }
    
    /**
     * 幸运抽奖，注意这里是随机的抽奖，所以需要随机数，只需要自己制定一个抽奖规则就可以
     *
     */
    public void sendLuckyCust()  {
        System.out.println("购物管理系统 > 幸运抽奖\n\n");
        System.out.print("是否开始（y/n）：");
        Scanner scanner = new Scanner(System.in);
        Connection con ;
        Statement stmt ;
        ResultSet rs ;
        try {
            con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" );
            stmt = con.createStatement ( );
            rs = stmt.executeQuery ( "select goodsname from goods" );
            int i = 0;
            while ( rs.next () ){
                goodsName[i] = rs.getString("goodsname");
                i++;
            }
            rs = stmt.executeQuery ( "select goodsprice from goods" );
            int j = 0;
            while ( rs.next () ){
                goodsPrice[j] = rs.getDouble ("goodsprice");
                j++;
            }
            rs = stmt.executeQuery ( "select CustNo from jdbd");
            int q = 0;
            while ( rs.next () ){
                custNo[q] = rs.getInt ("CustNo");
                q++;
            }
            rs = stmt.executeQuery ( "select CustBirth from jdbd");
            int w = 0;
            while ( rs.next () ){
                custBirth[w] = rs.getString ("CustBirth");
                w++;
            }
            rs = stmt.executeQuery ( "select CustScore from jdbd");
            int e = 0;
            while ( rs.next () ){
                custScore[e] = rs.getInt ("CustScore");
                e++;
                if ( "y".equals( scanner.next() )) {
                    int     i2    = (int) (Math.random() * 10D);	// 产生一个随机数
                    StringBuilder s    = new StringBuilder ( );
                    boolean flag = false;
                    for (int k = 0; k < custNo.length && custNo[k] != 0; k++) {
                        // 拿随机数与顾客会员号的相应结果进行比较，从而判断是否有顾客中奖
                        int j2 = (custNo[k] / 100) % 10;
                        if (j2 == i2) {
                            s.append ( custNo[ k ] ).append ( "\t" );
                            flag = true;
                        }
                    }
        
                    // 固定的奖品，所以不需要礼物类
                    if (flag) {
                        System.out.println( "幸运客户获赠MP3：" + s );
                    } else {
                        System.out.println("无幸运客户。");
                    }
                }
    
                // 返回上一级菜单
                returnLastMenu();
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        } catch ( Exception e ) {
            System.out.println ( "连接失败:" + e.getMessage ( ) );
        }

    }
    
    /**
     * 幸运大放送，取积分最高的会员作为幸运者，送其奖品
     *
     */
    public void sendGoldenCust()  {
        Connection con ;
        Statement stmt ;
        ResultSet rs ;
        try {
            con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" );
            stmt = con.createStatement ( );
            rs = stmt.executeQuery ( "select goodsname from goods" );
            int i = 0;
            while ( rs.next () ){
                goodsName[i] = rs.getString("goodsname");
                i++;
            }
            rs = stmt.executeQuery ( "select goodsprice from goods" );
            int j = 0;
            while ( rs.next () ){
                goodsPrice[j] = rs.getDouble ("goodsprice");
                j++;
            }
            rs = stmt.executeQuery ( "select CustNo from jdbd");
            int q = 0;
            while ( rs.next () ){
                custNo[q] = rs.getInt ("CustNo");
                q++;
            }
            rs = stmt.executeQuery ( "select CustBirth from jdbd");
            int w = 0;
            while ( rs.next () ){
                custBirth[w] = rs.getString ("CustBirth");
                w++;
            }
            rs = stmt.executeQuery ( "select CustScore from jdbd");
            int e = 0;
            while ( rs.next () ){
                custScore[e] = rs.getInt ("CustScore");
                e++;
                System.out.println("购物管理系统 > 幸运大放送\n\n");
                int i3 = 0;
                int j3 = custScore[0];
                for (int k = 0; k < custScore.length && custScore[k] != 0; k++) {
                    // 找到积分最高的会员
                    if (custScore[k] > j3) {
                        j3 = custScore[k];
                        i3 = k;
                    }
                }
    
                System.out.println( "具有最高积分的会员是： " + custNo[ i3 ] + "\t" + custBirth[ i3 ] + "\t" + custScore[ i3 ] );
    
                // 创建礼物类，并对礼物信息进行设置，这里的礼物是固定的信息
                Gift gift = new Gift();
                gift.name = "苹果笔记本电脑";
                gift.price = 12000D;
                System.out.print("恭喜！获赠礼品： ");
                System.out.println(gift);
    
                // 返回上一级菜单
                returnLastMenu();
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        } catch ( Exception e ) {
            System.out.println ( "连接失败:" + e.getMessage ( ) );
        }
  
    }
    public String[] goodsName;	// 商品的名称
    public double[] goodsPrice;	// 商品的价格
    public int[] custNo;	// 顾客的会员号
    public String[] custBirth;	// 顾客的生日
    public int[] custScore;	// 顾客的积分
}
