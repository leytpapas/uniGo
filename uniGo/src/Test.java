import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Test
{
    public static void main( String[] args ) throws Exception
    {
        String pubDate = "Wed, 21 Dec 2016 09:21:22 +0000";
        String day = pubDate.replaceFirst( "\\w+, ", "" );
        day = day.substring( 0, day.indexOf( " " ) );
        System.out.println( day );
        String month = pubDate.replaceFirst( "\\w+, " + day + " ", "" );
        month = month.substring( 0, month.indexOf( " " ) );
        //String month = "Dec";
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime( date );
        System.out.println(new SimpleDateFormat("MMMM").format(mCalendar.getTime()));

        int i=2;

        String h = Integer.toString( i );

        System.out.println( h );

//        String month2 = mCalendar.toString();
//        System.out.println( month2);
////        int month = cal.get( Calendar.MONTH);

//        Announcements announcements = new Announcements();
//
//        System.out.println( announcements.getAnnouncements( "https://www.e-ce.uth.gr/category/announcements/feed/?cat=4" ) );
//        Parser parse = new Parser();
//
//        //parse.getWeeklySchedule();
//        //        https://www.e-ce.uth.gr/studies/undergraduate/
//        //        https://www.e-ce.uth.gr/studies/undergraduate/fall-timetable/week/
//
//        parse.getCourse( "https://www.e-ce.uth.gr/studies/undergraduate/courses/ce110/" );
////
//        String html = parse.getUserDetails( "https://euniversity.uth.gr/unistudent/login.asp" );
//
//        Connection conn = null;
//        Statement stmt = null;
//        try
//        {
//            //STEP 2: Register JDBC driver
//            Class.forName( "org.mariadb.jdbc.Driver" );
//
//            //STEP 3: Open a connection
//            System.out.println( "Connecting to a selected database..." );
//            conn = DriverManager.getConnection( "jdbc:mariadb://83.212.97.212", "unigo", "unipass" );
//            System.out.println( "Connected database successfully..." );
//
//            //STEP 4: Execute a query
//            System.out.println( "Creating table in given database..." );
//            stmt = conn.createStatement();
//
//            String sql = "CREATE DATABASE uniGo";
//
////            String sql = "CREATE TABLE REGISTRATION " +
////                    "(id INTEGER not NULL, " +
////                    " first VARCHAR(255), " +
////                    " last VARCHAR(255), " +
////                    " age INTEGER, " +
////                    " PRIMARY KEY ( id ))";
//
//            stmt.executeUpdate( sql );
//            System.out.println( "Created table in given database..." );
//
//        }
//        catch ( SQLException ex )
//        {
//            ex.printStackTrace();
//        }
    }

//    public static void viewTable(Connection con, String dbName) throws SQLException
//    {
//
//        Statement stmt = null;
//        String query = "select COF_NAME, SUP_ID, PRICE, " +
//                        "SALES, TOTAL " +
//                        "from " + dbName + ".COFFEES";
//
//        try {
//            stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String coffeeName = rs.getString("COF_NAME");
//                int supplierID = rs.getInt("SUP_ID");
//                float price = rs.getFloat("PRICE");
//                int sales = rs.getInt("SALES");
//                int total = rs.getInt("TOTAL");
//                System.out.println(coffeeName + "\t" + supplierID +
//                        "\t" + price + "\t" + sales +
//                        "\t" + total);
//            }
//        } catch (SQLException e ) {
//            e.printStackTrace();
//        } finally {
//            if (stmt != null) { stmt.close(); }
//        }
//    }
}
