package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.WardDAO;
import lk.pos.hospital.entity.Ward;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WardDAOImpl implements WardDAO {
    @Override
    public boolean save(Ward entity) throws Exception {
        String sql="INSERT INTO ward VALUES(?,?)";
        return CrudUtil.updateQuery(sql,entity.getWardId(),entity.getWardName()) >0;
    }

    @Override
    public boolean updata(Ward entity) throws Exception {
        String sql="UPDATE ward SET wardName=? WHERE wid=?";
        return CrudUtil.updateQuery(sql,entity.getWardName(),entity.getWardId()) >0;
    }

    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE FROM ward WHERE wid=?";
        return CrudUtil.updateQuery(sql,object) >0;
    }

    @Override
    public Optional<Ward> search(String object) throws Exception {
        String sql="SELECT * FROM ward WHERE wid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object);
        Ward ward=null;
        if(set.next()){
            ward=new Ward(set.getString(1),set.getString(2));
        }
        return Optional.ofNullable(ward);
    }

    @Override
    public Optional<List<Ward>> getALL() throws Exception {
        String sql="SELECT * FROM ward ";
        List<Ward>wards=new ArrayList<>();
        ResultSet set = CrudUtil.exequteQuery(sql);
        while (set.next()){
            wards.add( new Ward(set.getString(1),set.getString(2)));
        }

        return Optional.ofNullable(wards);
    }
}
