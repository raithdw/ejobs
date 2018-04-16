package ir;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/publicEjobs")
public class PublicEjobs extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        String action = req.getParameter("action");

        if (action != null && action.equals("read"))
            read(req, resp);

    }



    private void read(HttpServletRequest req, HttpServletResponse resp) {
        //JobsDBOper listQA = JobsDBOper.getInstance();
        JobsDBOper jobs = new JobsDBOper();

        int iduser=-1;


        JSONObject json = new JSONObject();
        try {
            json.put("ejobs", jobs.getAllJobsFilter(req.getParameter("title"), req.getParameter("description")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnJsonResponse(resp, json.toString());
    }


    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}