package lk.pos.hospital.business;

import lk.pos.hospital.business.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory ;

    public enum BOType{
        WARD,BED,SCHEDULE,CLINIC,SPECIAL_FIELD,PATIENT,HOSPITAL_ADMIT,DOCTOR
    }

    public static BOFactory getInstance() {
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }


    public <T extends SuperBO>T getBo(BOType type){
        switch (type){
            case WARD:
                return (T) new WardBOImpl();
            case BED:
                return (T) new BedBOImpl();
            case SCHEDULE:
                return (T) new ScheduleBOImpl();
            case CLINIC:
                return (T) new ClinicBOImpl();
            case SPECIAL_FIELD:
                return (T) new SpecialFieldBOImpl();
            case PATIENT:
                return (T) new PatientBOImpl();
            case HOSPITAL_ADMIT:
                return (T) new PatientAdmitBOImpl();
            case DOCTOR:
                return (T) new DoctorBOImpl();
            default:return null;
        }
    }

    private BOFactory() {
    }
}
