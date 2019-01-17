package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.ClinicDAO;
import lk.pos.hospital.entity.Clinic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClinicDAOImpl implements ClinicDAO {

    @Override
    public boolean save(Clinic entity) throws Exception {
        String sql="INSERT INTO clinic VALUES(?,?,?,?)";
        return CrudUtil.updateQuery(sql,entity.getcId(),entity.getClinicName(),entity.getFieldID(),entity.getClinicCharge())>0;
    }

    @Override
    public boolean updata(Clinic entity) throws Exception {
        String sql="UPDATE clinic SET clinicName=?,fieldId=?,clinicCharge=? WHERE cid=?";
          return CrudUtil.updateQuery(sql,entity.getClinicName(),entity.getFieldID(),entity.getClinicCharge(),entity.getcId())>0;
    }

    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE FROM clinic WHERE cid=?";
        return CrudUtil.updateQuery(sql,object)>0;
    }

    @Override
    public Optional<Clinic> search(String object) throws Exception {
        String sql="SELECT * FROM clinic WHERE cid=?";
        ResultSet resultSet = CrudUtil.exequteQuery(sql, object);
        Clinic clinic=null;
        if (resultSet.next()){
            clinic=new Clinic(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
        }
        return Optional.ofNullable(clinic);
    }

    @Override
    public Optional<List<Clinic>> getALL() throws Exception {
        String sql="SELECT * FROM clinic ";
        ResultSet resultSet = CrudUtil.exequteQuery(sql);
        System.out.println("ok boss");
        List<Clinic>clinics=new ArrayList<>();
        while (resultSet.next()){
            clinics.add(new Clinic(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4)));
        }

        return Optional.ofNullable(clinics);
    }
}
