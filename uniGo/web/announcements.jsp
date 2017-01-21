<%@ page import="gr.inf.unigo.Announcement" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pages">
    <div data-page="projects" class="page no-toolbar no-navbar">
        <div class="page-content">

            <div class="navbarpages">
                <%--<div class="nav_left_logo"><a href="#" data-panel="left" class="open-panel"><img src="images/logo.png" alt="" title="" /></a></div>--%>
                <div class="nav_left_logo">
                    <div id="nav_top">
                        <a href="#" data-panel="left" class="open-panel" id="nav-top">
                            <img src="images/icons/white/menu.png" alt="" title="" />
                        </a>
                    </div>
                </div>
                <div class="nav_right_button">
                    <div id="nav_top2">
                        <%--<a href="menu.html"><img src="images/icons/white/menu.png" alt="" title="" /></a>--%>
                        <a href="home" id="nav-top2">
                            <img id="nav-logo" src="images/logo.png" alt="" title="" />
                        </a>
                        <%--<a href="#" data-panel="right" class="open-panel"><img src="images/icons/white/search.png" alt="" title="" /></a>--%>
                    </div>

                </div>
            </div>
            <%--<div class="navbarpages">--%>
                <%--&lt;%&ndash;<div class="nav_left_logo"><a href="#" data-panel="left" class="open-panel"><img src="images/logo.png" alt="" title="" /></a></div>&ndash;%&gt;--%>
                <%--<div class="nav_left_logo">--%>
                    <%--<div id="nav_top">--%>
                        <%--<a href="#" data-panel="left" class="open-panel" id="nav-top">--%>
                            <%--<img src="images/icons/white/menu.png" alt="" title="" />--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="nav_right_button">--%>
                    <%--<div id="nav_top2">--%>
                        <%--&lt;%&ndash;<a href="menu.html"><img src="images/icons/white/menu.png" alt="" title="" /></a>&ndash;%&gt;--%>
                        <%--<a href="home" id="nav-top2">--%>
                            <%--<img src="images/logo.png" alt="" title="" />--%>
                        <%--</a>--%>
                        <%--&lt;%&ndash;<a href="#" data-panel="right" class="open-panel"><img src="images/icons/white/search.png" alt="" title="" /></a>&ndash;%&gt;--%>
                    <%--</div>--%>

                <%--</div>--%>
            <%--</div>--%>
            <div id="pages_maincontent">

                <h2 class="page_title">Announcements</h2>

                <div class="page_content">
                    <div class="blog-posts">
                        <ul class="posts">
                            <%
                                //request.setCharacterEncoding( "UTF-8" );
                                ArrayList<Announcement> announcements = ( ArrayList<Announcement> ) request.getAttribute( "announcements" );

                                for ( Announcement announcement : announcements )
                                {
                                    out.println("<li>");
                                    out.println("<div class=\"post_entry\">");
                                    out.println("<div class=\"post_date\">");
                                    out.println("<span class=\"day\">" + announcement.getDay() + "</span>");
                                    out.println("<span class=\"month\">" + announcement.getMonth() + "</span>");
                                    out.println("</div>");
                                    out.println("<div class=\"post_title\">");
                                    out.println("<h2><a href=\"single-announcement?id=" + announcement.getId() + "\">" + announcement.getTitle() + "</a></h2>");
                                    out.println("</div>");
                                    out.println("</div>");
                                    out.println("</li>");

                                }
                            %>
                        </ul>

                        <div class="clear"></div>
                        <div id="loadMore"><img src="images/load_posts.png" alt="" title="" /></div>
                        <div id="showLess"><img src="images/load_posts_disabled.png" alt="" title="" /></div>
                    </div>

                </div>

            </div>


        </div>
    </div>
</div>


