package lk.pos.hospital.dto;

public class BedDTO extends SuperDTO {
    private String bedID;
    private String wardID;
    private String patientID;

    public BedDTO() {
    }

    public BedDTO(String besID, String wardID) {
        this.bedID = besID;
        this.wardID = wardID;
    }

    public BedDTO(String besID, String wardID, String patientID) {
        this.bedID = besID;
        this.wardID = wardID;
        this.patientID = patientID;
    }

    public String getBedID() {
        return bedID;
    }

    public void setBedID(String bedID) {
        this.bedID = bedID;
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
        return "BedDTO{" +
                "bedID='" + bedID + '\'' +
                ", wardID='" + wardID + '\'' +
                ", patientID='" + patientID + '\'' +
                '}';
    }
}
