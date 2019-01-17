package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.BedBO;
import lk.pos.hospital.business.custom.PatientAdmitBO;
import lk.pos.hospital.business.custom.PatientBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.HospitalAdmitDAO;
import lk.pos.hospital.dao.custom.PatientDAO;
import lk.pos.hospital.dao.custom.QueryDAO;
import lk.pos.hospital.db.DBConnection;
import lk.pos.hospital.dto.BedDTO;
import lk.pos.hospital.dto.CustomDTOTwo;
import lk.pos.hospital.dto.HospitalAdmitDTO;

import java.util.List;

public class PatientAdmitBOImpl implements PatientAdmitBO {
    PatientDAO patientDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.PATIENT);
    HospitalAdmitDAO hospitalAdmitDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.HOSPITAL_ADMIT);
    BedBO bedBO= BOFactory.getInstance().getBo(BOFactory.BOType.BED);
    QueryDAO queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.QUERYDAO);
    @Override
    public boolean savePatentHospitalAdmit(HospitalAdmitDTO dto, BedDTO bdto) throws Exception {
        return patientAdmi(dto,bdto);


    }

    private boolean patientAdmi(HospitalAdmitDTO dto, BedDTO bdto) throws Exception {
        boolean isSave=false;
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            isSave=hospitalAdmitDAO.save(Converter.getEntity(dto));
            if(!isSave){
                return false;
            }
            boolean isUpdate = bedBO.upDateBEDWithPatient(bdto);
            if(!isUpdate){
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
            return true;

        }catch (Exception e){
            DBConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean updatePatentHospitalAdmit(HospitalAdmitDTO dto) throws Exception {
        return hospitalAdmitDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deletePatentHospitalAdmit(HospitalAdmitDTO dto) throws Exception {
        return hospitalAdmitDAO.delete(Converter.getEntity(dto));
    }

    @Override
    public HospitalAdmitDTO searchPatentHospitalAdmit(HospitalAdmitDTO dto) throws Exception {
        return hospitalAdmitDAO.search(Converter.getEntity(dto)).map(Converter::<HospitalAdmitDTO>getDTO).orElse(null);
    }

    @Override
    public List<HospitalAdmitDTO> getAllPatentHospitalAdmit() throws Exception {
        return hospitalAdmitDAO.getALL().map(Converter::<HospitalAdmitDTO>getDtoList).get();
    }

    @Override
    public boolean dischargePatent(HospitalAdmitDTO dto, BedDTO bdto) throws Exception {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdate = bedBO.upDateBEDWithOUTPatient(bdto);
            if(!isUpdate){
                return false;
            }
            hospitalAdmitDAO.updata(Converter.getEntity(dto));
        }catch (Exception e){

            e.getStackTrace();
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }


        return false;
    }

    @Override
    public List<CustomDTOTwo> getAllForTABLE() throws Exception {
        return queryDAO.getHospitalAdmitDetails().map(Converter::<CustomDTOTwo>getDtoList).get() ;
    }
}
