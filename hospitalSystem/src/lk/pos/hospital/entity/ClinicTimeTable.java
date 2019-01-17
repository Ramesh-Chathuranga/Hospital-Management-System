package lk.pos.hospital.entity;

public class ClinicTimeTable extends SuperEntity{
   private String ClinicId;
   private String SheduleId;

    public ClinicTimeTable() {
    }

    public ClinicTimeTable(String clinicId, String sheduleId) {
        ClinicId = clinicId;
        SheduleId = sheduleId;
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
        return "ClinicTimeTableDAO{" +
                "ClinicId='" + ClinicId + '\'' +
                ", SheduleId='" + SheduleId + '\'' +
                '}';
    }
}
