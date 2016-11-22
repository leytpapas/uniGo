<%@ page import="gr.inf.unigo.Student" %>
<%@ page import="gr.inf.unigo.Parser" %>
<%--
  Created by IntelliJ IDEA.
  User: aptsaous
  Date: 19/11/2016
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  DEMO
  <%
    Parser parser = new Parser();
    try
    {
      String user = request.getAttribute( "user" ).toString();
      String passwd = request.getAttribute( "passwd" ).toString();

      out.println( "UserName: " + user + " Password: " + passwd );
      out.println("Testing..");

      String str =  parser.parseUniversity(  user, passwd );

      out.println( parser.grades );
      out.println("Testing..2");
      out.println( str );
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }

    out.println( );

  %>
  </body>
</html>
