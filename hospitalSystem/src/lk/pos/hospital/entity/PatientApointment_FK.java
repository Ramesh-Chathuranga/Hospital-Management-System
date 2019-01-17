package lk.pos.hospital.entity;

public class PatientApointment_FK {
    private String doctorId;
    private String patientId;
    private String sceduleId;

    public PatientApointment_FK() {
    }

    public PatientApointment_FK(String doctorId, String patientId, String sceduleId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.sceduleId = sceduleId;
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
        return "PatientApointment_FK{" +
                "doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", sceduleId='" + sceduleId + '\'' +
                '}';
    }
}
