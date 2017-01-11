package gr.inf.unigo;

import java.sql.*;

public class UniGoDB
{
    Connection conn;
    String dbName;

    public UniGoDB( String userName, String password, String dbName ) throws ClassNotFoundException, SQLException
    {
        this.dbName = dbName;
        Class.forName( "org.mariadb.jdbc.Driver" );
        this.conn = DriverManager.getConnection( "jdbc:mariadb://83.212.97.212/" + this.dbName + "?useUnicode=yes&characterEncoding=UTF-8", userName, password );

    }

    public void addUser( String userName, String password ) throws SQLException
    {
        Statement stmt = null;

        String query = "INSERT INTO Student( userName, password ) VALUES (\'" + userName + "\', \'" + password + "\')";

        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( query );

        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( stmt != null )
                stmt.close();
        }
    }

    public void updateUser()
    {
//        UPDATE `Student` SET userName='test' WHERE studentID=4528

    }

    public boolean searchUser( String userName ) throws SQLException
    {
        Statement stmt = null;

        String query = "SELECT * FROM Student WHERE userName LIKE '" + userName + "'";

        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( query );

            return rs.isBeforeFirst();

        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( stmt != null )
                stmt.close();
        }

        return false;

    }


}
