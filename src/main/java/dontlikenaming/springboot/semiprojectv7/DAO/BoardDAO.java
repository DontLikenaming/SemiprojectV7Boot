package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    List<Board> selectBoard(int cpage);
    int selectBoard();
    int countBoard();
    int insertBoard(Board bd);
    List<Board> selectBoard(Map<String, Object> params);
    int countBoard(Map<String, Object> params);

    Board selectOneBoard(Integer bno);
}
