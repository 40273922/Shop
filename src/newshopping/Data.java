package newshopping;
import java.sql.*;
/**
 * &#064;program:
 * &#064;description:
 * &#064;author:XieMu
 * &#064;create:  -11-29 22:42
 *存放购物系统的初始化数据的数据类，该类只是存放了已有的商品信息和顾客信息
 *
 * @author GK_L2
 */
public class Data {
    /**
     * 默认构造方法，初始化变量，由于都是数组对象或类对象，所以都需要采用new
     *
     */
    public Data() {
        goodsName 	= new String[21474] ;
        goodsPrice 	= new double[21474] ;
        custNo 		= new int[21474]	 ;
        custBirth 	= new String[21474];
        custScore 	= new int[21474]   ;
        manager 	= new Manager()  ;
    }
    /**
     * 初始化该类的数据
     *
     */
    public void initial() {
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
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        } catch ( Exception e ) {
            System.out.println ( "连接失败:" + e.getMessage ( ) );
        }
    }
    /**
     *  商品的名称
     *
     */
    public String[] goodsName;
    /**
     * 商品的价格
     *
     */
    public double[] goodsPrice;
    /**
     * 顾客的会员号
     *
     */
    public int[] custNo;
    /**顾客的生日
     *
     */
    public String[] custBirth;
    /**顾客的积分
     *
     */
    public int[] custScore;
    /**管理员类，仅仅存储了管理员的用户名和密码
     *
     */
    public Manager manager	;
}