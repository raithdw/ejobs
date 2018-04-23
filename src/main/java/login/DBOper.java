package login;

import java.sql.*;

public class DBOper {
    public final static String URL = "jdbc:postgresql://54.93.65.5:5432/mihai7";
    public final static String USERNAME = "fasttrackit_dev";
    public final static String PASSWORD = "fasttrackit_dev";

    /* -1 daca nu am gasit , id-ul daca am gasit */
    public int login (String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement st = conn.createStatement();

            String query = "SELECT id FROM users where username='"+user+"' and password='"+pwd+"'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                found = rs.getInt("id");
            }

            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;

    }

    /* -1 daca nu am gasit , id-ul daca am gasit */
    public int register (String user, String pwd) {

        int found = -1;

        try {

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!user.equals("") && (!pwd.equals(""))) {
                PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
                pSt.setString(1, user);
                pSt.setString(2, pwd);

                int rowsInserted = pSt.executeUpdate();

                pSt.close();

                conn.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;

    }

}
