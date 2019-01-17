package lk.pos.hospital.business.custom.impl;


import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.BedBO;
import lk.pos.hospital.business.custom.PatientBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.*;
import lk.pos.hospital.db.DBConnection;
import lk.pos.hospital.dto.*;
import lk.pos.hospital.entity.Patient;
import lk.pos.hospital.entity.PatientApointment_FK;
import lk.pos.hospital.entity.PatientDetail;
import lk.pos.hospital.entity.PatientDetail_PK;

import java.util.List;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.PATIENT);
    HospitalAdmitDAO hospitalAdmitDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.HOSPITAL_ADMIT);
    BedBO bedBO= BOFactory.getInstance().getBo(BOFactory.BOType.BED);
    PatientDetailDAO detailDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.PATENT_DETAIL);
    QueryDAO queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.QUERYDAO);
    PatientAppointmentDAO dao=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.PATIENT_APOINTMENT);


    @Override
    public boolean savePatient(PatientDTO object) throws Exception {
        return patientDAO.save(Converter.getEntity(object)) ;
    }

    @Override
    public boolean updatePatient(PatientDTO object) throws Exception {
        return patientDAO.updata(Converter.getEntity(object)) ;
    }

    @Override
    public boolean deletePatient(String object) throws Exception {
        return patientDAO.delete(object);
    }

    @Override
    public PatientDTO searchPatient(String object) throws Exception {
        return patientDAO.search(object).map(Converter::<PatientDTO>getDTO).orElse(null);
    }

    @Override
    public List<PatientDTO> getAllPatient() throws Exception {
        return patientDAO.getALL().map(Converter::<PatientDTO>getDtoList).get();
    }

    @Override
    public String getPatientRegisterID() throws Exception {
        List<Patient> patients =  patientDAO.getALL().get();
        int count = patients.size();
        System.out.println(count+"count");

        if (patients == null|| patients.isEmpty()  || count <= 0) {
            return "P001";
        }
        String setRegID = null;
        Patient register = patients.get(count - 1);
        String patientRID = register.getPid();
        String firstIndex = patientRID.substring(0, 1);
        String otherIndexs = patientRID.substring(1);

        int y=0;
        int x=0;
        int n=0;

        // System.out.println(patientRID.length() + "" + otherIndexs.length());

        if (Integer.parseInt(patientRID.substring(1, 2)) <9) {
            y=Integer.parseInt(patientRID.substring(1, 2));
            if (Integer.parseInt(patientRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(patientRID.substring(2, 3));
                if(Integer.parseInt(patientRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(patientRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(patientRID.substring(2, 3));

                if(Integer.parseInt(patientRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(patientRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(patientRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        } else {
            y=Integer.parseInt(patientRID.substring(1, 2));

            if (Integer.parseInt(patientRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(patientRID.substring(2, 3));
                if(Integer.parseInt(patientRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(patientRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(patientRID.substring(2, 3));

                if(Integer.parseInt(patientRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(patientRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(patientRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        }

        return setRegID;

    }

    @Override
    public boolean savePatientDetail(PatientDetailDTO dto,AppointmentDTO aDTO) throws Exception {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean save = detailDAO.save(Converter.getEntity(dto));
            System.out.println(save);
            if(!save){
                return false;
            }
            boolean save1 = dao.save(Converter.getEntity(aDTO));
            if(!save1){
                DBConnection.getInstance().getConnection().rollback();
                return  false;
            }

           return true;
        }catch (Exception e){
            DBConnection.getInstance().getConnection().rollback();
            return  false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    @Override
    public boolean deletePatientDetail(PatientDetailDTO dto) throws Exception {
        return detailDAO.delete(new PatientDetail_PK(dto.getPatientId(),dto.getClinicId())) ;
    }

    @Override
    public boolean updatePatientDetail(PatientDetailDTO dto) throws Exception {
        return detailDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public PatientDetailDTO searchPatientDetail(PatientDetailDTO dto) throws Exception {
        return detailDAO.search(new PatientDetail_PK(dto.getPatientId(),dto.getClinicId())).map(Converter::<PatientDetailDTO>getDTO).orElse(null);
    }

    @Override
    public  List<Custom_LDTO> getForPatienDetailTable() throws Exception {
        return  queryDAO.getAllPatient().map(Converter::<Custom_LDTO>getDtoList).get();
    }

    @Override
    public List<AppointmentDTO> getAllApointment() throws Exception {
        return dao.getALL().map(Converter::<AppointmentDTO>getDtoList).get();
    }

    @Override
    public List<PatientDetailDTO> getAllPatientDetail() throws Exception {
        return detailDAO.getALL().map(Converter::<PatientDetailDTO>getDtoList).get();
    }


}
