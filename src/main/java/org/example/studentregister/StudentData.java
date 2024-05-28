package org.example.studentregister;

import java.util.Date;

public class StudentData {

    private Integer studentNum;
    private String Year;
    private String Course;
    private String FirstName;
    private String LastName;
    private String Gender;
    private Date birth;

    private Integer Seventh;
    private  Integer Twelfth;
    private Integer FinalGrade;




    public StudentData(int studentNum, String Year, String Course, String FirstName, String LastName, String Gender, Date birth)  {
        this.studentNum = studentNum;
        this.Year = Year;
        this.Course = Course;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.birth = birth;
        this.Gender = Gender;

    }
    public StudentData(int studentNum, String Year,String Course, Integer Seventh,Integer Twelfth,Integer FinalGrade)
    {
        this.studentNum = studentNum;
        this.Year = Year;
        this.Course = Course;
        this.Seventh=Seventh;
        this.Twelfth=Twelfth;
        this.FinalGrade=FinalGrade;


    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public String getYear() {
        return Year;
    }

    public String getCourse() {
        return Course;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public Date getBirth() {
        return birth;
    }


    public Integer getSeventh (){
        return Seventh;
    }

    public Integer getTwelfth() {
        return Twelfth;
    }

    public Integer getFinals() {


        return FinalGrade;
    }

}
