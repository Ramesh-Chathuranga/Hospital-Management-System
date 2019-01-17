package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.DoctorRegBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.DoctorDAO;
import lk.pos.hospital.dao.custom.DoctorTimeTableDAO;
import lk.pos.hospital.dao.custom.QueryDAO;
import lk.pos.hospital.dto.CustomDTO;
import lk.pos.hospital.dto.DoctorDTO;
import lk.pos.hospital.dto.DoctorTimeTableDTO;
import lk.pos.hospital.entity.CustomEntity;
import lk.pos.hospital.entity.Doctor;

import java.util.List;

public class DoctorBOImpl implements DoctorRegBO {
    DoctorDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.DOCTOR);
    QueryDAO qDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.QUERYDAO);
    DoctorTimeTableDAO tableDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.DOCTOR_TIME_TABLE);
    @Override
    public boolean saveDoctor(DoctorDTO dto) throws Exception {
        return dao.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateDoctor(DoctorDTO dto) throws Exception {
        return dao.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteDoctor(String rid) throws Exception {
        return dao.delete(rid);
    }

    @Override
    public DoctorDTO searchDoctor(String rid) throws Exception {
        return dao.search(rid).map(Converter::<DoctorDTO>getDTO).orElse(null);
    }

    @Override
    public List<DoctorDTO> getAllDoctor() throws Exception {
        return dao.getALL().map(Converter::<DoctorDTO>getDtoList).get();
    }

    @Override
    public String autoDocRegIdDoctor() throws Exception {
        List<Doctor> doctors =  dao.getALL().get();
        int count=0;
        if(doctors.isEmpty()){
            count=0;
        }else {
            count = doctors.size();
        }
        System.out.println(count+"count");

        if (doctors == null|| doctors.isEmpty()  || count <= 0) {
            return "D001";
        }
        String setRegID = null;
        Doctor register = doctors.get(count - 1);
        String doctorRID = register.getDrId();
        String firstIndex = doctorRID.substring(0, 1);
        String otherIndexs = doctorRID.substring(1);

        int y=0;
        int x=0;
        int n=0;

        // System.out.println(doctorRID.length() + "" + otherIndexs.length());

        if (Integer.parseInt(doctorRID.substring(1, 2)) <9) {
            y=Integer.parseInt(doctorRID.substring(1, 2));
            if (Integer.parseInt(doctorRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(doctorRID.substring(2, 3));
                if(Integer.parseInt(doctorRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(doctorRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(doctorRID.substring(2, 3));

                if(Integer.parseInt(doctorRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(doctorRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(doctorRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        } else {
            y=Integer.parseInt(doctorRID.substring(1, 2));

            if (Integer.parseInt(doctorRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(doctorRID.substring(2, 3));
                if(Integer.parseInt(doctorRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(doctorRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(doctorRID.substring(2, 3));

                if(Integer.parseInt(doctorRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(doctorRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(doctorRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        }

        return setRegID;

    }

    @Override
    public List<CustomDTO> getAllDetailsByWid(String wid) throws Exception {
        return qDAO.getCustomEntityByWidOFField(wid).map(Converter::<CustomDTO>getDtoList).get();
    }

    @Override
    public boolean saveDoctorSchedule(DoctorTimeTableDTO dto) throws Exception {
        return tableDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteoctorSchedule(DoctorTimeTableDTO dto) throws Exception {
        return tableDAO.delete(Converter.getEntity(dto));
    }

    @Override
    public DoctorTimeTableDTO searchSchedule(DoctorTimeTableDTO dto) throws Exception {
        return tableDAO.search(Converter.getEntity(dto)).map(Converter::<DoctorTimeTableDTO>getDTO).orElse(null);
    }

    @Override
    public List<DoctorTimeTableDTO> getoctorScheduleDetails() throws Exception {
        return tableDAO.getALL().map(Converter::<DoctorTimeTableDTO>getDtoList).get();
    }
}
