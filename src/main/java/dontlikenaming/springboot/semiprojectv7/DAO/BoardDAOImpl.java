package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Board> selectBoard(int cpage) {
        PageRequest paging = PageRequest.of(cpage-1,10, Sort.by("bno").descending());
        return boardRepository.findAll(paging).getContent();
    }

    @Override
    public int selectBoard() {
        return Math.toIntExact(boardRepository.find());
    }

    @Override
    public int countBoard() {
        int allcnt = boardRepository.countBoardBy();
        return (int) Math.ceil((double)allcnt/10);
    }

    @Override
    public int insertBoard(Board bd) {
        return Math.toIntExact(boardRepository.save(bd).getBno());
    }

    @Override
    public List<Board> selectBoard(Map<String, Object> params) {
        List<Board> result = null;

        int page = (int) params.get("stdno");
        String fkey = params.get("fkey").toString();
        String ftype = params.get("ftype").toString();
        PageRequest paging = PageRequest.of(page,10, Sort.by("bno").descending());

        switch (ftype){
            case "title":
                result = boardRepository.findByTitleContains(paging, fkey).getContent(); break;
            case "content":
                result = boardRepository.findByContentContains(paging, fkey).getContent(); break;
            case "userid":
                result = boardRepository.findByUserid(paging, fkey).getContent(); break;
            case "titcont":
                result = boardRepository.findByTitleContainsOrContentContains(paging, fkey, fkey).getContent(); break;
        }

        return result;
    }

    @Override
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
    }

    @Override
    public Board selectOneBoard(Integer bno) {
        boardRepository.countViewBoard((long) bno);
        return boardRepository.findById((long) bno).get();
    }
}
