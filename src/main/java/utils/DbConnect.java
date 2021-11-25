package utils;

import java.sql.*;

public class DbConnect {

    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String HOST = "postgres";
    private static final String PASSWORD = "Welcome123";

    public static Connection getConnection()  {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, HOST, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Statement st) {
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

    }

    public static void closeConnection(PreparedStatement st) {
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

    }

    public static void closeConnection(Connection con) {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
//				System.out.println(e.getMessage());
            }
    }

    public static void closeConnection(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
}

