import gr.inf.unigo.Student;
import gr.inf.unigo.UniGoDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ValidateCheckInServlet extends HttpServlet
{
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        Student user = ( Student ) session.getAttribute( "user" );

        UniGoDB db = ( UniGoDB ) getServletContext().getAttribute( "db" );

        try
        {
            db.addPointsToUser( user.getUserName(), 50 );

            PrintWriter out = resp.getWriter();

            out.println( "Valid Check-In! 50 XP pts earned!!" );
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }



    }
}
