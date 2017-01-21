package gr.inf.unigo;

import java.util.ArrayList;

public class CourseRegistration
{
    private String userName;
    private ArrayList<String> registeredCourses;

    public CourseRegistration( String userName )
    {
        this.userName = userName;
        this.registeredCourses = new ArrayList<String>();
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public ArrayList<String> getRegisteredCourses()
    {
        return registeredCourses;
    }

    public void setRegisteredCourses( ArrayList<String> registeredCourses )
    {
        this.registeredCourses = registeredCourses;
    }

    public void addCourseByID( String courseID )
    {
        this.registeredCourses.add( courseID );
    }
}
