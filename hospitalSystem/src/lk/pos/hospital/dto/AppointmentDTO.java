package lk.pos.hospital.dto;

public class AppointmentDTO extends SuperDTO{
    private int apid;
    private String doctorId;
    private String patientId;
    private String sceduleId;

    public AppointmentDTO() {
    }

    public AppointmentDTO(int apid, String doctorId, String patientId, String sceduleId) {
        this.apid = apid;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.sceduleId = sceduleId;
    }

    public int getApid() {
        return apid;
    }

    public void setApid(int apid) {
        this.apid = apid;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSceduleId() {
        return sceduleId;
    }

    public void setSceduleId(String sceduleId) {
        this.sceduleId = sceduleId;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "apid='" + apid + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", sceduleId='" + sceduleId + '\'' +
                '}';
    }
}
