package ir;

import login.DBOper;

import java.sql.*;
import java.util.ArrayList;
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
            Job ir = new Job();
            ir.setId(rs.getInt("id"));
            ir.setTitle(rs.getString("title"));
            ir.setDescription(rs.getString("description"));
            listofNames.add(ir);
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
            Job ir = new Job();
            ir.setId(rs.getInt("id"));
            ir.setTitle(rs.getString("title"));
            ir.setDescription(rs.getString("description"));
            listofNames.add(ir);
        }

        s.close();
        conn.close();

        return listofNames;
    }


}



