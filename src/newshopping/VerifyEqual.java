package newshopping;

import java.sql.*;
import java.util.Scanner;

/**
 * &#064;program:
 * &#064;description:
 * &#064;author:XieMu
 * &#064;create:  -11-29 22:42
 * 验证用户登录的账户和密码是否正确的类
 *
 * @author GK_L2
 */
public class VerifyEqual {
    /**
     * 空构造方法
     *
     */
    public VerifyEqual() {
    }
    /**
     * 执行验证的方法
     *
     */
    public boolean verify() throws SQLException {
            Connection con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/jdbcl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&severTimezone=Asia/Beijing" , "root" , "40273939zjpzjp" );
            Statement stmt = con.createStatement ( );
            // 由用户输入用户名
            System.out.print ( "请输入用户名：" );
            Scanner scanner = new Scanner ( System.in );
            String s2 = scanner.next ( );
            // 由用户输入密码
            System.out.print ( "请输入密码：" );
            scanner = new Scanner ( System.in );
            String s3 = scanner.next ( );
            ResultSet rs = stmt.executeQuery ( "select * from jdbcl.manager" );
            while ( rs.next ( ) ) {
                if ( ( rs.getNString ( "username" ).equals ( s2 ) && rs.getString ( "password" ).equals ( s3 ) ) ) {
                    // 判断用户输入的信息是否和已有的信息一致
                    return true;
                }
            }
        return false;
    }
}



