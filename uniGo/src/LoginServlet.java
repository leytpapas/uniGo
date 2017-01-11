import gr.inf.unigo.UniGoDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {

        RequestDispatcher view;

        String userName = req.getParameter( "user_name" );
        String password =  req.getParameter( "user_pass" );

        LDAP ldap = new LDAP( userName, password );

        if ( ldap.auth() )
        {
            UniGoDB db = ( UniGoDB ) getServletContext().getAttribute( "db" );
            try
            {
                db.addUser( userName, password );
            }
            catch ( SQLException e )
            {
                e.printStackTrace();
            }

            view = req.getRequestDispatcher( "success_login.jsp" );

        }
        else
        {
            view = req.getRequestDispatcher( "error_login.jsp" );
        }

        view.forward( req, resp );

    }
}