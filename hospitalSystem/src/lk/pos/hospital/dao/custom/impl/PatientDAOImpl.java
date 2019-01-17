package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.PatientDAO;
import lk.pos.hospital.entity.Patient;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient entity) throws Exception {
        String sql="INSERT INTO patient VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.updateQuery(sql,entity.getPid(),entity.getNic(),entity.getFullName(),entity.getAddress(),entity.getTelephoneNO(),entity.getEmail(),entity.getGender()) >0;
    }

    @Override
    public boolean updata(Patient entity) throws Exception {
        String sql="UPDATE patient SET nic=?,fullName=?,address=?,telephoneNO=?,email=?,gender=? WHERE pid=?";
        return  CrudUtil.updateQuery(sql,entity.getNic(),entity.getFullName(),entity.getAddress(),entity.getTelephoneNO(),entity.getEmail(),entity.getGender(),entity.getPid()) >0;
    }

    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE FROM patient WHERE pid=?";
        return CrudUtil.updateQuery(sql,object)>0;
    }

    @Override
    public Optional<Patient> search(String object) throws Exception {
        String sql="SELECT * FROM patient WHERE pid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object);
        Patient patient=null;
        if(set.next()){
            patient=new Patient(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7));
        }
        return Optional.ofNullable(patient);
    }

    @Override
    public Optional<List<Patient>> getALL() throws Exception {
        String sql="SELECT * FROM patient ";
        ResultSet set = CrudUtil.exequteQuery(sql);
        List<Patient>patients=new ArrayList<>();
        while (set.next()){
            patients.add(new Patient(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7)));
        }

        return Optional.ofNullable(patients);
    }
}
