package lk.ijse.institute.to;

public class Batch {
    private String batchId;
    private String batchName;
    private String courseId;
    private String courseName;
    private String std_id;
    private String std_name;

    public Batch(String batchId, String batchName, String courseId, String courseName, String std_id, String std_name) {
        this.setBatchId(batchId);
        this.setBatchName(batchName);
        this.setCourseId(courseId);
        this.setCourseName(courseName);
        this.setStd_id(std_id);
        this.setStd_name(std_name);
    }

    public Batch(String b_id) {
        this.batchId=b_id;
    }


    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId='" + batchId + '\'' +
                ", batchName='" + batchName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                '}';
    }
}
