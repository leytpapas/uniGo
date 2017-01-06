package gr.inf.unigo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
{
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";
    public String grades;

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

    public Course getCourse( String courseURL ) throws IOException
    {
        Document doc = Jsoup.connect( courseURL ).get();

        String title = doc.getElementById( "page-heading" ).getElementsByTag( "h1" ).html();
        String code = title.substring( 0, title.indexOf( ' ' ) );
        title = title.replaceFirst( code + " ", "" );
        System.out.println( code );
        System.out.println( title );

        Element courseDetails = doc.getElementsByClass( "course" ).get(2);
        Elements rows = courseDetails.select( "tr" );
        String subjectArea = null, semester = null, type = null, teachingPeriod = null, ECTS = null, url = null, instructor = null, eMail = null, jobTitle = null;
        ArrayList<String> books = new ArrayList<String>();
        String imageUrl = null;

        for ( Element row : rows )
        {
            if ( row.getElementsByTag( "th" ).html().contains( "Γνωστικό Αντικείμενο" ) )
                subjectArea = row.getElementsByTag( "td" ).html();
            else if ( row.getElementsByTag( "th" ).html().contains( "Εξάμηνο" ) )
                semester = row.getElementsByTag( "td" ).html();
            else if ( row.getElementsByTag( "th" ).html().contains( "Τύπος Μαθήματος" ) )
                type = row.getElementsByTag( "td" ).html();
            else if ( row.getElementsByTag( "th" ).html().contains( "Ώρες Διδασκαλίας" ) )
                teachingPeriod = row.getElementsByTag( "td" ).html();
            else if ( row.getElementsByTag( "th" ).html().contains( "Μονάδες ECTS" ) )
                ECTS = row.getElementsByTag( "td" ).html();
            else if ( row.getElementsByTag( "th" ).html().contains( "Σελίδα Μαθήματος" ) )
                url = row.getElementsByTag( "td" ).select( "a" ).attr( "href" );
            else if ( row.getElementsByTag( "th" ).html().contains( "Υπεύθυνοι Μαθήματος" ) )
            {
                instructor = row.getElementsByTag( "td" ).select( "a" ).first().html();
                eMail = row.getElementsByTag( "td" ).select( "a" ).last().html();
                jobTitle = row.getElementsByTag( "td" ).text().replaceAll( instructor + ", ", "" ).replaceAll( "E-mail: " + eMail, "" );
                imageUrl = row.getElementsByTag( "td" ).select( "img" ).attr( "src" );
            }
            else if ( row.getElementsByTag( "th" ).html().contains( "Συγγράμματα" ) )
            {
                for ( Element book : row.getElementsByTag( "ul" ).select( "li" ) )
                    books.add( book.text() );
            }
        }

        System.out.println( subjectArea );
        System.out.println( semester );
        System.out.println( type );
        System.out.println( teachingPeriod );
        System.out.println( ECTS );
        System.out.println( url );
        System.out.println( instructor );
        System.out.println( eMail );
        System.out.println( jobTitle );
        System.out.println( imageUrl );

        return null;
    }

    public String getUserDetails( String eUniversityURL ) throws IOException
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


        Connection.Response loginForm2 = Jsoup.connect( eUniversityURL )
                .method( Connection.Method.POST )
                .referrer( "https://euniversity.uth.gr/unistudent/login.asp" )
                .userAgent( USER_AGENT )
                .data( "userName", "aptsaous" )
                .data( "pwd", "iNFUTH2012" )
                .data( "submit1", loginTrue )
                .data( "loginTrue", "login" )
                .data( key, val )
                .cookie( "login", "True" )
                .cookies( loginForm.cookies() )
                .execute();

        Document document = loginForm2.parse();

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

        Connection.Response userGrades = Jsoup.connect( "https://euniversity.uth.gr/unistudent/stud_CResults.asp" )
                .method( Connection.Method.GET )
                .userAgent( USER_AGENT )
                .cookies( loginForm.cookies() )
                .execute();

        Document gradesDoc = userGrades.parse();

        getStudentGrades( gradesDoc.html() );
        return "ok";



    }

    public String getWeeklySchedule()
    {
        StringBuilder html = new StringBuilder();

        Document doc = Jsoup.parse( html.toString() );

        ArrayList<Course> courses = new ArrayList<Course>();

        Elements dayElement = doc.getElementsByAttributeValue( "href", "#tabs-1-1" );
        String day = dayElement.html();

        Element dayElementInfo = doc.getElementById( "tabs-1-1" );

        Elements courseElementInfo = dayElementInfo.getElementsByClass( "sbody" );

        for ( Element entry : courseElementInfo )
        {
            Elements info = entry.getElementsByTag( "td" );

            String[] times = info.get( 0 ).text().split( " " );

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm" );
            Date startTime = null;
            Date endTime = null;

            try
            {
                startTime = simpleDateFormat.parse( times[0] );
                endTime = simpleDateFormat.parse( times[2] );
            }
            catch ( ParseException e )
            {
                e.printStackTrace();
            }

            String courseName = info.get( 1 ).text();
            String courseType = info.get( 2 ).text();
            String classroom = info.get( 3 ).text();
            String instructor = info.get( 4 ).text();

            CourseInfo courseInfo = new CourseInfo( day, classroom, startTime, endTime );
            Course course = new Course( courseName, instructor );
            boolean found = false;

            if ( courses.isEmpty() )
            {
                course.addCourseInfo( courseInfo );
                courses.add( course );
            }

            for ( int i = 0; i < courses.size(); i++ )
            {
                if ( courses.get( i ).getCourseName().equals( courseName ) )
                {
                    courses.get( i ).addCourseInfo( courseInfo );
                    found = true;
                    break;
                }
            }

            if ( !found )
            {
                course.addCourseInfo( courseInfo );
                courses.add( course );
            }
        }



        for ( Course course : courses )
        {
            System.out.println( course.getCourseName() );
        }


        return null;
    }

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

    public String getStudentDetails( String html )
    {
        Document doc = Jsoup.parse( html );
        Student student = new Student();

        Elements elements = doc.select( "tr [height=20]" );

        Element studentIDTag = doc.getElementById( "subheader" );
        student.setFirstName( elements.first().html() );
        System.out.println( elements.first() );
        Elements studentIDinnerTag = studentIDTag.getElementsByTag( "span" );
        String str = studentIDinnerTag.first().html();
        String lastName =  elements.first().child( 1 ).html();
        student.setStudentID( Integer.parseInt( str.substring( str.indexOf( "(" ) + 1, str.indexOf( ")" ) ) ) );
        student.setLastName( lastName );
        System.out.println( "Your student id is: " + student.getStudentID() + "\nYour last name is: " + student.getLastName() );

        return student.toString();
    }

}