package lk.pos.hospital.dao.custom.impl;

import lk.pos.hospital.dao.CrudUtil;
import lk.pos.hospital.dao.custom.SpecificFieldDAO;
import lk.pos.hospital.entity.SpecificField;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecificFildDAOImpl implements SpecificFieldDAO {
    @Override
    public boolean save(SpecificField entity) throws Exception {
        String sql="INSERT INTO specificfield VALUES(?,?,?)";
        return CrudUtil.updateQuery(sql,entity.getfId(),entity.getFieldName(),entity.getWardId())>0;
    }

    @Override
    public boolean updata(SpecificField entity) throws Exception {
        String sql="UPDATE specificfield SET fieldName=?,wardId=? WHERE fid=?";
        return  CrudUtil.updateQuery(sql,entity.getFieldName(),entity.getWardId(),entity.getfId())>0;
    }

    @Override
    public boolean delete(String object) throws Exception {
        String sql="DELETE specificfield ward WHERE fid=?";
        return  CrudUtil.updateQuery(sql,object)>0;
    }

    @Override
    public Optional<SpecificField> search(String object) throws Exception {
        String sql="SELECT * FROM specificfield WHERE fid=?";
        ResultSet set = CrudUtil.exequteQuery(sql, object);
        SpecificField specificField=null;
        if(set.next()){
           specificField =new SpecificField(set.getString(1),set.getString(2),set.getString(3));
        }
        return Optional.ofNullable(specificField);
    }

    @Override
    public Optional<List<SpecificField>> getALL() throws Exception {
        String sql="SELECT * FROM specificfield";
        List<SpecificField>specificFields=new ArrayList<>();

        ResultSet set = CrudUtil.exequteQuery(sql);

        while (set.next()){
            specificFields.add(new SpecificField(set.getString(1),set.getString(2),set.getString(3)));
        }

        return Optional.ofNullable(specificFields);
    }
}
