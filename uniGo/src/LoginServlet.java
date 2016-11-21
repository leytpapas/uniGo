import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        String uid = req.getParameter( "uid" );
        String passwd = req.getParameter( "passwd" );

        RequestDispatcher view = req.getRequestDispatcher( "index.jsp" );

        view.forward( req, resp );
    }
}