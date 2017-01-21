package gr.inf.unigo;

public class Student
{
    private int studentID;
    private String firstName;
    private String lastName;
    private String userName;
    private String gender;
    private String facebookID;
    private String password;

    public Student( String userName, String facebookID, int studentID, String firstName, String lastName, String gender, String password )
    {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.facebookID = facebookID;
        this.password = password;
    }

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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getFacebookID()
    {
        return facebookID;
    }

    public void setFacebookID( String facebookID )
    {
        this.facebookID = facebookID;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender( String gender )
    {
        this.gender = gender;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }
}
