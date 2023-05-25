package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Map<String, Object> selectBoard(int cpage) {
        PageRequest paging = PageRequest.of(cpage-1,10, Sort.by("bno").descending());

        Map<String, Object> bds = new HashMap<>();
        bds.put("bdlist", boardRepository.findAll(paging).getContent());
        bds.put("cntpg", boardRepository.findAll(paging).getTotalPages());

        return bds;
    }

    @Override
    public int insertBoard(Board bd) {
        return Math.toIntExact(boardRepository.save(bd).getBno());
    }

    @Override
    public Map<String, Object> selectBoard(Map<String, Object> params) {
        Page<Board> result = null;

        int page = (int) params.get("stdno");
        String fkey = params.get("fkey").toString();
        String ftype = params.get("ftype").toString();
        PageRequest paging = PageRequest.of(page,10, Sort.by("bno").descending());

        switch (ftype){
            case "title":
                result = boardRepository.findByTitleContains(paging, fkey);
                break;
            case "content":
                result = boardRepository.findByContentContains(paging, fkey);
                break;
            case "userid":
                result = boardRepository.findByUserid(paging, fkey);
                break;
            case "titcont":
                result = boardRepository.findByTitleContainsOrContentContains(paging, fkey, fkey);
                break;
        }

        Map<String, Object> bds = new HashMap<>();
        bds.put("bdlist", result.getContent());
        bds.put("cntpg", result.getTotalPages());

        return bds;
    }

    @Override
    public Board selectOneBoard(Integer bno) {
        int cntpg = Math.toIntExact(boardRepository.countBoardBy());
        if(bno>cntpg)bno=cntpg;

        boardRepository.countViewBoard((long) bno);

        return boardRepository.findById((long) bno).get();
    }

    /*@Override
    public int countBoard(Map<String, Object> params) {
        int allcnt = 0;

        // like 검색에 대한 query method
        // findByTitleLike          : %검색어% (%문자 제공 필요)
        // findByTitleContains      : %검색어% (%문자 제공 필요x)
        // findByTitleStartsWith    : 검색어% (%문자 제공 필요x)
        // findByTitleEndsWith      : %검색어 (%문자 제공 필요x)
        String fkey = params.get("fkey").toString();
        String ftype = params.get("ftype").toString();

        switch (ftype){
            case "title":
                allcnt = Math.toIntExact(boardRepository.countBnoByTitleContains(fkey)); break;
            case "content":
                allcnt = Math.toIntExact(boardRepository.countBnoByContentContains(fkey)); break;
            case "userid":
                allcnt = Math.toIntExact(boardRepository.countBnoByUserid(fkey)); break;
            case "titcont":
                allcnt = Math.toIntExact(boardRepository.countBnoByTitleContainsOrContentContains(fkey, fkey)); break;
        }
        if(allcnt==0)allcnt=1;

        return (int) Math.ceil((double)allcnt/10);
    }*/
}
