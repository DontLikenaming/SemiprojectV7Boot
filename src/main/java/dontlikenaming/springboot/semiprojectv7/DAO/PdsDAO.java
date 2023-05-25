package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;

import java.util.List;
import java.util.Map;

public interface PdsDAO {
    Map<String, Object> selectPds(int cpage);

    int insertPds(Pds pds);

    Map<String, Object> selectPds(Map<String, Object> params);

    Pds selectOnePds(Integer bno);

    int insertAttach(PdsAttach pa);
    PdsAttach selectAttech(Integer pno);
}
