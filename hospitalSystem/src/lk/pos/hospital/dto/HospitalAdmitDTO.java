package lk.pos.hospital.dto;

import java.time.LocalDate;

public class HospitalAdmitDTO extends SuperDTO {
    private String patientId;
    private String wardId;
    private LocalDate date;
    private LocalDate dischargedate;

    public HospitalAdmitDTO() {
    }

    public HospitalAdmitDTO(String patientId, String wardId, LocalDate date) {
        this.patientId = patientId;
        this.wardId = wardId;
        this.date = date;
    }

    public HospitalAdmitDTO(String patientId, String wardId, LocalDate date, LocalDate dischargedate) {
        this.setPatientId(patientId);
        this.setWardId(wardId);
        this.setDate(date);
        this.setDischargedate(dischargedate);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDischargedate() {
        return dischargedate;
    }

    public void setDischargedate(LocalDate dischargedate) {
        this.dischargedate = dischargedate;
    }

    @Override
    public String toString() {
        return "HospitalAdmitDTO{" +
                "patientId='" + patientId + '\'' +
                ", wardId='" + wardId + '\'' +
                ", date=" + date +
                ", dischargedate=" + dischargedate +
                '}';
    }
}
