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

    public void addUser( String userName, String password, String gender ) throws SQLException
    {
        Statement stmt = null;

        String query = "INSERT INTO Student( userName, password, gender ) VALUES (\'" + userName + "\', \'" + password + "\', \'" + gender + "\')";

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

    public void addFacebookID( String userName, String fbID, String gender ) throws SQLException
    {
        Statement stmt = null;

        String query = "UPDATE Student SET facebookID=\'" + fbID + "\', gender=\'" + gender + "\' WHERE userName=\'" + userName + "\'";

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

    public boolean searchUserByUserName( String userName ) throws SQLException
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

    public boolean searchUserByFbID( String fbID ) throws SQLException
    {
        Statement stmt = null;

        String query = "SELECT * FROM Student WHERE facebookID LIKE '" + fbID + "'";

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

    public Student getStudent( String user ) throws SQLException
    {
        Statement stmt = null;

        String query = "SELECT userName, facebookID, studentID, firstName, lastName, gender FROM Student WHERE userName LIKE '" + user + "'";

        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( query );

            if ( rs.next() )
            {
                String userName = rs.getString( "userName" );
                String facebookID = rs.getString( "facebookID" );
                int studentID = rs.getInt( "studentID" );
                String firstName = rs.getString( "firstName" );
                String lastName = rs.getString( "lastName" );
                String gender = rs.getString( "gender" );

                Student student = new Student( userName, facebookID, studentID, firstName, lastName, gender  );

                return student;
            }


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

        return null;
    }

    public void updateGender( String userName, String gender ) throws SQLException
    {
        Statement stmt = null;

        String query = "UPDATE Student SET gender=\'" + gender + "\' WHERE userName=\'" + userName + "\'";

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

}
