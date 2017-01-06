package gr.inf.unigo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Announcement
{
    private String title;
    private String pubDate;
    private String description;
    private String url;
    private String category;
    private String day;
    private String month;
    private String id;

    public Announcement( String title, String pubDate, String description, String url, String category, String id )
    {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.url = url;
        this.category = category;
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getDay()
    {
        day = pubDate.replaceFirst( "\\w+, ", "" );
        day = day.substring( 0, day.indexOf( " " ) );

        return day;
    }

    public void setDay( String day )
    {
        this.day = day;
    }

    public String getMonth()
    {
        month = pubDate.replaceFirst( "\\w+, " + day + " ", "" );
        month = month.substring( 0, month.indexOf( " " ) );

        Date date = null;
        try
        {
            date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTime( date );
            month = new SimpleDateFormat("MMMM").format( mCalendar.getTime() );
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
            return "Unknown";
        }

        return month;
    }

    public void setMonth( String month )
    {
        this.month = month;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate( String pubDate )
    {
        this.pubDate = pubDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }
}
