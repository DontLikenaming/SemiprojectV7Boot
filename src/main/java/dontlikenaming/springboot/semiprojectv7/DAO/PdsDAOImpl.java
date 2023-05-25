package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import dontlikenaming.springboot.semiprojectv7.repository.AttachRepository;
import dontlikenaming.springboot.semiprojectv7.repository.PdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("pdsdao")
public class PdsDAOImpl implements PdsDAO{

    @Autowired PdsRepository pdsRepository;
    @Autowired AttachRepository attachRepository;


    @Override
    public Map<String, Object> selectPds(int cpage) {
        PageRequest paging = PageRequest.of(cpage-1,10, Sort.by("pno").descending());


        Map<String, Object> pds = new HashMap<>();
        pds.put("pdslist", pdsRepository.findAll(paging).getContent());
        pds.put("cntpg", pdsRepository.findAll(paging).getTotalPages());


        return pds;
    }

    @Override
    public List<String> selectFtype(){
        List<String> ftypes = attachRepository.findbyFtypes();
        List<String> result = new ArrayList<>();

        for(String ftype : ftypes) {
            switch (ftype){
                case "jpg" : result.add("pic"); break;
                case "png" : result.add("pic"); break;
                case "csv" : result.add("txt"); break;
                case "txt" : result.add("txt"); break;
                case "zip" : result.add("zip"); break;

                default: result.add("default"); break;
            }
        }

        return result;
    }

    @Override
    public int insertPds(Pds pds) {
        return Math.toIntExact(pdsRepository.save(pds).getPno());
    }

    @Override
    public int insertAttach(PdsAttach pa) {
        return Math.toIntExact(attachRepository.save(pa).getPano());
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
        pdsRepository.countViewPds((long) pno);

        return pdsRepository.findPdsByPno((long) pno);
    }

    @Override
    public PdsAttach selectAttech(Integer pno) {
        PdsAttach pa = attachRepository.findPdsattachByPno(pno);
        switch (pa.getFtype()){
            case "jpg" :
            case "png" : pa.setPicext("pic"); break;

            case "csv" :
            case "txt" : pa.setPicext("txt"); break;

            case "zip" : pa.setPicext("zip"); break;

            default: pa.setPicext("default"); break;
        }
        return pa;
    }

    @Override
    public Boolean updateAttech(String fname){
        boolean result = false;
        if(pdsRepository.countFdownPds(fname)>0) result = true;

        return result;
    }

/*    @Override
    public Map<String, Object> downAttach(Integer pno){
        String fname = attachRepository.findFnameByPno(pno).getFname();
        String uuid = pdsRepository.findUuidByPno((long) pno).getUuid();
        pdsRepository.countFdownPds(pno);

        Map<String, Object> pds = new HashMap<>();
        pds.put("fname", fname);
        pds.put("uuid", uuid);

        return pds;
    }*/

}
