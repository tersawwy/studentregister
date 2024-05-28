package org.example.studentregister;

public class studentgrades {

private Integer Seventh;
private Integer Twelfth;
private Integer Finalgrade;

public studentgrades(Integer studentNum, String Year,String Course, Integer Seventh,Integer Twelfth,Integer Finalgrade)
{

    this.Seventh=Seventh;
    this.Twelfth=Twelfth;
    this.Finalgrade=Finalgrade;



}


    public Integer getFinalgrade() {
        return Finalgrade;
    }

    public Integer getSeventh() {
        return Seventh;
    }

    public Integer getTwelfth() {
        return Twelfth;
    }
}
