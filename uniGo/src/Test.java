import gr.inf.unigo.*;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Test
{
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";

    public static void main( String[] args ) throws Exception
    {
//        String eceURL = "https://www.e-ce.uth.gr/";
//
//        org.jsoup.Connection.Response loginForm = Jsoup.connect( eceURL )
//                        .userAgent( USER_AGENT )
//                        .method( org.jsoup.Connection.Method.GET )
//                        .execute();
//
//        Document doc = loginForm.parse();

        org.jsoup.Connection.Response loginForm2;

        String userName = "user";
        String password = "pass";
        String wp_submit = "";
        String redirect_to = "https://www.e-ce.uth.gr";// doc.getElementsByAttributeValue( "name", "redirect_to" ).first().val();

        try
        {
            loginForm2 = Jsoup.connect( "https://www.e-ce.uth.gr/wp-login.php" )
                    .method( org.jsoup.Connection.Method.POST )
                    .referrer( "https://www.e-ce.uth.gr/" )
                    .userAgent( USER_AGENT )
                    .data( "log", userName )
                    .data( "pwd", password )
                    .data( "wp-submit", wp_submit ).data( "redirect_to", redirect_to )
                    //.cookies( loginForm.cookies() )
                    .execute();

            Document document = loginForm2.parse();
            System.out.println( document.html() );

            org.jsoup.Connection.Response loginForm3 = Jsoup.connect( "https://www.e-ce.uth.gr/category/announcements/feed/?cat=4" )
                    .method( org.jsoup.Connection.Method.GET )
                    .referrer( "https://www.e-ce.uth.gr/" )
                    .userAgent( USER_AGENT )
                    .cookies( loginForm2.cookies() )
                    .execute();

            Document document2 = loginForm3.parse();
            ArrayList<Announcement> announcements = new ArrayList<Announcement>();
            System.out.println( document2.html() );
//            int id = 1;
//            Elements news = document2.getElementsByTag( "item" );
//
//            for ( Element neo : news )
//            {
//                String title = neo.select( "title" ).html();
//                String pubDate = neo.select( "pubDate" ).html();
//                pubDate = pubDate.replaceAll( "\\+0000", "" );
//                String category = neo.select( "category" ).html();
//                String url = neo.select( "guid" ).html();
//                String description = neo.select( "description" ).html();
//
//                Announcement announcement = new Announcement( title, pubDate, description, url, category, Integer.toString( id ) );
//
//                id++;
//
//                announcements.
//
//            }

        }
        catch ( HttpStatusException e )
        {
            e.printStackTrace();
        }



//
    }

}
