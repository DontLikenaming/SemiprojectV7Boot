package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import dontlikenaming.springboot.semiprojectv7.repository.AttachRepository;
import dontlikenaming.springboot.semiprojectv7.repository.PdsRepository;
import dontlikenaming.springboot.semiprojectv7.utils.PdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("pdsdao")
public class PdsDAOImpl implements PdsDAO{

    @Autowired PdsRepository pdsRepository;
    @Autowired AttachRepository attachRepository;
    @Autowired PdsUtils pdsUtils;

    @Override
    public Map<String, Object> selectPds(int cpage) {
        PageRequest paging = PageRequest.of(cpage-1,10, Sort.by("pno").descending());

        Map<String, Object> pds = new HashMap<>();
        pds.put("pdslist", pdsRepository.findAll(paging).getContent());
        pds.put("cntpg", pdsRepository.findAll(paging).getTotalPages());

        return pds;
    }

    @Override
    public int insertPds(Pds pds) {
        pds.setUuid(pdsUtils.makeUUID());
        return Math.toIntExact(pdsRepository.save(pds).getPno());
    }

    @Override
    public Map<String, Object> selectPds(Map<String, Object> params) {
        Page<Pds> result = null;

        int page = (int) params.get("stdno");
        String fkey = params.get("fkey").toString();
        String ftype = params.get("ftype").toString();
        PageRequest paging = PageRequest.of(page,10, Sort.by("pno").descending());

        switch (ftype){
            case "title":
                result = pdsRepository.findByTitleContains(paging, fkey);
                break;
            case "content":
                result = pdsRepository.findByContentContains(paging, fkey);
                break;
            case "userid":
                result = pdsRepository.findByUserid(paging, fkey);
                break;
            case "titcont":
                result = pdsRepository.findByTitleContainsOrContentContains(paging, fkey, fkey);
                break;
        }

        Map<String, Object> pds = new HashMap<>();
        pds.put("pdslist", result.getContent());
        pds.put("cntpg", result.getTotalPages());

        return pds;
    }

    @Override
    public Pds selectOnePds(Integer pno) {
        int cntpg = Math.toIntExact(pdsRepository.countPdsBy());
        if(pno>cntpg)pno=cntpg;

        pdsRepository.countViewPds((long) pno);

        return pdsRepository.findById((long) pno).get();
    }

    @Override
    public boolean insertAttach(PdsAttach pa) {
        boolean result = false;
        attachRepository.save(pa);
        if(Math.toIntExact(attachRepository.save(pa).getPano())>0)result = true;

        return result;
    }

    @Override
    public List<PdsAttach> selectAttech(Integer pno) {
        return attachRepository.findPdsattachBypno((long)pno);
    }
}
