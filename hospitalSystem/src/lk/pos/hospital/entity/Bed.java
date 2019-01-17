package lk.pos.hospital.entity;

public class Bed extends SuperEntity{
   private String besID;
   private String wardID;
   private String patientID;

    public Bed() {
    }

    public Bed(String besID, String wardID) {
        this.besID = besID;
        this.wardID = wardID;
    }

    public Bed(String besID, String wardID, String patientID) {
        this.besID = besID;
        this.wardID = wardID;
        this.patientID = patientID;
    }

    public String getBesID() {
        return besID;
    }

    public void setBesID(String besID) {
        this.besID = besID;
    }

    public String getWardID() {
        return wardID;
    }

    public void setWardID(String wardID) {
        this.wardID = wardID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "besID='" + besID + '\'' +
                ", wardID='" + wardID + '\'' +
                ", patientID='" + patientID + '\'' +
                '}';
    }
}
