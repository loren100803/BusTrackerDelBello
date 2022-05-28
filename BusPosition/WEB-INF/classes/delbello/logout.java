package delbello;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class logout extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
       HttpSession session = req.getSession();
       session.invalidate();
       res.sendRedirect(req.getContextPath()+"/");  
       
    }
}


