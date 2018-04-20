package ej;

import login.DBOper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class JobsDBOper {

    private List<Job> listofNames = new ArrayList<>();

    public void addJobs(Job s, int fkuser) throws ClassNotFoundException, SQLException {

        if ((s.getTitle()!=null && s.getTitle().trim().length() > 0) && (s.getDescription()!=null && s.getDescription().trim().length() > 0)) {
            Class.forName("org.postgresql.Driver");


            Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO jobs (title, description, iduser, data_add) VALUES (?,?, ?, now())");
            pSt.setString(1, s.getTitle());
            pSt.setString(2, s.getDescription());
            pSt.setInt(3, fkuser);

            int rowsInserted = pSt.executeUpdate();

            pSt.close();
            conn.close();
        }

    }

    public List getMyJobs(int iduser) throws ClassNotFoundException, SQLException{

        Class.forName("org.postgresql.Driver");




        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        System.out.println(iduser);
        PreparedStatement pSt = conn.prepareStatement("SELECT * FROM jobs where iduser=? order by data_add desc");
        pSt.setInt(1, iduser);
        ResultSet rs = pSt.executeQuery();
        while(rs.next()) {
            Job ej = new Job();
            ej.setId(rs.getInt("id"));
            ej.setTitle(rs.getString("title"));
            ej.setDescription(rs.getString("description"));
            listofNames.add(ej);
        }

        pSt.close();
        conn.close();

        return listofNames;
    }

    public List getAllJobsFilter(String title, String description) throws ClassNotFoundException, SQLException{

        Class.forName("org.postgresql.Driver");

        if(title == null) {
            title = "";
        }
        if(description == null) {
            description = "";
        }

        if(title.equals("") && description.equals(""))
            return listofNames;

        String query = "SELECT * FROM jobs WHERE title like '%" + title + "%' and  description like '%" + description + "%' ORDER BY data_add desc";
        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);
        Statement s = conn.createStatement();

        //PreparedStatement pSt = conn.prepareStatement("SELECT * FROM jobs  order by data_add desc");
        ResultSet rs = s.executeQuery(query);
        while(rs.next()) {
            Job ej = new Job();
            ej.setId(rs.getInt("id"));
            ej.setTitle(rs.getString("title"));
            ej.setDescription(rs.getString("description"));
            listofNames.add(ej);
        }

        s.close();
        conn.close();

        return listofNames;
    }
    public static void demoDelete(long id) throws ClassNotFoundException, SQLException {

        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");



        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM jobs WHERE id=?");
        pSt.setLong(1,id);


        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 6. close the objects
        pSt.close();
        conn.close();
    }


}


