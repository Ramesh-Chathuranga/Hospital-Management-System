package lk.pos.hospital.dao;

import lk.pos.hospital.dao.custom.DoctorDAO;
import lk.pos.hospital.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTYPE {
        BED,CLINIC,CLINIC_TIME_TABLE,
        DOCTOR, DOCTOR_TIME_TABLE,HOSPITAL_ADMIT,
        PATIENT,PATIENT_APOINTMENT,PATENT_DETAIL,
        SCHEDULE,SPECIFIC_FIELD,WARD,QUERYDAO,
    }

    public static DAOFactory getInstance() {
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public <T extends  SuperDAO>T getDAO(DAOTYPE daotype){
        switch (daotype){
            case BED:
                return (T) new BedDAOImpl();
            case WARD:
                return (T) new WardDAOImpl();
            case CLINIC:
                return (T)new ClinicDAOImpl();
            case DOCTOR:
                return (T)new DoctorDAOImpl();
            case PATIENT:
                return (T) new PatientDAOImpl();
            case SCHEDULE:
                return (T) new SceduleDAOImpl();
            case PATENT_DETAIL:
                return (T) new PatientDetailDAOImpl();
            case HOSPITAL_ADMIT:
                return (T) new HospitalAdmitDAOImpl();
            case SPECIFIC_FIELD:
                return (T) new SpecificFildDAOImpl();
            case CLINIC_TIME_TABLE:
                return (T) new ClinicTimeTableDAOImpl();
            case DOCTOR_TIME_TABLE:
                return (T) new DoctorTimeTableDAOImpl();
            case PATIENT_APOINTMENT:
                return (T) new PatientAppointmentDAOImpl();
            case QUERYDAO:
                return (T) new QueryDAOImpl();
                default:
                    return null;
        }
    }


    private DAOFactory() {
    }
}
