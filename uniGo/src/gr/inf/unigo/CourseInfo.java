package gr.inf.unigo;

import java.util.Date;

public class CourseInfo
{
    private String day;
    private String classroom;
    private Date startTime;
    private Date endTime;

    public CourseInfo(String day, String classroom, Date startTime, Date endTime)
    {
        this.day = day;
        this.classroom = classroom;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay()
    {
        return day;
    }

    public void setDay( String day )
    {
        this.day = day;
    }

    public String getClassroom()
    {
        return classroom;
    }

    public void setClassroom( String classroom )
    {
        this.classroom = classroom;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime( Date startTime )
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime( Date endTime )
    {
        this.endTime = endTime;
    }
}
