package lk.pos.hospital.entity;

public class PatientApointment extends SuperEntity {
   private int appointmentId;
   private PatientApointment_FK patientApointment_fk;

    public PatientApointment() {
    }

    public PatientApointment(int appointmentId, PatientApointment_FK patientApointment_fk) {
        this.appointmentId = appointmentId;
        this.patientApointment_fk = patientApointment_fk;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public PatientApointment_FK getPatientApointment_fk() {
        return patientApointment_fk;
    }

    public void setPatientApointment_fk(PatientApointment_FK patientApointment_fk) {
        this.patientApointment_fk = patientApointment_fk;
    }

    @Override
    public String toString() {
        return "PatientApointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientApointment_fk=" + patientApointment_fk +
                '}';
    }
}
