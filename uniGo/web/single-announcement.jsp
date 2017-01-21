<%@ page import="gr.inf.unigo.Announcement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Announcement announcement = ( Announcement ) request.getAttribute( "announcement" );
%>

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
                        <a href="announcements" id="nav-top3">
                            <img id="nav-back" src="images/icons/white/back.png" alt="" title="" />
                        </a>
                        <%--<a href="#" data-panel="right" class="open-panel"><img src="images/icons/white/search.png" alt="" title="" /></a>--%>
                    </div>

                </div>
            </div>
            <%--<div class="navbarpages">--%>
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
                            <img src="images/logo.png" alt="" title="" />
                        </a>
                        <%--<a href="#" data-panel="right" class="open-panel"><img src="images/icons/white/search.png" alt="" title="" /></a>--%>
                    </div>

                </div>
            </div>
            <div id="pages_maincontent">


                <div class="post_single">

                    <div class="page_content">
                        <div class="entry">
                            <blockquote><%= announcement.getTitle() %></blockquote>
                            <p>
                                <%= announcement.getDescription() %>   </p>
                            <p>
                                Read more: <a href="<%= announcement.getUrl()%>" class="external" target="_blank"><%= announcement.getUrl() %></a>
                            </p>
                            <ul class="simple_list">
                                <li>Posted: <%= announcement.getPubDate() %></li>
                            </ul>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>