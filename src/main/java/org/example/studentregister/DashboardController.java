package org.example.studentregister;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


import java.security.spec.ECField;
import java.sql.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane CourseForm;

    @FXML
    private AnchorPane GradesForm;

    @FXML
    private AnchorPane StdForm;

    @FXML
    private AnchorPane anch1;

    @FXML
    private AnchorPane anch2;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private AnchorPane bg45;

    @FXML
    private Button course_addbtn;

    @FXML
    private Button course_clearbtn;

    @FXML
    private TableColumn<coursedata, String> course_coursetable;

    @FXML
    private Button course_delbtn;

    @FXML
    private TableColumn<coursedata, String> course_desctable;

    @FXML
    private TextField course_desctxtfield;

    @FXML
    private TableView<coursedata> course_tableview;

    @FXML
    private TextField course_txtfield;

    @FXML
    private Button course_updatebtn;

    @FXML
    private Button coursebtn;

    @FXML
    private TextField grade_12th;

    @FXML
    private TableColumn<StudentData, Integer> grade_12thtable;

    @FXML
    private TextField grade_7th;

    @FXML
    private TableColumn<StudentData, Integer> grade_7thtable;

    @FXML
    private TextField grade_course;

    @FXML
    private TableColumn<StudentData, String> grade_coursetable;

    @FXML
    private TextField grade_final;

    @FXML
    private TableColumn<StudentData, Integer> grade_finaltable;

    @FXML
    private TextField grade_id;

    @FXML
    private TableColumn<StudentData, Integer> grade_studentidtable;

    @FXML
    private TableView<StudentData> grade_tableview;

    @FXML
    private TextField grade_year;

    @FXML
    private TableColumn<StudentData , String> grade_yeartable;

    @FXML
    private Button gradebtn_clear;

    @FXML
    private Button gradebtn_update;

    @FXML
    private TextField gradesearch;

    @FXML
    private Button grdbtn;

    @FXML
    private AnchorPane leftanch;

    @FXML
    private Button signout;

    @FXML
    private Button stdform_addbtn;

    @FXML
    private TableColumn<StudentData, Date> stdform_bddatetable;

    @FXML
    private DatePicker stdform_birthdatechoose;

    @FXML
    private Button stdform_clearbtn;

    @FXML
    private ComboBox<?> stdform_coursechoose;

    @FXML
    private TableColumn<StudentData, String> stdform_coursetable;

    @FXML
    private Button stdform_deletebtn;

    @FXML
    private TableColumn<StudentData, String> stdform_firsttable;

    @FXML
    private TextField stdform_fname;

    @FXML
    private ComboBox<?> stdform_genderchoose;

    @FXML
    private TableColumn<StudentData, String> stdform_gendertable;

    @FXML
    private Button stdform_insrtbtn;

    @FXML
    private TableColumn<StudentData, String> stdform_lasttable;

    @FXML
    private TextField stdform_lname;

    @FXML
    private TextField stdform_search;

    @FXML
    private TextField stdform_stdid;

    @FXML
    private TableColumn<StudentData, Integer> stdform_stdidtable;

    @FXML
    private TableView<StudentData> stdform_tableview;

    @FXML
    private Button stdform_updatebtn;

    @FXML
    private ComboBox<?> stdform_yearchoose;

    @FXML
    private TableColumn<StudentData, String> stdform_yeartable;

    @FXML
    private Label totalcourses;

    @FXML
    private Label totalstudents;

    @FXML
    private Label userrr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addshowstudentdata();
        addstdYearlist();
        addstdgender();
        addcourselist();
        addStudentsAdd();
        studentGradesShowListData();
        addstudentcourselist();
        displaytotal();
        displaycors();
    }

    @Override
    public String toString() {

        return "Please Fill in All Blank fields";
    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
private String[] yearList={"First Year","Second Year","Third Year","Fourth Year"};

public void addstdYearlist(){

    List<String> yearL= new ArrayList<>();

    for(String data: yearList){
        yearL.add(data);
    }

    ObservableList ObList = FXCollections.observableArrayList(yearL);
    stdform_yearchoose.setItems(ObList);



}

public void availcourseadd()
{

    String insertData = "INSERT INTO course (course,description) VALUES(?,?)";

    connect = database.connectDb();
    try {
        Alert alert;
        if (course_txtfield.getText().isEmpty() || course_desctxtfield.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fill All");
            alert.showAndWait();

        } else {
            String checkData = "SELECT course FROM course WHERE course ='" + course_txtfield.getText() + "'";
            statement = connect.createStatement();
            result = statement.executeQuery(checkData);
        }
        if (result.next()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erk");
            alert.setHeaderText(null);
            alert.setContentText("Course" + course_txtfield.getText() + "already exists");
            alert.showAndWait();
        } else {

            prepare = connect.prepareStatement(insertData);
            prepare.setString(1, course_txtfield.getText());
            prepare.setString(2, course_desctxtfield.getText());
            prepare.executeUpdate();


            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();

            availcorsselect();
        }






    }catch(Exception e){


    }

}

   public void addstudentcourselist(){

    String listCourse ="SELECT * FROM course";
    connect=database.connectDb();

    try{

        ObservableList listC= FXCollections.observableArrayList();

        prepare= connect.prepareStatement(listCourse);
        result = prepare.executeQuery();

        while(result.next()) {
            listC.add(result.getString("course"));

        }
        stdform_coursechoose.setItems(listC);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


   }
    public void availableCourseDelete() {

        String deleteData = "DELETE FROM course WHERE course = '"
                + course_txtfield.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (course_txtfield.getText().isEmpty()
                    || course_desctxtfield.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(toString());
                alert.showAndWait();
            } else {
//                ALL GOOD GUYS! NOW LETS PROCEED TO ADD STUDENTS FORM
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Course: " + course_txtfield.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                    availcorsselect();
                    // TO CLEAR THE TEXT FIELDS
                    availablecourseClear();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void availableCourseUpdate() {

        String updateData = "UPDATE course SET description = '"
                + course_desctxtfield.getText() + "' WHERE course = '"
                +course_txtfield.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (course_txtfield.getText().isEmpty()
                    || course_desctxtfield.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(toString());
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Course: " + course_txtfield.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                    availcorsselect();
                    // TO CLEAR THE TEXT FIELDS
                    availablecourseClear();

                } else {
                    return;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
public void availablecourseClear() {

    course_txtfield.setText("");

    course_desctxtfield.setText("");


}

public void availcorsselect(){

    coursedata courseD= course_tableview.getSelectionModel().getSelectedItem();
    int num = course_tableview.getSelectionModel().getSelectedIndex();

    if((num-1)<-1){
        return;
    }

    course_txtfield.setText((courseD.getCourse()));
    course_desctxtfield.setText((courseD.getDescription()));


}
private String[] genderList ={"Male","Female"};
public void addstdgender(){
    List<String> genderL= new ArrayList<>();

    for(String data: genderList){
        genderL.add(data);
    }

    ObservableList ObList = FXCollections.observableArrayList(genderL);
    stdform_genderchoose.setItems(ObList);



}









    private double x=0;
    private double y=0;
    public void logout() {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");


            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                signout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void close() {
        System.exit(0);
    }

    public void addStudentsAdd() {

        String insertData = "INSERT INTO student "
                + "(studentNum,FirstName,LastName,Course,Year,Gender,birth) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (stdform_stdid.getText().isEmpty()
                    || stdform_yearchoose.getSelectionModel().getSelectedItem() == null
                    || stdform_fname.getText().isEmpty()
                    || stdform_lname.getText().isEmpty()
                    || stdform_coursechoose.getSelectionModel().getSelectedItem() == null
                    || stdform_genderchoose.getSelectionModel().getSelectedItem() == null
                    || stdform_birthdatechoose.getValue() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(toString());
                alert.showAndWait();
            } else {

                String checkData = "SELECT studentNum FROM student WHERE studentNum = '"
                        + stdform_stdid.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student #" + stdform_stdid.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, stdform_stdid.getText());
                    prepare.setString(4, (String) stdform_coursechoose.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) stdform_yearchoose.getSelectionModel().getSelectedItem());
                    prepare.setString(2, stdform_fname.getText());
                    prepare.setString(3, stdform_lname.getText());
                    prepare.setString(6, (String) stdform_genderchoose.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(stdform_birthdatechoose.getValue()));






                    prepare.executeUpdate();
                        String insertgrade="INSERT INTO student_grade"+"(studentNum,Year,Course,Seventh,Twelfth,FinalGrade)"
                                +"VALUES(?,?,?,?,?,?)";
                        prepare = connect.prepareStatement(insertgrade);
                    prepare.setString(1, stdform_stdid.getText());
                    prepare.setString(2, (String) stdform_yearchoose.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) stdform_coursechoose.getSelectionModel().getSelectedItem());
                    prepare.setInt(4,0);
                    prepare.setInt(5,0);
                    prepare.setInt(6,0);

                    prepare.executeUpdate();




                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addshowstudentdata();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displaytotal() {

        String sql = "SELECT COUNT(id) FROM student";
        connect = database.connectDb();

        int count=0;
        try{
            prepare=connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()) {
            count=result.getInt("COUNT(id)");
            }

            totalstudents.setText(String.valueOf(count));

        } catch(Exception e){
            e.printStackTrace();

        }
    }

    public void displaycors(){
        String sql = "SELECT COUNT(course) FROM student";
        connect = database.connectDb();

        int count=0;
        try{
            prepare=connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()) {
                count=result.getInt("COUNT(course)");
            }

            totalcourses.setText(String.valueOf(count));

        }catch(Exception e){
            e.printStackTrace();

        }
    }




    public ObservableList<StudentData> studentGradesListData() {

        ObservableList<StudentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_grade";

        connect = database.connectDb();

        try {
            StudentData studentD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new StudentData(result.getInt("studentNum"),
                        result.getString("Year"),
                        result.getString("Course"),
                        result.getInt("Seventh"),
                        result.getInt("Twelfth"),
                        result.getInt("FinalGrade"));

                listData.add(studentD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    public ObservableList<StudentData> studentGradesList(){

        ObservableList<StudentData> listData = FXCollections.observableArrayList();
        String sql="SELECT * FROM student_grade";
        connect = database.connectDb();

        try{
            StudentData studentD;



            prepare = connect.prepareStatement(sql);
            result= prepare.executeQuery();
            while(result.next()){

                studentD= new StudentData(result.getInt("studentNum"),
                        result.getString("Year"),
                        result.getString("Course"),result.getInt("Seventh"),
                        result.getInt("Twelfth"),result.getInt("FinalGrade"));

listData.add(studentD);
            }


        }catch(Exception e){}
        return listData;
    }

    private ObservableList<StudentData> studentGradesL;

    public void studentGradesShowListData() {
        studentGradesL = studentGradesListData();

        grade_studentidtable.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        grade_yeartable.setCellValueFactory(new PropertyValueFactory<>("Year"));
        grade_coursetable.setCellValueFactory(new PropertyValueFactory<>("Course"));
        grade_7thtable.setCellValueFactory(new PropertyValueFactory<>("Seventh"));
        grade_12thtable.setCellValueFactory(new PropertyValueFactory<>("Twelfth"));
        grade_finaltable.setCellValueFactory(new PropertyValueFactory<>("FinalGrade"));
//
        grade_tableview.setItems(studentGradesL);

    }

    public void studentGradesUpdate() {
        double finalCheck1 = 0;
        double finalCheck2 = 0;

        String checkData = "SELECT * FROM student_grade WHERE studentNum = '"
                + grade_id.getText() + "'";

        connect = database.connectDb();

        double finalResult = 0;

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            if (result.next()) {
                finalCheck1 = result.getDouble("Seventh");
                finalCheck2 = result.getDouble("Twelfth");
            }

            if (finalCheck1 == 0 || finalCheck2 == 0) {
                finalResult = 0;
            } else { //LIKE (X+Y)/2 AVE WE NEED TO FIND FOR FINALS
                finalResult = (Integer.parseInt(grade_7th.getText())
                        + Integer.parseInt(grade_12th.getText()) / 2);
            }

            String updateData = "UPDATE student_grade SET "
                    + " Year = '" + grade_year.getText()
                    + "', Course = '" + grade_course.getText()
                    + "', Seventh = '" + grade_7th.getText()
                    + "', Twelfth = '" + grade_12th.getText()
                    + "', FinalGrade = '" + finalResult + "' WHERE studentNum = '"
                    + grade_id.getText() + "'";

            Alert alert;

            if (grade_id.getText().isEmpty()
                    || grade_7th.getText().isEmpty()
                    || grade_12th.getText().isEmpty()
                    || grade_final.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + grade_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    studentGradesShowListData();
                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsSearch() {

        FilteredList<StudentData> filter = new FilteredList<>(addstdntlist, e -> true);

        stdform_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;

                } else {
                    return false;
                }
            });
        });

        SortedList<StudentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(stdform_tableview.comparatorProperty());
        stdform_tableview.setItems(sortList);

    }

        public void addStudentsDelete() {

            String deleteData = "DELETE FROM student WHERE studentNum = '"
                    + stdform_stdid.getText() + "'";
            String deleteData2 = "DELETE FROM student_grade WHERE studentNum = '"
                    + stdform_stdid.getText() + "'";

            connect = database.connectDb();

            try {
                Alert alert;

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to DELETE Student #" + stdform_stdid.getText() + "?");

                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        statement = connect.createStatement();
                        statement.executeUpdate(deleteData);
                        statement.executeUpdate(deleteData2);
                    }
                }
             catch(Exception e){

            }
        }




    public void addStudentsUpdate() {



        String updateData = "UPDATE student SET "
                + "Year = '" + stdform_yearchoose.getSelectionModel().getSelectedItem()
                + "', Course = '" + stdform_coursechoose.getSelectionModel().getSelectedItem()
                + "', FirstName = '" + stdform_fname.getText()
                + "', LastName = '" + stdform_lname.getText()
                + "', Gender = '" + stdform_genderchoose.getSelectionModel().getSelectedItem()
                + "', birth = '" + stdform_birthdatechoose.getValue()
                + "' WHERE studentNum = '"
                + stdform_stdid.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + stdform_stdid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();


                    addshowstudentdata();

                    addStudentsClear();

                } else {
                    return;
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        studentGradesShowListData();
    }
    public void  addStudentsClear()
    {



        stdform_stdid.setText("");
        stdform_yearchoose.getSelectionModel().clearSelection();
        stdform_genderchoose.getSelectionModel().clearSelection();
        stdform_fname.setText("");
        stdform_lname.setText("");
        stdform_coursechoose.getSelectionModel().clearSelection();
        stdform_birthdatechoose.setValue(null);



    }



    public void stdformselect(){

        StudentData studentD = stdform_tableview.getSelectionModel().getSelectedItem();
        int num = stdform_tableview.getSelectionModel().getSelectedIndex();
        if((num-1)<-1) {return;};

        stdform_stdid.setText(String.valueOf(studentD.getStudentNum()));
        stdform_fname.setText(studentD.getFirstName());
        stdform_lname.setText(studentD.getLastName());





    }

    public ObservableList<coursedata> availableCourseListData(){

        ObservableList<coursedata> listdata= FXCollections.observableArrayList();
        String sql = "SELECT * FROM course";
        connect= database.connectDb();
        try{
            coursedata courseD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                courseD= new coursedata(result.getString("course") ,
                        result.getString("description"));

                listdata.add(courseD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listdata;
    }

    private ObservableList<coursedata> availablecourselist;
    public void addcourselist(){
        availablecourselist=availableCourseListData();

        course_coursetable.setCellValueFactory(new PropertyValueFactory<>("course"));
        course_desctable.setCellValueFactory(new PropertyValueFactory<>("description"));
        course_tableview.setItems(availablecourselist);



    }
    private ObservableList<StudentData> addstdntlist;
    public void addshowstudentdata(){
        addstdntlist=addstdntData();
        stdform_stdidtable.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        stdform_yeartable.setCellValueFactory(new PropertyValueFactory<>("Year"));
        stdform_coursetable.setCellValueFactory(new PropertyValueFactory<>("Course"));
        stdform_firsttable.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        stdform_lasttable.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        stdform_gendertable.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        stdform_bddatetable.setCellValueFactory(new PropertyValueFactory<>("birth"));
        stdform_tableview.setItems(addstdntlist);
    }
public ObservableList<StudentData> addstdntData(){
        ObservableList<StudentData> listStudents = FXCollections.observableArrayList();
        String sql= "SELECT * FROM student";
        connect = database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
    StudentData studentD;
            while(result.next()){
                studentD=new StudentData(result.getInt("studentNum" ),
                        result.getString("Year"),
                        result.getString("Course"),
                        result.getString("FirstName"),result.getString("LastName"),
                        result.getString("Gender"),result.getDate("birth"));
                listStudents.add(studentD);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return listStudents;
}
    public void switchform(ActionEvent event){
        if(event.getSource()==b2){
            StdForm.setVisible(true);
            CourseForm.setVisible(false);
            GradesForm.setVisible(false);
            addshowstudentdata();
            addstdYearlist();
            addstdgender();
            addstudentcourselist();
            displaytotal();
            displaycors();

        }
        else if(event.getSource()==grdbtn){
            StdForm.setVisible(false);
            CourseForm.setVisible(false);
            GradesForm.setVisible(true);
            studentGradesShowListData();
            addstdYearlist();
            addstdgender();
            displaytotal();
            displaycors();

        }
        else if(event.getSource()==coursebtn){
            StdForm.setVisible(false);
            CourseForm.setVisible(true);
            GradesForm.setVisible(false);
            addcourselist();
            displaytotal();
            displaycors();
        }
    }
}

