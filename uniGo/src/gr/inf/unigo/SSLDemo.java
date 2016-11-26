package gr.inf.unigo;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class SSLDemo
{
    public String getSSL()
    {
        try
        {

            CertificateFactory cf = CertificateFactory.getInstance( "X.509" );

            InputStream caInput = new BufferedInputStream( new FileInputStream( "/etc/pki/tls/certs/ca-bundle.crt" ) );
            Certificate ca = cf.generateCertificate( caInput );
            System.out.println( "ca=" + ( ( X509Certificate ) ca ).getSubjectDN() );

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance( keyStoreType );
            keyStore.load( null, null );
            keyStore.setCertificateEntry( "ca", ca );

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance( tmfAlgorithm );
            tmf.init( keyStore );

            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance( "TLS" );
            context.init( null, tmf.getTrustManagers(), null );

            System.setProperty( "sun.net.http.allowRestrictedHeaders", "true" );
            URL url = new URL( "https://10.64.4.64/unistudent/" );
            HttpsURLConnection urlConnection = ( HttpsURLConnection ) url.openConnection();
            //urlConnection.
            urlConnection.setRequestProperty( "Host", "euniversity.uth.gr" );
            urlConnection.setRequestProperty( "User-Agent", "curl/7.49.1" );
            urlConnection.setRequestProperty( "Accept", "*/*" );
            urlConnection.setSSLSocketFactory( context.getSocketFactory() );

            //return "SSL seems ok";

            return ( urlConnection.getResponseMessage() );
        }
        catch ( CertificateException e )
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch ( NoSuchAlgorithmException e )
        {
            e.printStackTrace();
            return e.getMessage();

        }
        catch ( KeyStoreException e )
        {
            e.printStackTrace();
            return e.getMessage();

        }
        catch ( KeyManagementException e )
        {
            e.printStackTrace();
            return e.getMessage();

        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
            return e.getMessage();

        }
        catch ( MalformedURLException e )
        {
            e.printStackTrace();
            return e.getMessage();

        }
        catch ( IOException e )
        {
            e.printStackTrace();
            return e.getMessage();

        }

    }

    public String getCurl() throws IOException
    {
        String[] command = { "curl", "--verbose", "euniversity.uth.gr" };
        ProcessBuilder process = new ProcessBuilder(command);
        Process p;

        p = process.start();
        BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        builder.append( "  End\n" );

        return builder.toString();
    }

//    public String getHtml()
//    {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet httpGet = new HttpGet( "https://10.64.4.64/unistudent" );
//        httpGet.addHeader( "Host", "euniversity.uth.gr" );
//        httpGet.addHeader( "User-Agent", "curl/7.49.1" );
//        httpGet.addHeader( "Accept", "*/*" );
//        System.out.println( Arrays.toString( httpGet.getAllHeaders() ) );
//
//        HttpResponse response = null;
//        try
//        {
//            response = client.execute( httpGet );
//            HttpEntity entity = response.getEntity();
//            String responseString = EntityUtils.toString( entity, "Windows-1253" );
//            return (responseString);
//        }
//        catch ( IOException e )
//        {
//            e.printStackTrace();
//            return e.toString();
//        }
//
//
//
//
//    }
}