import gr.inf.unigo.Announcement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SingleAnnouncementServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        ArrayList<Announcement> announcements = ( ArrayList<Announcement> ) getServletContext().getAttribute( "announcements" );

        for ( Announcement announcement : announcements )
        {
            if ( announcement.getId().equals( req.getParameter( "id" ) ) )
            {
                req.setAttribute( "announcement", announcement );

                RequestDispatcher view;

                view = req.getRequestDispatcher( "single-announcement.jsp" );

                view.forward( req, resp );

                break;
            }
        }
    }
}
