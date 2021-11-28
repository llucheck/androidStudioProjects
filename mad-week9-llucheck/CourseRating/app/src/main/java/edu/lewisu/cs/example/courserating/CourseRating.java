package edu.lewisu.cs.example.courserating;


public class CourseRating {
    private int id;
    private String courseName;
    private String instructorName;
    private String courseType;
    private int rating;

    CourseRating() {
        courseName = "";
        instructorName="";
        courseType="";
        rating=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getCourseName() {
        return courseName;
    }

    void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    String getInstructorName() {
        return instructorName;
    }

    void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    String getCourseType() {
        return courseType;
    }

    void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    int getRating() {
        return rating;
    }

    void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return  "courseName=" + courseName + "\n" +
                "instructorName=" + instructorName + "\n" +
                "courseType=" + courseType + "\n" +
                "rating=" + rating ;
    }
}
