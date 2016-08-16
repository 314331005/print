package print;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

import java.io.IOException; 
import java.sql.*; 
import java.util.List; 
import java.util.Properties; 

/** 
* JDBC工具类  
*/ 
public class DBToolkit { 
        private static final Log log = LogFactory.getLog(DBToolkit.class); 
        private static String url = null; 
        private static String username = null; 
        private static String password = null; 
        private static String driver = null; 
        private static Properties props = new Properties(); 

        static { 
                try { 
                        props.load(DBToolkit.class.getResourceAsStream("/jdbc.properties")); 
                } catch (IOException e) { 
                		e.printStackTrace();
                        log.error("#ERROR# :系统加载sysconfig.properties配置文件异常，请检查！", e); 
                } 
                url = (props.getProperty("jdbc.url")); 
                username = (props.getProperty("jdbc.username")); 
                password = (props.getProperty("jdbc.password")); 
                driver = (props.getProperty("jdbc.driver")); 
                //注册驱动类 
                try { 
                        Class.forName(driver); 
                } catch (ClassNotFoundException e) { 
                        log.error("#ERROR# :加载数据库驱动异常，请检查！", e); 
                        e.printStackTrace();
                } 
        } 

        /** 
         * 创建一个数据库连接 
         * 
         * @return 一个数据库连接 
         */ 
        public static Connection getConnection() { 
                Connection conn = null; 
                //创建数据库连接 
                try { 
                        conn = DriverManager.getConnection(url, username, password); 
                } catch (SQLException e) { 
                        log.error("#ERROR# :创建数据库连接发生异常，请检查！", e); 
                        e.printStackTrace();
                } 
                return conn; 
        } 

        /** 
         * 在一个数据库连接上执行一个静态SQL语句查询 
         * 
         * @param conn            数据库连接 
         * @param staticSql 静态SQL语句字符串 
         * @return 返回查询结果集ResultSet对象 
         */ 
        public static ResultSet executeQuery(Connection conn, String staticSql) { 
                ResultSet rs = null; 
                try { 
                        //创建执行SQL的对象 
                        Statement stmt = conn.createStatement(); 
                        //执行SQL，并获取返回结果 
                        rs = stmt.executeQuery(staticSql); 
                } catch (SQLException e) { 
                        log.error("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql, e); 
                        e.printStackTrace();
                } 
                return rs; 
        } 

        /** 
         * 在一个数据库连接上执行一个静态SQL语句 
         * 
         * @param conn            数据库连接 
         * @param staticSql 静态SQL语句字符串 
         */ 
        public static void executeSQL(Connection conn, String staticSql) { 
                try { 
                        //创建执行SQL的对象 
                        Statement stmt = conn.createStatement(); 
                        //执行SQL，并获取返回结果 
                        stmt.execute(staticSql); 
                } catch (SQLException e) { 
                        log.error("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql, e); 
                        e.printStackTrace();
                } 
        } 

        /** 
         * 在一个数据库连接上执行一批静态SQL语句 
         * 
         * @param conn        数据库连接 
         * @param sqlList 静态SQL语句字符串集合 
         */ 
        public static void executeBatchSQL(Connection conn, List<String> sqlList) { 
                try { 
                        //创建执行SQL的对象 
                        Statement stmt = conn.createStatement(); 
                        for (String sql : sqlList) { 
                                stmt.addBatch(sql); 
                        } 
                        //执行SQL，并获取返回结果 
                        stmt.executeBatch(); 
                } catch (SQLException e) { 
                        log.error("#ERROR# :执行批量SQL语句出错，请检查！", e); 
                        e.printStackTrace();
                } 
        } 

        public static void closeConnection(Connection conn) { 
                if (conn == null) return; 
                try { 
                        if (!conn.isClosed()) { 
                                //关闭数据库连接 
                                conn.close(); 
                        } 
                } catch (SQLException e) { 
                        log.error("#ERROR# :关闭数据库连接发生异常，请检查！", e); 
                        e.printStackTrace();
                } 
        }

		public static Properties getProps() {
			return props;
		}
        
}