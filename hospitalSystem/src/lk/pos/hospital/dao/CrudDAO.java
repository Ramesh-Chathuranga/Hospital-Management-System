package lk.pos.hospital.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T,ID> extends SuperDAO{
    public boolean save(T entity)throws Exception;
    public boolean updata(T entity)throws Exception;
    public  boolean delete(ID object)throws Exception;
    public Optional<T> search(ID object)throws Exception;
    public Optional<List<T>> getALL()throws Exception;
}
