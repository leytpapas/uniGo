import gr.inf.unigo.UniGoDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        RequestDispatcher view;

//        HttpSession session = req.getSession();
//        session.setAttribute( "gender", "male" );

        view = req.getRequestDispatcher( "index.jsp" );

        view.forward( req, resp );
    }
}
