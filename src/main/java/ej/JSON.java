package ej;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/tl")
public class JSON extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        String action = req.getParameter("action");

        if (action != null && action.equals("read"))
            read(req, resp);
        else if (action != null && action.equals("write"))
            write(req, resp);
        else if (action != null && action.equals("delete"))
            delete(req, resp);

    }

    private void read(HttpServletRequest req, HttpServletResponse resp) {
        JobsDBOper listJob = new JobsDBOper();

        int iduser = -1;

        HttpSession s = req.getSession();
        Object o = s.getAttribute("userid");
        if (o != null) {
            iduser = (Integer) o;
        }

        JSONObject json = new JSONObject();
        try {
            json.put("myjobs", listJob.getMyJobs(iduser));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnJsonResponse(resp, json.toString());
    }

    private void write(HttpServletRequest req, HttpServletResponse resp) {

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Job ej = new Job();
        ej.setTitle(title);
        ej.setDescription(description);

        int fkuser = -1;

        HttpSession s = req.getSession();
        Object o = s.getAttribute("userid");
        if (o != null) {
            fkuser = (Integer) o;
        }

        if (fkuser != -1) {

            JobsDBOper listOfNames = new JobsDBOper();
            try {
                listOfNames.addJobs(ej, fkuser);
            } catch (ClassNotFoundException e) {

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("enter pe done");

        HttpSession session = req.getSession(true);

        String idS = req.getParameter("id");
        int id = Integer.parseInt(idS);
        try {
            JobsDBOper.deleteJobs(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resp.sendRedirect("admin.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("i am done");
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