package gr.inf.unigo;

import java.util.ArrayList;

public class Course
{
    private String courseName;
    private String instructor;
    private ArrayList<CourseInfo> courseInfoList;

    public Course( String courseName, String instructor )
    {
        this.courseName = courseName;
        this.instructor = instructor;
        this.courseInfoList = new ArrayList<CourseInfo>();
    }

    public void addCourseInfo( CourseInfo courseInfo )
    {
        courseInfoList.add( courseInfo );
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName( String courseName )
    {
        this.courseName = courseName;
    }

    public String getInstructor()
    {
        return instructor;
    }

    public void setInstructor( String instructor )
    {
        this.instructor = instructor;
    }

    @Override
    public boolean equals( Object obj )
    {
        Course course = ( Course ) obj;

        return this.getCourseName().equals( ( ( Course ) obj ).getCourseName() );
    }
}
