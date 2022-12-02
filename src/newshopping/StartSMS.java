package newshopping;

import org.jetbrains.annotations.Contract;

import java.sql.*;
import java.util.Scanner;

/**
 * &#064;program:
 * &#064;description:
 * &#064;author:XieMu
 * &#064;create:  -11-29 22:42
 *该类是这个系统的主方法类，用于启动购物系统
 *
 * @author GK_L2
 */
public class StartSMS {
    /**
     * 空构造方法
     *
     */
    @Contract ( pure = true )
    public StartSMS() {
    }
    
    /**
     * 系统主方法
     *
     */
    public static void main( String[] args ) throws SQLException {
        // 创建已有的数据类的对象，并初始化已有的商品信息和顾客信息
        Data data = new Data ( );
        data.initial ( );
        // 创建菜单类的对象
        Menu menu = new Menu ( );
        // 这里将初始化的已有数据信息送给了菜单对象
        menu.setData ( data.goodsName , data.goodsPrice , data.custNo , data.custBirth , data.custScore );
        // 显示一级菜单，即登录界面
        menu.showLoginMenu ( );
        // 该标志用来判断是否发生了系统操作错误，当操作不当的时候flag为假，从而退出系统，默认为无错误
        boolean flag = true;
        // 处理整个系统的流程
        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" );
            stmt = con.createStatement ( );
            do {
                // 发生操作错误，退出系统
                if ( ! flag ) {
                    break;
                }
                // 创建验证用户登录的账户和密码是否正确的类的对象，这里只创建对象，并没有执行验证方法
                VerifyEqual verifyequal = new VerifyEqual ( );
                // 输入一级菜单中的选择
                Scanner scanner = new Scanner ( System.in );
                int i = scanner.nextInt ( );
                // 根据用户对一级菜单的选择做出不同的响应，注意这里就是经典的switch-case的用法
                switch ( i ) {
                    case 1:
                        // 用户选择"登录系统"
                        // 定义计数器，表示用户最多只能尝试3次，3次输入错误直接退出系统
                        int j = 3;
                        // 处理登录系统的流程
                        ResultSet rs;
                        do {
                            rs = stmt.executeQuery ( "select * from jdbcl.manager" );
                            while ( rs.next ( ) ) {
                                boolean bo =verifyequal.verify ( );
                                if (bo ){
                                    menu.showMainMenu ( );
                                    j = 3;
                                    break;
                                }
                            }
                            if ( j != 1 ) {
                                // 用户输入有误，还没有达到3次，允许重新输入
                                System.out.println ( "\n用户名和密码不匹配，请重新输入：" );
                            } else {
                                // 3次尝试结束，设置退出标志,并退出do-while循环
                                System.out.println ( "\n您没有权限进入系统！谢谢！" );
                                flag = false;
                                break;
                            }
                            // 每输入一次将计数器减1，用于表示已经尝试了多少次
                            j--;
                        } while ( true );
                        break;
                    case 2:
                        boolean fl = verifyequal.verify ( );
                        if (fl ) {
                            System.out.print ( "请输入新的用户名：" );
                            String username = scanner.next ( );
                            Statement stmt2 = con.createStatement ();
                            ResultSet rs2 = stmt2.executeQuery ( "select * from jdbcl.manager" );
                            while ( rs2.next () ){
                                stmt.executeUpdate ( "update jdbcl.manager set username = '" + username + "' where username = '" + rs2.getNString ( "username" ) + "' " );
                            }
                            System.out.print ( "请输入新的密码：" );
                            int password = scanner.nextInt ( );
                            while ( rs2.next () ){
                                stmt.executeUpdate ( "update jdbcl.manager set password = '" + password + "' where username = '" + rs2.getNString ( "username" ) + "' " );
                            }
                            System.out.println ( "用户名和密码已更改！" );
                            System.out.println ( "\t\t\t\t 1. 登 录 系 统\n\n" );
                            System.out.println ( "\t\t\t\t 2. 更 改 管 理 员 信 息\n\n" );
                            System.out.println ( "\t\t\t\t 3. 退 出\n\n" );
                            System.out.println ( "请选择，输入数字：" );
                        } else {
                            System.out.println ( "抱歉，你没有权限修改！" );
                            flag = false;
                        }
                        break;
                    case 3:
                        System.out.println ( "谢谢您的使用！" );
                        System.exit ( 0 );
                        break;
                    default:
                        System.out.print ( "输入有误！请重新选择，输入数字: " );
                        break;
                }
            } while ( flag );
    
        } catch ( Exception e ) {
            e.printStackTrace ( );
        } finally {
            assert con != null;
            con.close ( );
            assert stmt != null;
            stmt.close ( );
        }
    }
}

