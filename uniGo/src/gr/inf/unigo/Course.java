package gr.inf.unigo;

public class Course
{
    private String course;
    private String timeCourse;
    private String classroom;
    private String dayCourse;

    public Course( String course, String dayCourse, String timeCourse, String classroom )
    {
        this.course = course;
        this.dayCourse = dayCourse;
        this.timeCourse = timeCourse;
        this.classroom = classroom;
    }

    public String getDayCourse()
    {
        return dayCourse;
    }

    public void setDayCourse( String dayCourse )
    {
        this.dayCourse = dayCourse;
    }

    public String getCourse()
    {
        return course;
    }

    public void setCourse( String course )
    {
        this.course = course;
    }

    public String getTimeCourse()
    {
        return timeCourse;
    }

    public void setTimeCourse( String timeCourse )
    {
        this.timeCourse = timeCourse;
    }

    public String getClassroom()
    {
        return classroom;
    }

    public void setClassroom( String classroom )
    {
        this.classroom = classroom;
    }
}
