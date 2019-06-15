package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
     private static DataSource ds;
      static {
          Properties pro =new Properties();
          try {
              pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
              ds= DruidDataSourceFactory.createDataSource(pro);
          } catch (IOException e) {
              e.printStackTrace();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      public  static Connection getConnection() throws SQLException {
          return ds.getConnection();
      }
      public static void close(ResultSet res,Statement stmt, Connection conn){
          if(stmt!=null){
              try {
                  stmt.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          if(conn!=null){
              try {
                  conn.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          if(res!=null){
              try {
                  res.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }

      }
   public static DataSource getDataSourse(){
          return ds;
   }
}
