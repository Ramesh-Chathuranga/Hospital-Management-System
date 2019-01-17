package lk.pos.hospital.entity;

public class DoctorTimeTable extends SuperEntity {
    private String doctorId;
    private String scheduleId;

    public DoctorTimeTable() {
    }

    public DoctorTimeTable(String doctorId, String scheduleId) {
        this.doctorId = doctorId;
        this.scheduleId = scheduleId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return "DoctorTimeTable{" +
                "doctorId='" + doctorId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                '}';
    }
}
