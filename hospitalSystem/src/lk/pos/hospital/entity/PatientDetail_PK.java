package lk.pos.hospital.entity;

public class PatientDetail_PK {
   private String patientId;
   private String ClinicID;

    public PatientDetail_PK() {
    }

    public PatientDetail_PK(String patientId, String clinicID) {
        this.patientId = patientId;
        ClinicID = clinicID;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClinicID() {
        return ClinicID;
    }

    public void setClinicID(String clinicID) {
        ClinicID = clinicID;
    }

    @Override
    public String toString() {
        return "PatientDetail_PK{" +
                "patientId='" + patientId + '\'' +
                ", ClinicID='" + ClinicID + '\'' +
                '}';
    }
}
