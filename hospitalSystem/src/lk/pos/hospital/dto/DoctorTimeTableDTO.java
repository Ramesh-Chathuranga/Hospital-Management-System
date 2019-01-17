package lk.pos.hospital.dto;

public class DoctorTimeTableDTO extends SuperDTO {
    private String doctorId;
    private String scheduleId;

    public DoctorTimeTableDTO() {
    }

    public DoctorTimeTableDTO(String doctorId, String scheduleId) {
        this.doctorId = doctorId;
        this.scheduleId = scheduleId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "DoctorTimeTableDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                '}';
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
}
