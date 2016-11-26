
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by aptsaous on 25/11/2016.
 */
public class Test
{
    public static void main( String[] args ) throws IOException
    {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet( "http://10.64.4.64/unistudent" );
        httpGet.addHeader( "Host", "euniversity.uth.gr" );
        httpGet.addHeader( "User-Agent", "curl/7.49.1" );
        httpGet.addHeader( "Accept", "*/*" );
        System.out.println( Arrays.toString( httpGet.getAllHeaders() ) );

        HttpResponse response = client.execute( httpGet );

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString( entity, "Windows-1253" );
        System.out.println(responseString);
//        URL url = null;
//        try
//        {
//            //  curl --verbose --header 'Host: euniversity.uth.gr' 10.64.4.64/unistudent/
//            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
//            url = new URL( "http://10.64.4.64/unistudent/" );
//            HttpURLConnection urlConnection = ( HttpURLConnection ) url.openConnection();
//            urlConnection.setRequestProperty( "Host", "euniversity.uth.gr" );
//            urlConnection.setRequestProperty( "User-Agent", "curl/7.49.1" );
//            urlConnection.setRequestProperty( "Accept", "*/*" );
//
//
//            System.out.println( urlConnection.getResponseMessage() + "   lol" );
//        }
//        catch ( MalformedURLException e )
//        {
//            e.printStackTrace();
//        }
//        catch ( IOException e )
//        {
//            e.printStackTrace();
//        }

    }
}
