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
//        String uid = req.getParameter( "uid" );
//        String passwd = req.getParameter( "passwd" );
//
//        req.setAttribute( "user", uid );
//        req.setAttribute( "passwd", passwd );

        RequestDispatcher view;

        if ( req.getParameter( "user_name" ).equals( "aptsaous" ) && req.getParameter( "user_pass" ).equals( "123" ) )
        {
            view = req.getRequestDispatcher( "success_login.jsp" );
        }
        else
        {
            view = req.getRequestDispatcher( "error_login.jsp" );
        }

        view.forward( req, resp );

    }
}