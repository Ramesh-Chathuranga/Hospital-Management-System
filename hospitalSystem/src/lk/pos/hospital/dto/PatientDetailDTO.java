package lk.pos.hospital.dto;

public class PatientDetailDTO extends SuperDTO {
    private String patientId;
    private String clinicId;
    private String note;

    public PatientDetailDTO() {
    }

    public PatientDetailDTO(String patientId, String clinicId, String note) {
        this.setPatientId(patientId);
        this.setClinicId(clinicId);
        this.setNote(note);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PatientDetailDTO{" +
                "patientId='" + patientId + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
