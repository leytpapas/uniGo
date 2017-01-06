import gr.inf.unigo.Announcement;
import gr.inf.unigo.Announcements;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AnnouncementsServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        Announcements announcements = new Announcements();

        ArrayList<Announcement> announcementsList = announcements.getAnnouncements( "https://www.e-ce.uth.gr/category/announcements/feed/?cat=4" );
        req.setAttribute( "announcements", announcementsList );

        getServletContext().setAttribute( "announcements", announcementsList );
        RequestDispatcher view;

        view = req.getRequestDispatcher( "announcements.jsp" );

        view.forward( req, resp );
    }
}
