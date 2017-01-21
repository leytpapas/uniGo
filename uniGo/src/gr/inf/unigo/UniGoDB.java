package gr.inf.unigo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

        String query = "SELECT * FROM Student WHERE userName LIKE '" + user + "'";

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
                String password = rs.getString( "password" );

                Student student = new Student( userName, facebookID, studentID, firstName, lastName, gender, password  );

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

    public void addPointsToUser( String userName, int points ) throws SQLException
    {
        Statement stmt = null;

        String query = "UPDATE Student SET XP=(XP+" + points + ") WHERE userName=\'" + userName + "\'";

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

    public ArrayList<RankingEntry> getRankings( String user ) throws SQLException
    {
        Statement stmt = null;
        ArrayList<RankingEntry> rankings = new ArrayList<RankingEntry>();
        boolean inTop5 = false;

        String queryUserRank = "SELECT  uo.*, \n" +
                "        (\n" +
                "        SELECT  COUNT(*)\n" +
                "        FROM    Student ui\n" +
                "        WHERE   (ui.XP) >= (uo.XP)\n" +
                "        ) AS rank\n" +
                "FROM    Student uo\n" +
                "WHERE   userName LIKE '" + user + "'";

        String query = "SELECT userName, XP FROM Student ORDER BY XP DESC LIMIT 5";

        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( query );

            while ( rs.next() )
            {
                String userName = rs.getString( "userName" );
                int xp = rs.getInt( "XP" );

                if ( userName.equals( user ) )
                    inTop5 = true;

                RankingEntry rankingEntry = new RankingEntry( userName, xp );

                rankings.add( rankingEntry );

            }

            if ( !inTop5 )
            {
                stmt = conn.createStatement();
                rs = stmt.executeQuery( queryUserRank );

                while ( rs.next() )
                {
                    String userName = rs.getString( "userName" );
                    int xp = rs.getInt( "XP" );
                    int rank = rs.getInt( "rank" );

                    RankingEntry rankingEntry = new RankingEntry( userName, xp );
                    rankingEntry.setRank( rank );

                    rankings.add( rankingEntry );

                }
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

        return rankings;
    }

    public void insertSchedule(String dbName, String course, String day, String time, String courseType, String classroom) throws SQLException {
        Statement stmt=null;

        String query= "insert into "+dbName+ ".Schedule(course, dayCourse, timeCourse, courseType, classroom)" + " values('"+course+"','"+ day+ "','"+ time+"','"+ courseType+"','"+ classroom+"')";

        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException e ) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }

    }

    public ArrayList<Course> getDailySchedule( CourseRegistration courseRegistration ) throws SQLException
    {
        Statement stmt = null;
        StringBuilder queryStr = new StringBuilder();

        if ( courseRegistration.getRegisteredCourses().isEmpty() )
            return null;

        ArrayList<Course> schedule = new ArrayList<Course>();

        queryStr.append( "SELECT * FROM Schedule WHERE ( " );

        int i;

        for ( i = 0; i < courseRegistration.getRegisteredCourses().size(); i++ )
        {
            if ( i == courseRegistration.getRegisteredCourses().size()-1 )
                break;

            queryStr.append( "course LIKE '%" + courseRegistration.getRegisteredCourses().get( i ) + "%' OR " );
        }

        queryStr.append( "course LIKE '%" + courseRegistration.getRegisteredCourses().get(i) + "%' )" );

//        course LIKE '%410%' OR course LIKE '%420%' ) AND dayCourse LIKE 'Τετάρτη'";

        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( queryStr.toString() );

            while ( rs.next() )
            {
                String courseName = rs.getString( "course" );
                String day = rs.getString( "dayCourse" );
                String timeCourse = rs.getString( "timeCourse" );
                String courseType = rs.getString( "courseType" );
                String classroom = rs.getString( "classroom" );

                Course course = new Course( courseName, day, timeCourse, classroom );

                schedule.add( course );

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

        return schedule;
    }

//    public void addPointsToUser( int points, String u )

}
