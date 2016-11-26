

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import gr.inf.unigo.*;

/**
 * Created by aptsaous on 25/11/2016.
 */
public class Test
{
    public static void main( String[] args ) throws IOException
    {
        Parser parse = new Parser();

        parse.getWeeklySchedule();

        parse.parseUniversity("elena","%el16619",1);
//


    }
}
