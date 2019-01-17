package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.ClinicBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.ClinicDAO;
import lk.pos.hospital.dao.custom.ClinicTimeTableDAO;
import lk.pos.hospital.dao.custom.QueryDAO;
import lk.pos.hospital.db.DBConnection;
import lk.pos.hospital.dto.ClinicDTO;
import lk.pos.hospital.dto.ClinicTimeTableDTO;
import lk.pos.hospital.dto.CustomDTO;
import lk.pos.hospital.dto.SpecialFieldDTO;
import lk.pos.hospital.entity.Clinic;

import java.util.List;

public class ClinicBOImpl implements ClinicBO {
    ClinicDAO clinicDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.CLINIC);
    QueryDAO queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.QUERYDAO);
    ClinicTimeTableDAO  timeTableDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.CLINIC_TIME_TABLE);
    @Override
    public boolean saveClinic(ClinicDTO dto) throws Exception {
        return clinicDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateClinic(ClinicDTO dto) throws Exception {
        return clinicDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteClinic(ClinicTimeTableDTO object) throws Exception {
        if(getClincSchedule(object)==null){
            return clinicDAO.delete(object.getClinicId());
        }
          try{
              clinicDelete(object);
              return true;
          }finally {
              return false;
          }
    }

    private  void  clinicDelete(ClinicTimeTableDTO object) throws Exception {
        boolean isSave=false;

        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            isSave=timeTableDAO.delete(Converter.getEntity(object));
            if(!isSave){
                return;
            }
            isSave= clinicDAO.delete(object.getClinicId());
            if(!isSave){
                DBConnection.getInstance().getConnection().rollback();
                return ;
            }
        }catch (Exception e){
            DBConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }


    @Override
    public ClinicDTO searchClinic(String object) throws Exception {
        return clinicDAO.search(object).map(Converter::<ClinicDTO>getDTO).orElse(null);
    }

    @Override
    public List<ClinicDTO> getAllClinic() throws Exception {
        return clinicDAO.getALL().map(Converter::<ClinicDTO>getDtoList).get();
    }

    @Override
    public String getRegisterID() throws Exception {
        List<Clinic> schedules =  clinicDAO.getALL().get();
        int count = schedules.size();

        if (schedules == null|| schedules.isEmpty()  || count <= 0) {
            return "C001";
        }
        String setRegID = null;
        Clinic register = schedules.get(count - 1);
        String clinicRID = register.getcId();
        String firstIndex = clinicRID.substring(0, 1);


        int y=0;
        int x=0;
        int n=0;


        if (Integer.parseInt(clinicRID.substring(1, 2)) <9) {
            y=Integer.parseInt(clinicRID.substring(1, 2));
            if (Integer.parseInt(clinicRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(clinicRID.substring(2, 3));
                if(Integer.parseInt(clinicRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(clinicRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(clinicRID.substring(2, 3));

                if(Integer.parseInt(clinicRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(clinicRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(clinicRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        } else {
            y=Integer.parseInt(clinicRID.substring(1, 2));

            if (Integer.parseInt(clinicRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(clinicRID.substring(2, 3));
                if(Integer.parseInt(clinicRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(clinicRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(clinicRID.substring(2, 3));

                if(Integer.parseInt(clinicRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(clinicRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(clinicRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        }

        return setRegID;

    }

    @Override
    public boolean saveClincToSCXhedule(ClinicTimeTableDTO dto) throws Exception {
        return timeTableDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean upDateClinicSchedule(ClinicTimeTableDTO dto) throws Exception {
        return timeTableDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteClinicSchedule(ClinicTimeTableDTO dto) throws Exception {
        return timeTableDAO.delete(Converter.getEntity(dto));
    }

    @Override
    public ClinicTimeTableDTO getClincSchedule(ClinicTimeTableDTO dto) throws Exception {
        return timeTableDAO.search(Converter.getEntity(dto)).map(Converter::<ClinicTimeTableDTO>getDTO).orElse(null);
    }

    @Override
    public List<ClinicTimeTableDTO> getAllClinicSchedule() throws Exception {
        return timeTableDAO.getALL().map(Converter::<ClinicTimeTableDTO>getDtoList).get();
    }

    @Override
    public List<CustomDTO> getALLForTable() throws Exception {

        return queryDAO.getClinc().map(Converter::<CustomDTO>getDtoList).get();
    }
}
