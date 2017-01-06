package gr.inf.unigo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Announcements
{
    private ArrayList<Announcement> announcements;

    public Announcements()
    {
        this.announcements = new ArrayList<Announcement>();
    }

    public ArrayList<Announcement> getAnnouncements( String link ) throws IOException
    {
        Document doc = Jsoup.connect( link ).get();
        int id = 1;

        Elements news = doc.getElementsByTag( "item" );

        for ( Element neo : news )
        {
            String title = neo.select( "title" ).html();
            String pubDate = neo.select( "pubDate" ).html();
            pubDate = pubDate.replaceAll( "\\+0000", "" );
            String category = neo.select( "category" ).html();
            String url = neo.select( "guid" ).html();
            String description = neo.select( "description" ).html();

            Announcement announcement = new Announcement( title, pubDate, description, url, category, Integer.toString( id ) );

            id++;

            announcements.add( announcement );
        }

        return announcements;

    }
}
