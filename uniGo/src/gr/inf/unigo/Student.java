package gr.inf.unigo;

public class Student
{
    private int studentID;
    private String firstName;
    private String lastName;

    public int getStudentID()
    {
        return studentID;
    }

    public void setStudentID( int studentID )
    {
        this.studentID = studentID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "Student ID: " + studentID + " " + lastName;
    }
}
