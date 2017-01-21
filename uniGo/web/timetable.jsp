<%@ page import="java.util.ArrayList" %>
<%@ page import="gr.inf.unigo.Course" %>
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
                <%--<div class="nav_left_logo"><a href="index-old.html"><img src="images/logo.png" alt="" title=""/></a>--%>
                <%--</div>--%>
                <%--<div class="nav_right_button"><a href="menu.html"><img src="images/icons/white/menu.png" alt=""--%>
                                                                       <%--title=""/></a></div>--%>
            <%--</div>--%>
            <div id="pages_maincontent">

                <h2 class="page_title">Timetable</h2>

                <div class="page_content">


                    <div class="buttons-row">
                        <a href="#tab1" class="tab-link button active">Mon</a>
                        <a href="#tab2" class="tab-link button">Tue</a>
                        <a href="#tab3" class="tab-link button">Wed</a>
                        <a href="#tab4" class="tab-link button">Thu</a>
                        <a href="#tab5" class="tab-link button">Fri</a>
                    </div>

                    <div class="tabs-simple">
                        <div class="tabs">
                            <div id="tab1" class="tab active">
                                <ul class="responsive_table">
                                    <li class="table_row">
                                        <div class="table_section_time">Time</div>
                                        <div class="table_section_course">Course</div>
                                        <div class="table_section_classroom">Classroom</div>
                                    </li>
                                    <% ArrayList<Course> schedule = ( ArrayList<Course> ) session.getAttribute( "schedule" );

                                        if ( schedule == null )
                                            return;

                                        for ( Course course : schedule )
                                        {
                                            if ( course.getDayCourse().equals( "Δευτέρα" ) )
                                            {
                                                out.println("<li class=\"table_row\">");
                                                out.println("<div class=\"table_section_time\">" + course.getTimeCourse() + "</div>");
                                                out.println("<div class=\"table_section_course\">" + course.getCourse() + "</div>");
                                                out.println("<div class=\"table_section_classroom\">" + course.getClassroom() + "</div>");
                                                out.println("</li>");
                                            }
                                        }
                                    %>
                                </ul>
                            </div>
                            <div id="tab2" class="tab">
                                <ul class="responsive_table">
                                    <li class="table_row">
                                        <div class="table_section_time">Time</div>
                                        <div class="table_section_course">Course</div>
                                        <div class="table_section_classroom">Classroom</div>
                                    </li>
                                    <%
                                        for ( Course course : schedule )
                                        {
                                            if ( course.getDayCourse().equals( "Τρίτη" ) )
                                            {
                                                out.println("<li class=\"table_row\">");
                                                out.println("<div class=\"table_section_time\">" + course.getTimeCourse() + "</div>");
                                                out.println("<div class=\"table_section_course\">" + course.getCourse() + "</div>");
                                                out.println("<div class=\"table_section_classroom\">" + course.getClassroom() + "</div>");
                                                out.println("</li>");
                                            }
                                        }
                                    %>
                                </ul>
                            </div>
                            <div id="tab3" class="tab">
                                <ul class="responsive_table">
                                    <li class="table_row">
                                        <div class="table_section_time">Time</div>
                                        <div class="table_section_course">Course</div>
                                        <div class="table_section_classroom">Classroom</div>
                                    </li>
                                    <%
                                        for ( Course course : schedule )
                                        {
                                            if ( course.getDayCourse().equals( "Τετάρτη" ) )
                                            {
                                                out.println("<li class=\"table_row\">");
                                                out.println("<div class=\"table_section_time\">" + course.getTimeCourse() + "</div>");
                                                out.println("<div class=\"table_section_course\">" + course.getCourse() + "</div>");
                                                out.println("<div class=\"table_section_classroom\">" + course.getClassroom() + "</div>");
                                                out.println("</li>");
                                            }
                                        }
                                    %>
                                </ul>
                            </div>
                            <div id="tab4" class="tab">
                                <ul class="responsive_table">
                                    <li class="table_row">
                                        <div class="table_section_time">Time</div>
                                        <div class="table_section_course">Course</div>
                                        <div class="table_section_classroom">Classroom</div>
                                    </li>
                                    <%
                                        for ( Course course : schedule )
                                        {
                                            if ( course.getDayCourse().equals( "Πέμπτη" ) )
                                            {
                                                out.println("<li class=\"table_row\">");
                                                out.println("<div class=\"table_section_time\">" + course.getTimeCourse() + "</div>");
                                                out.println("<div class=\"table_section_course\">" + course.getCourse() + "</div>");
                                                out.println("<div class=\"table_section_classroom\">" + course.getClassroom() + "</div>");
                                                out.println("</li>");
                                            }
                                        }
                                    %>
                                </ul>
                            </div>
                            <div id="tab5" class="tab">
                                <ul class="responsive_table">
                                    <li class="table_row">
                                        <div class="table_section_time">Time</div>
                                        <div class="table_section_course">Course</div>
                                        <div class="table_section_classroom">Classroom</div>
                                    </li>
                                    <%
                                        for ( Course course : schedule )
                                        {
                                            if ( course.getDayCourse().equals( "Παρασκευή" ) )
                                            {
                                                out.println("<li class=\"table_row\">");
                                                out.println("<div class=\"table_section_time\">" + course.getTimeCourse() + "</div>");
                                                out.println("<div class=\"table_section_course\">" + course.getCourse() + "</div>");
                                                out.println("<div class=\"table_section_classroom\">" + course.getClassroom() + "</div>");
                                                out.println("</li>");
                                            }
                                        }
                                    %>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>


            </div>


        </div>
    </div>
</div>