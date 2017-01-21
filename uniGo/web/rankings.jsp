<%@ page import="gr.inf.unigo.RankingEntry" %>
<%@ page import="gr.inf.unigo.Student" %>
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
                <%--<div class="nav_left_logo">--%>
                    <%--<div id="nav_top">--%>
                        <%--<a href="#" data-panel="left" class="open-panel" id="nav-top">--%>
                            <%--<img src="images/icons/white/menu.png" alt="" title=""/>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="nav_right_button">--%>
                    <%--<div id="nav_top2">--%>
                        <%--<a href="home" id="nav-top2">--%>
                            <%--<img src="images/logo.png" alt="" title=""/>--%>
                        <%--</a>--%>
                    <%--</div>--%>

                <%--</div>--%>
            <%--</div>--%>

            <div id="pages_maincontent">

                <h2 class="page_title">Student Rankings</h2>

                <div class="page_content">

                    <h3>Top 5</h3>

                    <ul class="responsive_table">
                        <li class="table_row">
                            <div class="table_section_small">#</div>
                            <div class="table_section">Student</div>
                            <div class="table_section">XP</div>
                        </li>

                        <%
                            int rank = 1;
                            ArrayList<RankingEntry> rankings = ( ArrayList<RankingEntry> ) session.getAttribute( "rankings" );

                            for ( RankingEntry ranking : rankings )
                            {
                                if ( rank == 6 )
                                    rank = ranking.getRank();


                                if ( ranking.getUserName().equals( ( ( Student ) session.getAttribute( "user" ) ).getUserName() ) )
                                    out.println( "<li class=\"table_row\" id=\"selected\">" );
                                else
                                    out.println( "<li class=\"table_row\">" );

                                out.println( "<div class=\"table_section_small\">" + rank + "</div>" );
                                out.println( "<div class=\"table_section\">" + ranking.getUserName() + "</div>" );
                                out.println( "<div class=\"table_section\">" + ranking.getXp() + " pts</div>" );
                                out.println( "</li>" );

                                rank++;
                            }
                        %>
                    </ul>

                    <p>Rankings are updated daily</p>

                    <h3>Rewards</h3>

                    <ul class="responsive_table">
                        <li class="table_row">
                            <div class="table_section_13">Rankings</div>
                            <div class="table_section_14">Reward</div>


                        </li>
                        <li class="table_row">
                            <div class="table_section_13">#1</div>
                            <div class="table_section_14">2x Cinema tickets</div>


                        </li>
                        <li class="table_row">
                            <div class="table_section_13">#2-3</div>
                            <div class="table_section_14">20% discount at Stories to Eat</div>


                        </li>
                        <li class="table_row">
                            <div class="table_section_13">#4-5</div>
                            <div class="table_section_14">10% discount on stationery products</div>


                        </li>


                    </ul>
                    <p>Rewards will be offered at the end of every month</p>


                </div>

            </div>


        </div>
    </div>
</div>
