package lk.pos.hospital.dto;

public class ClinicTimeTableDTO extends SuperDTO {
    private String ClinicId;
    private String SheduleId;

    public ClinicTimeTableDTO(String clinicId, String sheduleId) {
        ClinicId = clinicId;
        SheduleId = sheduleId;
    }

    public ClinicTimeTableDTO() {
    }

    public String getClinicId() {
        return ClinicId;
    }

    public void setClinicId(String clinicId) {
        ClinicId = clinicId;
    }

    public String getSheduleId() {
        return SheduleId;
    }

    public void setSheduleId(String sheduleId) {
        SheduleId = sheduleId;
    }

    @Override
    public String toString() {
        return "ClinicTimeTableDTO{" +
                "ClinicId='" + ClinicId + '\'' +
                ", SheduleId='" + SheduleId + '\'' +
                '}';
    }
}
