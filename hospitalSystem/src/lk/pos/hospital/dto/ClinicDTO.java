package lk.pos.hospital.dto;

public class ClinicDTO extends SuperDTO{
    private String cId;
    private String clinicName;
    private String fieldID;
    private double clinicCharge;

    public ClinicDTO() {
    }

    public ClinicDTO(String cId, String clinicName, String fieldID, double clinicCharge) {
        this.setcId(cId);
        this.setClinicName(clinicName);
        this.setFieldID(fieldID);
        this.setClinicCharge(clinicCharge);
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public double getClinicCharge() {
        return clinicCharge;
    }

    public void setClinicCharge(double clinicCharge) {
        this.clinicCharge = clinicCharge;
    }

    @Override
    public String toString() {
        return "ClinicDTO{" +
                "cId='" + cId + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", fieldID='" + fieldID + '\'' +
                ", clinicCharge=" + clinicCharge +
                '}';
    }
}
