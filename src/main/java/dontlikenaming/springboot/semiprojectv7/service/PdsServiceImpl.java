package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.DAO.PdsDAO;
import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pdssrv")
public class PdsServiceImpl implements PdsService{
    @Autowired private PdsDAO pdsdao;

    @Override
    public Map<String, Object> readPds(Integer cpage) {
        return pdsdao.selectPds(cpage);
    }

    @Override
    public Map<String, Object> readPds(Integer page, String ftype, String fkey) {
        int stdno = (page-1);

        // 처리 시 사용할 데이터들을 해쉬맵에 담아서 보냄
        Map<String, Object> params = new HashMap<>();
        params.put("stdno", stdno);
        params.put("ftype", ftype);
        params.put("fkey", fkey);

        return pdsdao.selectPds(params);
    }

    @Override
    public int newPds(Pds pds) {
        int result = -1;
        if(pdsdao.insertPds(pds)>0) { result = pdsdao.insertPds(pds); }

        return result;
    }

    @Override
    public Pds readOnePds(Integer bno) {
        return pdsdao.selectOnePds(bno);
    }

    @Override
    public boolean newPdsAttach(MultipartFile attach, int pno) {
        String fname = attach.getOriginalFilename();
        String ftype = attach.getContentType();
        String fsize = String.valueOf(attach.getSize()/1024);

        PdsAttach pa = new PdsAttach(null, fname, ftype, fsize, null, pno);

        return pdsdao.insertAttach(pa);
    }


    @Override
    public List<PdsAttach> selectAttech(Integer pno) {
        return pdsdao.selectAttech(pno);
    }

}
