package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.DAO.PdsDAO;
import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import dontlikenaming.springboot.semiprojectv7.utils.PdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pdssrv")
public class PdsServiceImpl implements PdsService{
    @Autowired private PdsDAO pdsdao;
    @Autowired PdsUtils pdsUtils;

    @Override
    public Map<String, Object> readPds(Integer cpage) {
        return pdsdao.selectPds(cpage);
    }

    @Override
    public Map<String, Object> newPds(Pds pds) {
        pds.setUuid(pdsUtils.makeUUID());   // 식별코드 생성
        int pno = pdsdao.insertPds(pds);    // 폼 데이터 DB에 저장

        // 첨부파일을 시스템에 저장할 때 사용할 정보를 Map에 저장
        Map<String, Object> pinfo = new HashMap<>();
        pinfo.put("pno", pno);
        pinfo.put("uuid", pds.getUuid());

        return pinfo;
    }

    @Override
    public boolean newPdsAttach(MultipartFile attach, Map<String, Object> pinfo) {
        // 첨부파일 업로드 처리
        PdsAttach pa = pdsUtils.processUpload(attach, pinfo);

        // 첨부파일 정보 DB에 저장
        int pano = pdsdao.insertAttach(pa);

        return pano > 0;
    }

    @Override
    public Pds readOnePds(Integer pno) {
        return pdsdao.selectOnePds(pno);
    }

    @Override
    public PdsAttach readOneAttach(Integer pno) {
        return pdsdao.selectAttech(pno);
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
}
