package entity;

public class Enrol {
    private int stu_id;
    private  int course_id;
    private  int score;
    private  String accept;

    @Override
    public String toString() {
        return "Enrol{" +
                "stu_id=" + stu_id +
                ", class_id=" + course_id +
                ", score=" + score +
                ", accept='" + accept + '\'' +
                '}';
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getClass_id() {
        return course_id;
    }

    public void setClass_id(int class_id) {
        this.course_id = class_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
