package newshopping;


import java.sql.*;
import java.util.Scanner;

/**
 * &#064;program:
 * &#064;description:
 * &#064;author:XieMu
 * &#064;create:  -11-29 22:42
 *顾客信息管理类
 *
 * @author GK_L2
 */

public class CustManagement {
    /**
     * 空构造方法
     */
    public CustManagement ( ) {
    }
    
    /**
     * 返回上一级菜单，即二级菜单-客户信息管理菜单
     */
    public void returnLastMenu ( ) throws Exception {
        System.out.print ( "\n\n请按'n'返回上一级菜单:" );
        Scanner scanner = new Scanner ( System.in );
        boolean flag = true;
        do {
            if ( "n".equals ( scanner.next ( ) ) ) {
                Menu menu = new Menu ( );
                menu.setData ( goodsName , goodsPrice , custNo , custBirth , custScore );
                menu.showCustMMenu ( );
            } else {
                System.out.print ( "输入错误, 请重新'n'返回上一级菜单：" );
                flag = false;
            }
        } while ( ! flag );
    }
    /**
     * 添加客户信息
     */
    public void add ( ) throws SQLException {
        String driver = " com.mysql.jdbc.Driver.class";
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName ( driver );
        } catch ( java.lang.ClassNotFoundException e ) {
            System.out.println ( "无法加载驱动." );
        }
        try {
            con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" );
            stmt = con.createStatement ( );
            System.out.println ( "语句对象：" + stmt );
            System.out.println ( "连接成功." );
            System.out.println ( "购物管理系统 > 客户信息管理 > 添加客户信息\n\n" );
            Scanner scanner = new Scanner ( System.in );
            System.out.print ( "请输入会员号(<4位整数>)：" );
            int i = scanner.nextInt ( );
            System.out.print ( "请输入会员生日（月/日<用两位数表示>）：" );
            String s = scanner.next ( );
            System.out.print ( "请输入积分：" );
            int j = scanner.nextInt ( );
            stmt.executeUpdate ( "insert into jdbcl.jdbd values ('" + i + "','" + s + "','" + j + "')" );
            System.out.println ( "新会员添加成功！" );
            returnLastMenu ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        } catch ( Exception e ) {
            System.out.println ( "连接失败:" + e.getMessage ( ) );
        }
        if ( con != null ) {
            try {
                con.close ( );
            } catch ( SQLException e ) {
                e.printStackTrace ( );
            }finally {
                con.close ();
                assert stmt != null;
                stmt.close ();
            }
        }
    }
    /**
     * 修改客户信息
     */
    public void modify ( )  {
        try ( Connection con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" ) ; Statement stmt = con.createStatement ( ) ) {
            System.out.println ( "连接成功." );
            System.out.println ( "购物管理系统 > 客户信息管理 > 修改客户信息\n\n" );
            System.out.print ( "请输入会员号：" );
            Scanner scanner = new Scanner ( System.in );
            int i = scanner.nextInt ( );
            System.out.println ( "|-----------|------------|------------|");
            System.out.println ( "|   会员号        生日          积分     |"  );
            System.out.println ( "|-----------|------------|------------|");
            ResultSet rs = stmt.executeQuery ( "select * from jdbcl.jdbd where CustNo = '"+i+"'" );
            boolean f = false;
            while ( rs.next () ){
                f = rs.getInt ( "CustNo" ) == i;
            }
            if (f){
                System.out.println ( "* * * * * * * * * * * * * * * * * * * * *\n" );
                System.out.println ( "\t\t\t\t1.修 改 会 员 生 日.\n" );
                System.out.println ( "\t\t\t\t2.修 改 会 员 积 分.\n" );
                System.out.println ( "* * * * * * * * * * * * * * * * * * * * *\n" );
                System.out.print ( "请选择，输入数字：" );
                switch ( scanner.nextInt ( ) ) {
                    case 1: // "修改会员生日"
                        System.out.print ( "请输入修改后的生日：" );
                        String custBirth = scanner.next ( );
                        System.out.println ( "生日信息已更改！" );
                        stmt.executeUpdate ( "update jdbcl.jdbd set CustBirth = '" + custBirth + "' where CustNo = '" + i + "' " );
                    break;
                    case 2: // "修改会员积分"
                        System.out.print ( "请输入修改后的会员积分：" );
                        int custScore = scanner.nextInt ( );
                        stmt.executeUpdate ( "update jdbcl.jdbd set CustScore = '" + custScore + "' where CustNo = '" + i + "' " );
                        System.out.println ( "会员积分已更改！" );
                    break;
                    default :
                }
            } else {
                System.out.println ( "抱歉，没有你查询的会员。" );
            }
            returnLastMenu ( );
        } catch ( Exception s ){
            s.printStackTrace ();
        }
    }
    /**
     * 查询客户信息
     */
    public void search ( )  {
        System.out.println ( "购物管理系统 > 客户信息管理 > 查询客户信息\n" );
        Scanner scanner = new Scanner ( System.in );
        try ( Connection con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" ) ; Statement stmt = con.createStatement ( ) ) {
            System.out.println ( "连接成功." );
            for ( String s = "y" ; "y".equals ( s ) ; s = scanner.next ( ) ) {
                System.out.print ( "请输入会员号：" );
                int i = scanner.nextInt ( );
                ResultSet rs = stmt.executeQuery ( "select * from jdbcl.jdbd where CustNo = '"+i+"'" );
                System.out.println ( "|-------------------------------------|" );
                System.out.println ( "|   会员号        生日          积分    |" );
                System.out.println ( "|-----------|------------|------------|" );
                if ( !rs.next () ){
                    System.out.println ("----------- 抱歉，查无此人！------------" );
                }
                while ( rs.next ( ) ) {
                    if ( rs.getInt ( "CustScore" ) < 1000 ){
                        System.out.print ( "|\t" + rs.getInt ( "CustNo" ) + "\t" + "|" + " " + rs.getString ( "CustBirth" ) + " " + "|" + "\t " + rs.getInt ( "CustScore" ) + "\t\t  |\n------------|------------|------------|\n" );
                    }else if (  rs.getInt ( "CustScore" ) >= 1000 ){
                        System.out.print ( "|\t" + rs.getInt ( "CustNo" ) + "\t" + "|" + " " + rs.getString ( "CustBirth" ) + " " + "|" + "\t " + rs.getInt ( "CustScore" ) + "\t  |\n------------|------------|------------|\n" );
                    }
                    
                }
                System.out.print ( "\n要继续查询吗（y/n）:" );
            }
            returnLastMenu ( );
        }catch ( Exception e ) {
            e.printStackTrace ();
        }
    }
    /**
     * 显示所有客户信息
     */
    public void show ( ){
        System.out.println ( "购物管理系统 > 客户信息管理 > 显示客户信息\n\n" );
        try ( Connection con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" ) ; Statement stmt = con.createStatement ( ) ) {
            System.out.println ( "连接成功." );
            System.out.println ( "|-------------------------------------|" );
            System.out.println ( "|   会员号        生日          积分    |" );
            System.out.println ( "|-----------|------------|------------|" );
            ResultSet rs = stmt.executeQuery ( "select * from jdbcl.jdbd" );
            while ( rs.next ( ) ) {
                if ( rs.getInt ( "CustScore" ) < 1000 ) {
                    System.out.print ( "|\t" + rs.getInt ( "CustNo" ) + "\t" + "|" + " " + rs.getString ( "CustBirth" ) + " " + "|" + "\t " + rs.getInt ( "CustScore" ) + "\t\t  |\n------------|------------|------------|\n" );
                } else {
                    System.out.print ( "|\t" + rs.getInt ( "CustNo" ) + "\t" + "|" + " " + rs.getString ( "CustBirth" ) + " " + "|" + "\t " + rs.getInt ( "CustScore" ) + "\t  |\n------------|------------|------------|\n" );
                }
            }
            returnLastMenu ( );
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
    }
    /*====================定义该类所拥有的变量====================*/
    public String[] goodsName;    // 商品的名称
    public double[] goodsPrice;    // 商品的价格
    public int[] custNo;    // 顾客的会员号
    public String[] custBirth;    // 顾客的生日
    public int[] custScore;    // 顾客的积分

}
