package gr.inf.unigo;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
{
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";
    public String grades;
//    public UniGoDB uniGoDB;
//
//    public Parser() throws SQLException, ClassNotFoundException
//    {
////        this.uniGoDB = new UniGoDB( "unigo", "unipass", "uniGo" );
//
//    }

    /* Finds eUniversity's secret input field in the submit form, decodes it, reconstructs it
           and finally returns the key-value pair of the input field */
    public String getFormCode( String form )
    {
        System.out.println( form );
        form = form.substring( form.indexOf( "$" ) );
        form = form.replaceAll( "\\+", "" );
        form = form.replaceAll( "\\'", "" );

        String pattern = "([\\\\x0-9]{136,})|([\\\\x0-9]{132})";
        Pattern r = Pattern.compile( pattern );
        Matcher m = r.matcher( form );

        StringBuilder valueStr = new StringBuilder();
        StringBuilder keyStr = new StringBuilder();

        while ( m.find() )
        {
            if ( m.group( 1 ) != null )
            {
                String value = m.group( 1 ).replaceAll( "\\\\x", " " );
                String[] valTokens = value.split( "\\s+" );

                for ( String tkn : valTokens )
                {
                    if ( tkn.isEmpty() )
                        continue;

                    int decimal = Integer.parseInt( tkn, 16 );
                    valueStr.append( ( char ) decimal );
                }
            }
            else
            {
                String key = m.group( 2 ).replaceAll( "\\\\x", " " );
                String[] keyTokens = key.split( "\\s+" );

                for ( String tkn : keyTokens )
                {
                    if ( tkn.isEmpty() )
                        continue;

                    int decimal = Integer.parseInt( tkn, 16 );
                    keyStr.append( ( char ) decimal );
                }
            }
        }

        keyStr.append( ":" + valueStr );

        return keyStr.toString();
    }

//    public String getSchedule() throws Exception
//    {
//
//        ArrayList<String> htmlList = new ArrayList<String>();
//
//        Document doc = Jsoup.connect( "https://www.e-ce.uth.gr/studies/undergraduate/" ).get();
//        Element rows4= doc.getElementById("tabs-1-3");
//
//
//        Elements rows = rows4.getElementsByTag( "td" );
//
//        ArrayList<String> subject = new ArrayList<String>();
//        ArrayList<StringBuffer> schedule = new ArrayList<StringBuffer>();
//
//        for(Element row1: rows) {
//            Elements rows2= row1.getElementsByTag("a");
//            for(Element row2: rows2) {
//                String sub = row2.getElementsByAttribute("title").text();
//                Elements rows3= row2.getElementsByAttribute("title");
//                if (!sub.equals(" ")) {
//                    subject.add(sub);
//                }
//                for( Element row3: rows3) {
//                    String html2 = row3.attr("href");
//
//                    htmlList.add(html2);
//
//                    System.out.println(html2);
//
//                }
//            }
//        }
//
//
//
//        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
//        lhs.addAll(htmlList);
//        htmlList.clear();
//        htmlList.addAll( lhs );
//
//        for(int i = 0; i < htmlList.size(); i++) {
//            //String url= getCourseWebSite(htmlList.get(i));
//            getCourse(htmlList.get(i));
//        }
//
//
//        return "ok";
//    }

//    public Course getCourse( String courseURL ) throws IOException, SQLException
//    {
//        Document doc = Jsoup.connect( courseURL ).get();
//
//        String title = doc.getElementById( "page-heading" ).getElementsByTag( "h1" ).html();
//        String code = title.substring( 0, title.indexOf( ' ' ) );
//        title = title.replaceFirst( code + " ", "" );
//        System.out.println( code );
//        System.out.println( title );
//
//        Element courseDetails = doc.getElementsByClass( "course" ).get( 2 );
//        Elements rows = courseDetails.select( "tr" );
//        String subjectArea = null, semester = null, type = null, teachingPeriod = null, ECTS = null, url = null, instructor = null, eMail = null, jobTitle = null;
//        ArrayList<String> books = new ArrayList<String>();
//        String imageUrl = null;
//        ArrayList<String> days = new ArrayList<String>();
//        ArrayList<String> time = new ArrayList<String>();
//        ArrayList<String> courseType = new ArrayList<String>();
//        ArrayList<String> classroom = new ArrayList<String>();
//        String courseName=null;
//
//        String info=null;
//
//        Element row3= doc.getElementById( "page-heading" );
//        Elements rows3= row3.getElementsByTag( "div" );
//        for (Element row4:rows3){
//            courseName= row4.getElementsByTag("h1").text();
//
//            Elements row1= doc.getElementsByClass( "sbody" );
//            for (Element row2: row1){
//                days.add(row2.getElementsByTag("td").get(0).text());
//                time.add(row2.getElementsByTag("td").get(1).text());
//                courseType.add( row2.getElementsByTag( "td" ).get( 2 ).text() );
//                classroom.add( row2.getElementsByTag( "td" ).get( 3 ).text() );
//                String time2 = new String( row2.getElementsByTag( "td" ).get( 1 ).text().getBytes(), "UTF-8" );
//                uniGoDB.insertSchedule( "uniGo", courseName, row2.getElementsByTag( "td" ).get( 0 ).text(), time2, row2.getElementsByTag( "td" ).get( 2 ).text(), row2.getElementsByTag( "td" ).get( 3 ).text() );
//
//            }
//
//        }
//
//        for ( Element row : rows )
//        {
//            if ( row.getElementsByTag( "th" ).html().contains( "Γνωστικό Αντικείμενο" ) )
//                subjectArea = row.getElementsByTag( "td" ).html();
//            else if ( row.getElementsByTag( "th" ).html().contains( "Εξάμηνο" ) )
//                semester = row.getElementsByTag( "td" ).html();
//            else if ( row.getElementsByTag( "th" ).html().contains( "Τύπος Μαθήματος" ) )
//                type = row.getElementsByTag( "td" ).html();
//            else if ( row.getElementsByTag( "th" ).html().contains( "Ώρες Διδασκαλίας" ) )
//                teachingPeriod = row.getElementsByTag( "td" ).html();
//            else if ( row.getElementsByTag( "th" ).html().contains( "Μονάδες ECTS" ) )
//                ECTS = row.getElementsByTag( "td" ).html();
//            else if ( row.getElementsByTag( "th" ).html().contains( "Σελίδα Μαθήματος" ) )
//                url = row.getElementsByTag( "td" ).select( "a" ).attr( "href" );
//            else if ( row.getElementsByTag( "th" ).html().contains( "Υπεύθυνοι Μαθήματος" ) )
//            {
//                instructor = row.getElementsByTag( "td" ).select( "a" ).first().html();
//                eMail = row.getElementsByTag( "td" ).select( "a" ).last().html();
//                jobTitle = row.getElementsByTag( "td" ).text().replaceAll( instructor + ", ", "" ).replaceAll( "E-mail: " + eMail, "" );
//                imageUrl = row.getElementsByTag( "td" ).select( "img" ).attr( "src" );
//            }
//            else if ( row.getElementsByTag( "th" ).html().contains( "Συγγράμματα" ) )
//            {
//                for ( Element book : row.getElementsByTag( "ul" ).select( "li" ) )
//                    books.add( book.text() );
//            }
//        }
//
//        System.out.println( subjectArea );
//        System.out.println( semester );
//        System.out.println( type );
//        System.out.println( teachingPeriod );
//        System.out.println( ECTS );
//        System.out.println( url );
//        System.out.println( instructor );
//        System.out.println( eMail );
//        System.out.println( jobTitle );
//        System.out.println( imageUrl );
//
//        for(int i = 0; i < days.size(); i++) {
//            System.out.print( "Day: " + days.get( i ) +" Time: "+ time.get(i)+ " CourseType: "+ courseType.get(i)+ " Classroom: "+ classroom.get(i)+ "\n" );
//
//        }
//        System.out.println( "------------------------------------------\n" );
//
//        return null;
//    }

    public CourseRegistration getUserDetails( String eUniversityURL, String userName, String password ) throws IOException, SQLException
    {
        Connection.Response loginForm = Jsoup.connect( eUniversityURL )
                .userAgent( USER_AGENT )
                .method( Connection.Method.GET )
                .execute();

        Document doc = loginForm.parse();

        String loginTrue = doc.getElementsByAttribute( "tabIndex" ).attr( "value" );
        loginTrue = ( java.net.URLEncoder.encode( loginTrue, "Cp1253" ) );

        Elements elements = doc.getElementsByAttributeValue( "type", "text/javascript" );

        String code = getFormCode( elements.last().html() );
        System.out.println( code );
        String key = code.substring( 0, code.indexOf( ":" ) );
        String val = code.replaceFirst( key + ":", "" );
        System.out.println( key );
        System.out.println( val );

        Connection.Response loginForm2;

        try
        {
            loginForm2 = Jsoup.connect( eUniversityURL )
                    .method( Connection.Method.POST )
                    .referrer( "https://euniversity.uth.gr/unistudent/login.asp" )
                    .userAgent( USER_AGENT )
                    .data( "userName", userName )
                    .data( "pwd", password )
                    .data( "submit1", loginTrue )
                    .data( "loginTrue", "login" )
                    .data( key, val )
                    .cookie( "login", "True" )
                    .cookies( loginForm.cookies() )
                    .execute();
        }
        catch ( HttpStatusException e )
        {
            return null;
        }


        Document document = loginForm2.parse();
        System.out.println( document.html() );

        Elements div = document.getElementsByAttributeValue( "style", "MARGIN-TOP: 20px" );
        String uName = div.first().getElementsByAttributeValue( "style", "height:20" ).first().getElementsByAttributeValue( "width", "250" ).html();

        Elements details = div.first().getElementsByAttributeValue( "height", "20" );

        for ( Element detail : details )
        {
            System.out.println( detail.select( "td" ).last().html() );
        }

        System.out.println( uName );

        String email = div.first().getElementById( "viewEmail" ).select( "a" ).text();

        System.out.println( email );

//        Connection.Response userGrades = Jsoup.connect( "https://euniversity.uth.gr/unistudent/stud_CResults.asp" )
//                .method( Connection.Method.GET )
//                .userAgent( USER_AGENT )
//                .cookies( loginForm.cookies() )
//                .execute();
//
//        Document gradesDoc = userGrades.parse();

        Connection.Response courseRegConn = Jsoup.connect( "https://euniversity.uth.gr/unistudent/stud_vClasses.asp?mnuid=diloseis;showDil" )
                .method( Connection.Method.GET )
                .userAgent( USER_AGENT )
                .cookies( loginForm.cookies() )
                .execute();

        Document courseRegDoc = courseRegConn.parse();

        // getStudentGrades( gradesDoc.html(), uName.toCharArray() );
//        getUserRegistration( registrationDoc.html() );

//        getStudentGrades( gradesDoc.html() );
        return getCourseReg( courseRegDoc, userName );

    }

    public CourseRegistration getCourseReg( Document courseRegDoc, String userName ) throws SQLException, UnsupportedEncodingException
    {

        Element dhlwsh = courseRegDoc.getElementsByAttribute( "bgcolor" ).first();
        Elements ma8hmata = dhlwsh.getElementsByAttributeValue( "height", "25" );

        CourseRegistration courseRegistration = new CourseRegistration( userName );

        for ( Element ma8hma : ma8hmata )
        {
            Elements ma8hmaDetails = ma8hma.select( "td" );

            String courseID = ma8hmaDetails.first().text();

            courseID = courseID.replaceFirst( "\\(", "" ).replaceFirst( "\\)", "" );
            courseRegistration.addCourseByID( courseID );
        }

        return courseRegistration;
    }

//    public String getWeeklySchedule()
//    {
//        StringBuilder html = new StringBuilder();
//
//        Document doc = Jsoup.parse( html.toString() );
//
//        ArrayList<Course> courses = new ArrayList<Course>();
//
//        Elements dayElement = doc.getElementsByAttributeValue( "href", "#tabs-1-1" );
//        String day = dayElement.html();
//
//        Element dayElementInfo = doc.getElementById( "tabs-1-1" );
//
//        Elements courseElementInfo = dayElementInfo.getElementsByClass( "sbody" );
//
//        for ( Element entry : courseElementInfo )
//        {
//            Elements info = entry.getElementsByTag( "td" );
//
//            String[] times = info.get( 0 ).text().split( " " );
//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm" );
//            Date startTime = null;
//            Date endTime = null;
//
//            try
//            {
//                startTime = simpleDateFormat.parse( times[0] );
//                endTime = simpleDateFormat.parse( times[2] );
//            }
//            catch ( ParseException e )
//            {
//                e.printStackTrace();
//            }
//
//            String courseName = info.get( 1 ).text();
//            String courseType = info.get( 2 ).text();
//            String classroom = info.get( 3 ).text();
//            String instructor = info.get( 4 ).text();
//
//            CourseInfo courseInfo = new CourseInfo( day, classroom, startTime, endTime );
//            Course course = new Course( courseName, instructor );
//            boolean found = false;
//
//            if ( courses.isEmpty() )
//            {
//                course.addCourseInfo( courseInfo );
//                courses.add( course );
//            }
//
//            for ( int i = 0; i < courses.size(); i++ )
//            {
//                if ( courses.get( i ).getCourseName().equals( courseName ) )
//                {
//                    courses.get( i ).addCourseInfo( courseInfo );
//                    found = true;
//                    break;
//                }
//            }
//
//            if ( !found )
//            {
//                course.addCourseInfo( courseInfo );
//                courses.add( course );
//            }
//        }
//
//
//
//        for ( Course course : courses )
//        {
//            System.out.println( course.getCourseName() );
//        }
//
//
//        return null;
//    }

    public String getStudentGrades( String html ) throws IOException
    {

        Document doc = Jsoup.parse( html );

        Elements rows = doc.getElementsByClass( "topBorderLight" );
        Elements rows2= doc.getElementsByClass( "redFonts" );

        ArrayList<String> gradesArray = new ArrayList<String>();
        ArrayList<String> lessonsArray = new ArrayList<String>();

        for ( Element row1 : rows2 )
        {
            String grade = row1.getElementsByTag("span").first().ownText();

            if (!grade.equals(""))
                gradesArray.add(grade);
        }


        for(Element row : rows) {

            String lesson = row.getElementsByTag("td").first().text();
            if(row.hasAttr("colspan")) {

                lessonsArray.add(lesson);
            }
        }
        for(int i = 0; i < lessonsArray.size(); i++) {
            System.out.print( "Lesson: " + lessonsArray.get( i ) + "\n" );
            System.out.println("Grade: "+ gradesArray.get(i));
        }
        return "ok";
    }

//    public String getStudentDetails( String html )
//    {
//        Document doc = Jsoup.parse( html );
//        Student student = new Student();
//
//        Elements elements = doc.select( "tr [height=20]" );
//
//        Element studentIDTag = doc.getElementById( "subheader" );
//        student.setFirstName( elements.first().html() );
//        System.out.println( elements.first() );
//        Elements studentIDinnerTag = studentIDTag.getElementsByTag( "span" );
//        String str = studentIDinnerTag.first().html();
//        String lastName =  elements.first().child( 1 ).html();
//        student.setStudentID( Integer.parseInt( str.substring( str.indexOf( "(" ) + 1, str.indexOf( ")" ) ) ) );
//        student.setLastName( lastName );
//        System.out.println( "Your student id is: " + student.getStudentID() + "\nYour last name is: " + student.getLastName() );
//
//        return student.toString();
//    }

    public void insertuserRegistration(String dbName, char[] userID, char[] courseID) throws SQLException, UnsupportedEncodingException
    {
        Statement stmt=null;

        String str= "Αποστολος";

        String values= "values('1692','" + str + "')" ;
        String query= "insert into "+dbName+ ".userRegistration(studentID, courseID)" + values;
        //
        // String query= "insert into "+dbName+ ".userRegistration(studentID, courseID)" + "values('"+userID+"','"+ courseID+"')";

        try
        {
            Class.forName( "org.mariadb.jdbc.Driver" );
        }
        catch ( ClassNotFoundException e )
        {
            e.printStackTrace();
        }
        java.sql.Connection conn = DriverManager.getConnection( "jdbc:mariadb://83.212.97.212/uniGo?useUnicode=yes&characterEncoding=UTF-8", "unigo", "unipass" );
        // DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname?useUnicode=yes&characterEncoding=UTF-8","username", "password");
        System.out.println( "Connected database successfully..." );

        try {


            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }


        query = "select courseID from " + dbName + ".userRegistration";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String stdID = rs.getString( "courseID" );
                System.out.println( stdID );
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }

    }

}