import gr.inf.unigo.UniGoDB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;


public class StartListener implements ServletContextListener
{
    @Override
    public void contextInitialized( ServletContextEvent servletContextEvent )
    {
        try
        {
            String userName;
            String password;
            String dbName;

            UniGoDB uniGoDB = new UniGoDB( "unigo", "unipass", "uniGo" );

            servletContextEvent.getServletContext().setAttribute( "db", uniGoDB );
        }
        catch ( ClassNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed( ServletContextEvent servletContextEvent )
    {
        // TODO close database connection
    }

}