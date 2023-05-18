package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{

    @Override
    public List<Board> selectBoard(int stdno) {
        return null;
    }

    @Override
    public int selectBoard() {
        return 0;
    }

    @Override
    public int countBoard() {
        return 0;
    }

    @Override
    public int insertBoard(Board bd) {
        return 0;
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
        return null;
    }
}
