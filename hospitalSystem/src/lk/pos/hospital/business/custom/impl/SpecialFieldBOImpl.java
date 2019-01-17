package lk.pos.hospital.business.custom.impl;

import lk.pos.hospital.business.Converter;
import lk.pos.hospital.business.custom.SpecialFieldBO;
import lk.pos.hospital.dao.DAOFactory;
import lk.pos.hospital.dao.custom.SpecificFieldDAO;
import lk.pos.hospital.dto.SpecialFieldDTO;
import lk.pos.hospital.entity.SpecificField;

import java.util.List;

public class SpecialFieldBOImpl implements SpecialFieldBO {
    SpecificFieldDAO fieldDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTYPE.SPECIFIC_FIELD);
    @Override
    public boolean saveSfield(SpecialFieldDTO dto)throws Exception {
        return fieldDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateSfield(SpecialFieldDTO dto) throws Exception{
        return fieldDAO.updata(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteSField(String fid)throws Exception {
        return fieldDAO.delete(fid);
    }

    @Override
    public SpecialFieldDTO searchSField(String fid) throws Exception{
        return fieldDAO.search(fid).map(Converter::<SpecialFieldDTO>getDTO).orElse(null);
    }

    @Override
    public List<SpecialFieldDTO> getAllSField()throws Exception {
        return fieldDAO.getALL().map(Converter::<SpecialFieldDTO>getDtoList).get();
    }

    @Override
    public String generateSFieldID() throws Exception {
        List<SpecificField> fields =  fieldDAO.getALL().get();
        int count = fields.size();
        System.out.println(count+"count");

        if (fields == null|| fields.isEmpty()  || count <= 0) {
            return "F001";
        }
        String setRegID = null;
        SpecificField register = fields.get(count - 1);
        String fieldRID = register.getfId();
        String firstIndex = fieldRID.substring(0, 1);
        String otherIndexs = fieldRID.substring(1);

        int y=0;
        int x=0;
        int n=0;



        if (Integer.parseInt(fieldRID.substring(1, 2)) <9) {
            y=Integer.parseInt(fieldRID.substring(1, 2));
            if (Integer.parseInt(fieldRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(fieldRID.substring(2, 3));
                if(Integer.parseInt(fieldRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(fieldRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(fieldRID.substring(2, 3));

                if(Integer.parseInt(fieldRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(fieldRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(fieldRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        } else {
            y=Integer.parseInt(fieldRID.substring(1, 2));

            if (Integer.parseInt(fieldRID.substring(2, 3)) < 9) {
                //x
                x=Integer.parseInt(fieldRID.substring(2, 3));
                if(Integer.parseInt(fieldRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(fieldRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            } else{
                //!x
                x=Integer.parseInt(fieldRID.substring(2, 3));

                if(Integer.parseInt(fieldRID.substring(3, 4))<9){
                    //n
                    n=Integer.parseInt(fieldRID.substring(3, 4))+1;
                    setRegID = firstIndex + y + x +n;
                }else{
                    //!n
                    n=0;
                    n=0;
                    y=y+1;
                    n=Integer.parseInt(fieldRID.substring(3, 4));
                    x=x+1;
                    setRegID=firstIndex+y+x+n;
                }

            }
        }

        return setRegID;

    }
}
