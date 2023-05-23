package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    Map<String, Object> selectBoard(int cpage);

    int insertBoard(Board bd);

    Map<String, Object> selectBoard(Map<String, Object> params);

    Board selectOneBoard(Integer bno);
}
