package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        return (int) Math.ceil((allcnt/10));
    }

    @Override
    public int insertBoard(Board bd) {
        return Math.toIntExact(boardRepository.save(bd).getBno());
    }

    @Override
    public List<Board> selectBoard(Map<String, Object> params) {
        List<Board> board = new ArrayList<>();

        int page = (int) params.get("stdno");
        System.out.println("dao : " + page);
        String fkey = "%" + params.get("fkey") + "%";
        PageRequest paging = PageRequest.of(page,10, Sort.by("bno").descending());

        if(String.valueOf(params.get("ftype")).equals("title")) {
            board = boardRepository.findByTitleLike(fkey, paging).getContent();
        } else if(String.valueOf(params.get("ftype")).equals("content")) {
            board = boardRepository.findByContentLike(fkey, paging).getContent();
        }

        return board;
    }

    @Override
    public int countBoard(Map<String, Object> params) {
        int result = 0;
        String fkey = "%" + params.get("fkey") + "%";
        String ftype = (String) params.get("ftype");
        System.out.println(ftype);

        if(String.valueOf(params.get("ftype")).equals("title")) {
            result = Math.toIntExact(boardRepository.countBnoByTitleLike(fkey));
        } else if(String.valueOf(params.get("ftype")).equals("content")){
            result = Math.toIntExact(boardRepository.countBnoByContentLike(fkey));
        }

        result = result/10;
        return result;
    }

    @Override
    public Board selectOneBoard(Integer bno) {
        boardRepository.countViewBoard((long) bno);
        return boardRepository.findById((long) bno).get();
    }
}
