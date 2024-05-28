package org.example.studentregister;

public class coursedata {

    private String course;
    private String description;

    public coursedata(String course,String description)
    {
        this.course=course;
        this.description=description;

    }

    public String getCourse() {
        return course;
    }
    public String getDescription(){
        return description;
    }

}
