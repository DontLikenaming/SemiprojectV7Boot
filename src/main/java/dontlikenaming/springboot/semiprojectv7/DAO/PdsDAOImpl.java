package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import dontlikenaming.springboot.semiprojectv7.model.PdsReply;
import dontlikenaming.springboot.semiprojectv7.repository.AttachRepository;
import dontlikenaming.springboot.semiprojectv7.repository.PdsReplyRepository;
import dontlikenaming.springboot.semiprojectv7.repository.PdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@Repository("pdsdao")
public class PdsDAOImpl implements PdsDAO{

    @Autowired PdsRepository pdsRepository;
    @Autowired AttachRepository attachRepository;
    @Autowired PdsReplyRepository pdsReplyRepository;


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
                case "jpg" :
                case "png" : result.add("pic"); break;

                case "csv" :
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

    @Override
    public List<PdsReply> selectPdsReply(Integer pno) {
        return pdsReplyRepository.findByPnoOrderByRefnoAscRegdateAsc(pno);
    }

    @Override
    public boolean insertPdsReply(PdsReply pry) {
        // 댓글을 저장할 땐 생성되지 않은 댓글번호를
        // refno에도 저장하기 위해 update 호출함

        boolean result = false;
        if(pdsReplyRepository.save(pry).getRpno()>0){
            pdsReplyRepository.updateRefno(Math.toIntExact(pry.getRpno()));
            result = true;
        }
        return result;
    }

    @Override
    public boolean insertPdsRreply(PdsReply pry) {
        // 대댓글을 저장할 때에는 이미 링크로
        // 댓글번호를 전달받았기 때문에
        // update를 호출하지 않고 바로 처리함

        boolean result = false;

        if(pdsReplyRepository.save(pry).getRpno()>0){
            result = true;
        }
        return result;
    }

}
