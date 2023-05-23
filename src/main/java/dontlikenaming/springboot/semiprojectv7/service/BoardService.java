package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    Map<String, Object> readBoard(Integer cpage);

    Map<String, Object> readBoard(Integer cpage, String ftype, String fkey);

    boolean newBoard(Board bd);

    Board readOneBoard(Integer bno);

/*    int countBoard(String ftype, String fkey);*/
}
