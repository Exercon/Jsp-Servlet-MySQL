package mypackage;

import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.DataSource;

@javax.servlet.annotation.WebServlet(name = "myServlet")
public class myServlet extends javax.servlet.http.HttpServlet {


    // JDBC driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:8888/web_student_tracker";

    //  Database credentials
    static final String USER = "webstudent";
    static final String PASS = "webstudent";

    @Resource(name="jdbc/web_student_tracker")
    private javax.sql.DataSource datasource;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setContentType("text/plain");
        List<String> list = new ArrayList<String>();


        try{
            // Register JDBC driver
            // It is not necessary in newer versions
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection

            // If you want to use context.xml
            //Connection conn = datasource.getConnection();

            //If you want to hard code details
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String email = rs.getString("email");
                //Store the values
                list.add(new String("Mail: " + email+ "<br>"));

            }


            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
