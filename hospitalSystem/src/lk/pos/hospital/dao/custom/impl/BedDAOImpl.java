package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.BedDAO;
import lk.pos.hospital.entity.Bed;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BedDAOImpl implements BedDAO {


    @Override
    public boolean save(Bed entity) throws Exception {
            String sq="INSERT INTO bed (bedid, wardid)VALUES (?,?)";
        //    String sql="INSERT INTO bed VALUES(?,?,?)";
            return CrudUtil.updateQuery(sq,entity.getBesID(),entity.getWardID()) >0;


    }

    @Override
    public boolean updata(Bed entity) throws Exception {
        String sql="UPDATE bed SET wardid =? ,patientid=? WHERE bedid=?";
        return CrudUtil.updateQuery(sql,entity.getWardID(),null,entity.getBesID()) >0;
    }

    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE FROM bed WHERE  bedid=?";
        return CrudUtil.updateQuery(sql,object)>0;
    }

    @Override
    public Optional<Bed> search(String object) throws Exception {
        String sql="SELECT * FROM bed WHERE  bedid=?";
        Bed bed=null;
        ResultSet set = CrudUtil.exequteQuery(sql, object);
        if(set.next()){
            bed=new Bed(set.getString(1),set.getString(2),set.getString(3));
        }
        return Optional.ofNullable(bed);
    }

    @Override
    public Optional<List<Bed>> getALL() throws Exception {
        String sql="SELECT * FROM bed ";
        List<Bed> beds=new ArrayList<>();
        ResultSet set = CrudUtil.exequteQuery(sql);
        while (set.next()){
           beds.add(new Bed(set.getString(1),set.getString(2),set.getString(3)));
        }
        return Optional.ofNullable(beds);
    }

    @Override
    public boolean addPatienTOBED(Bed entity) throws Exception {
        String sql="UPDATE bed SET wardid =? ,patientid=? WHERE bedid=?";
        return CrudUtil.updateQuery(sql,entity.getWardID(),entity.getPatientID(),entity.getBesID())>0;
    }
}
