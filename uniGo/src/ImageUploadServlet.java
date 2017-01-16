import gr.inf.unigo.Student;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUploadServlet extends HttpServlet
{
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        Student student = ( Student ) session.getAttribute( "user" );
        String userName = student.getUserName();

        BufferedImage image = ImageIO.read( req.getInputStream() );
        ImageIO.write( image, "jpg", new File( "/usr/share/tomcat/webapps/uniGo_war/images/profiles/" + userName + ".jpg" ) );


    }
}
