package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.DoctorDAO;
import lk.pos.hospital.entity.Doctor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lk.pos.hospital.dao.CrudUtil.updateQuery;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public boolean save(Doctor entity) throws Exception {
        String sql="INSERT INTO doctor VALUES(?,?,?,?,?,?,?,?,?,?)";
        return CrudUtil.updateQuery(sql,entity.getDrId(),entity.getNic(),entity.getMedId(),entity.getFullName(),
                                        entity.getAddress(),entity.getTelephone(),entity.getEmail(),entity.getWardId(),
                                        entity.getFieldId(),entity.getGender()) >0;
    }

    @Override
    public boolean updata(Doctor entity) throws Exception {

            String sql="UPDATE doctor SET nic=?,medicalId=?,fullName=?,address=?,telephone=?,email=?, wardId=?, fieldId=?,gender=? WHERE drid=?";
            return updateQuery(sql, entity.getNic(), entity.getMedId(), entity.getFullName(), entity.getAddress(), entity.getTelephone(), entity.getEmail(), entity.getWardId(), entity.getFieldId(), entity.getGender(), entity.getDrId())>0;
          }


    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE FROM doctor WHERE  drid=?";
        return updateQuery(sql,object)>0;
    }

    @Override
    public Optional<Doctor> search(String object) throws Exception {
        String sql="SELECT * FROM doctor WHERE drid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object);
        Doctor doctor=null;
        if(set.next()){
            doctor=new Doctor(  set.getString(1),
                                set.getString(2),
                                set.getString(3),
                                set.getString(4),
                                set.getString(5),
                                set.getString(6),
                                set.getString(7),
                                set.getString(8),
                                set.getString(9),
                                set.getString(10) );
                    }
        return Optional.ofNullable(doctor);
    }

    @Override
    public Optional<List<Doctor>> getALL() throws Exception {
        String sql="SELECT * FROM doctor ";
        List<Doctor>doctors=new ArrayList<>();
        ResultSet set = CrudUtil.exequteQuery(sql);
        while (set.next()){
                   doctors.add( getDoctor( set.getString(1),
                                           set.getString(2),
                                           set.getString(3),
                                           set.getString(4),
                                           set.getString(5),
                                           set.getString(6),
                                           set.getString(7),
                                           set.getString(8),
                                           set.getString(9),
                                           set.getString(10)  ) );
        }
        return Optional.ofNullable(doctors);
    }

    private Doctor getDoctor(String did,String nic,String mid,String name,String address,String phone,String email,String wardid,String field,String gender){
       return new Doctor(did,nic,mid,name,address,phone,email,wardid,field,gender);
    }
}
