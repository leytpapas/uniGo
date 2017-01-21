import gr.inf.unigo.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        RequestDispatcher view;

        HttpSession session = req.getSession();
        CourseRegistration courseRegistration = ( CourseRegistration ) session.getAttribute( "reggedCourses" );

        UniGoDB db = ( UniGoDB ) getServletContext().getAttribute( "db" );

        try
        {
            ArrayList<Course> schedule = db.getDailySchedule( courseRegistration );

            session.setAttribute( "schedule", schedule );
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        view = req.getRequestDispatcher( "timetable.jsp" );

        view.forward( req, resp );
    }
}
