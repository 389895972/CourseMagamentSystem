package entity;

public class Class {
    private  int id;
    private  String room;
    private  String time;
    private  String tea_id;
    private  String cou_id;

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", time='" + time + '\'' +
                ", tea_id='" + tea_id + '\'' +
                ", cou_id='" + cou_id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTea_id() {
        return tea_id;
    }

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }

    public String getCou_id() {
        return cou_id;
    }

    public void setCou_id(String cou_id) {
        this.cou_id = cou_id;
    }
}
