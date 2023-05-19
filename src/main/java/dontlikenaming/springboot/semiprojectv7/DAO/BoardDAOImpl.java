package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Page<Board> selectBoard(int cpage) {
        PageRequest pageRequest = PageRequest.of(cpage-1,10);
        return boardRepository.findAll(pageRequest);
    }

    @Override
    public int selectBoard() {
        System.out.println(boardRepository.find());
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
        return null;
    }

    @Override
    public int countBoard(Map<String, Object> params) {
        return 0;
    }

    @Override
    public Board selectOneBoard(Integer bno) {
        boardRepository.countViewBoard((long)bno);
        return boardRepository.findById((long)bno).get();
    }
}
