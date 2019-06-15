package page;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test11 {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
        Statement statement = connection.createStatement();
        //String sql="update client set cname='刘刚刚' where cno='203' ";
        String sql = "select * from student where name='张三'and password='123'";
        ResultSet res = statement.executeQuery(sql);
        //int s = resultSet.getMetaData().getColumnCount();
        //List<String> list=new ArrayList<>();
        //   System.out.println(res);
        while (res.next()) {
//        // Map<String,Object> map=new HashMap<>();
////              System.out.println(resultSet.getString("cno"));
//        String cno = resultSet.getString("name");
//        String cname = resultSet.getString("password");
//            System.out.println(cname);
//        String csex = resultSet.getString("csex");
//        String ctel = resultSet.getString("ctel");
//        String csite = resultSet.getString("csite");
//        User user = new User(cno, cname, csex, ctel, csite);
////        list.add(user);
//        list.add(cname);
            System.out.println(res);
        }

    }

    }

