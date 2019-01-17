package lk.pos.hospital.entity;

public class PatientDetail extends SuperEntity {
    private PatientDetail_PK patientDetail_pk;
    private String note;

    public PatientDetail() {
    }

    public PatientDetail(PatientDetail_PK patientDetail_pk, String note) {
        this.patientDetail_pk = patientDetail_pk;
        this.note = note;
    }

    public PatientDetail_PK getPatientDetail_pk() {
        return patientDetail_pk;
    }

    public void setPatientDetail_pk(PatientDetail_PK patientDetail_pk) {
        this.patientDetail_pk = patientDetail_pk;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PatientDetail{" +
                "patientDetail_pk=" + patientDetail_pk +
                ", note='" + note + '\'' +
                '}';
    }
}
