<%@ page import="java.util.ArrayList" %>
<%@ page import="gr.inf.unigo.CourseRegistration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pages">
  <div data-page="projects" class="page no-toolbar no-navbar">
    <div class="page-content">

      <div class="navbarpages">
        <div class="nav_left_logo">
          <div id="nav_top">
            <a href="#" data-panel="left" class="open-panel" id="nav-top">
              <img src="images/icons/white/menu.png" alt="" title="" />
            </a>
          </div>
        </div>
        <div class="nav_right_button">
          <div id="nav_top2">
            <a href="home" id="nav-top2">
              <img src="images/logo.png" alt="" title="" />
            </a>
          </div>

        </div>
      </div>

        <div class="page_content">

            <h2 class="page_title">Timetable</h2>

            <div class="contactform">
                <form>
                    <label>Select day:</label>
                    <select id="day_select" name="" class="form_select active-state">
                        <option value="option one">Monday</option>
                        <option value="option one">Tuesday</option>
                        <option value="option one">Wednesday</option>
                        <option value="option one">Thursday</option>
                        <option value="option one">Friday</option>
                    </select>
                </form>
            </div>

            <h3><%
                CourseRegistration courseRegistration = ( CourseRegistration ) session.getAttribute( "reggedCourses" );

                ArrayList<String> courses = courseRegistration.getRegisteredCourses();
                for ( String course : courses )
                    out.println(course);
            %></h3>
            <h3>Rewards</h3>

            <ul class="responsive_table">
                <li class="table_row">
                    <div class="table_section_small">Time</div>
                    <div class="table_section">Course</div>
                    <div class="table_section">Classroom</div>
                </li>
                <li class="table_row">
                    <div class="table_section_small">1</div>
                    <div class="table_section">elena</div>
                    <div class="table_section">2500 pts</div>
                </li>

            </ul>

        </div>
    </div>
  </div>
</div>

<script>
    function myFunction() {
        var x = document.getElementById("day_select").value;
//        document.getElementById("demo").innerHTML = "You selected: " + x;
    }
</script>
