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
        return 0;
    }

    @Override
    public int insertBoard(Board bd) {
        return Math.toIntExact(boardRepository.save(bd).getBno());
    }

    @Override
    public List<Board> selectBoard(Map<String, Object> params) {
        List<Board> board = new ArrayList<>();
        PageRequest paging = PageRequest.of(1,10, Sort.by("bno").descending());
        if(params.containsKey("ftype"))board = boardRepository.findByTitleLike(String.valueOf(params.get("fkey")), paging).getContent();
        return board;
    }

    @Override
    public int countBoard(Map<String, Object> params) {
        return 0;
    }

    @Override
    public Board selectOneBoard(Integer bno) {
        boardRepository.countViewBoard((long) bno);
        return boardRepository.findById((long) bno).get();
    }
}
