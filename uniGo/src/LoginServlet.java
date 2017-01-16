import gr.inf.unigo.Student;
import gr.inf.unigo.UniGoDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {

        RequestDispatcher view = null;

        String userName = req.getParameter( "user_name" );
        String password =  req.getParameter( "user_pass" );
        String gender = req.getParameter( "user_gender" );



        UniGoDB db = ( UniGoDB ) getServletContext().getAttribute( "db" );

        try
        {
            if ( db.searchUserByUserName( userName ) )
            {
                db.updateGender( userName, gender );

                Student student = db.getStudent( userName );
                HttpSession session = req.getSession();
                session.setAttribute( "user", student );

                view = req.getRequestDispatcher( "success_login.jsp" );
            }
            else
            {
                LDAP ldap = new LDAP( userName, password );

                if ( ldap.auth() )
                {
                    db.addUser( userName, password, gender );

                    Student student = db.getStudent( userName );
                    HttpSession session = req.getSession();
                    session.setAttribute( "user", student );
                }
                else
                {
                    view = req.getRequestDispatcher( "error_login.jsp" );
                }
            }

            view.forward( req, resp );
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }
}