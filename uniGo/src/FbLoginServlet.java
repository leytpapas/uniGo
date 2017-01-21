import gr.inf.unigo.CourseRegistration;
import gr.inf.unigo.Parser;
import gr.inf.unigo.Student;
import gr.inf.unigo.UniGoDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class FbLoginServlet extends HttpServlet
{
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {

        RequestDispatcher view;

        String userName = req.getParameter( "user_name" );
        String fb_id =  req.getParameter( "fb_id" );
        String gender = req.getParameter( "gender" );

        UniGoDB db = ( UniGoDB ) getServletContext().getAttribute( "db" );
        Student student = null;
        try
        {
            student = db.getStudent( userName );
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        try
        {
            HttpSession session = req.getSession();
            session.setAttribute( "user", student );

            if ( db.searchUserByFbID( fb_id ) )
            {
                Parser parser = new Parser();
                CourseRegistration courseRegistration;

                while ( true )
                {
                    courseRegistration = parser.getUserDetails( "https://euniversity.uth.gr/unistudent/login.asp", student.getUserName(), student.getPassword() );

                    if ( courseRegistration != null )
                        break;
                }

                session.setAttribute( "reggedCourses", courseRegistration );

                view = req.getRequestDispatcher( "success_login.jsp" );
            }
            else if ( db.searchUserByUserName( userName ) )
            {
                db.addFacebookID( userName, fb_id, gender );

                Parser parser = new Parser();
                CourseRegistration courseRegistration;

                while ( true )
                {
                    courseRegistration = parser.getUserDetails( "https://euniversity.uth.gr/unistudent/login.asp", student.getUserName(), student.getPassword() );

                    if ( courseRegistration != null )
                        break;
                }

                session.setAttribute( "reggedCourses", courseRegistration );

                view = req.getRequestDispatcher( "success_login.jsp" );
            }
            else
            {
                view = req.getRequestDispatcher( "error_login.jsp" );
            }

            view.forward( req, resp );


        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }




    }
}