package lk.pos.hospital.main;

import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.controller.util.Validation;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.ClinicDAO;
import lk.pos.hospital.dao.custom.DoctorDAO;
import lk.pos.hospital.dao.custom.QueryDAO;
import lk.pos.hospital.dao.custom.SpecificFieldDAO;

public class test {
    public static void main(String[] args) throws Exception{
     // ClinicDAO clinicDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.CLINIC);
        SpecificFieldDAO fieldDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.SPECIFIC_FIELD);
        QueryDAO queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.QUERYDAO);
        DoctorDAO d=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.DOCTOR);

        System.out.println( d.getALL().get().isEmpty());

        System.out.println(" TEST "+Validation.isPhoneNumber("0752691284"));


//        d.getALL().get().stream().forEach(doctor -> {
//            System.out.println(doctor);
//        });

//        try {
//            queryDAO.getHospitalAdmitDetails().get().stream().forEach(customEntityTwo -> {
//                System.out.println(customEntityTwo);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
