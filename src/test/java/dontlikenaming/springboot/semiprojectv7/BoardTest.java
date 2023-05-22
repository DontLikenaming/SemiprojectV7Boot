package dontlikenaming.springboot.semiprojectv7;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("board save")
    public void saveBoard(){
        Board b = new Board(null, "Test01", "qwerty", null, null, "테스트", null);
        boardRepository.save(b);
    }

    @Test
    @DisplayName("board read")
    public void readBoard(){
        int bno = 4;
        boardRepository.countViewBoard((long)bno);
    }

    @Test
    @DisplayName("board select")
    public void selectBoard(){
        Map<String, Object> params = new HashMap<>();
        params.put("stdno", 0);
        params.put("ftype", "title");
        params.put("fkey", "Test");

        String fkey = params.get("fkey").toString();
        //PageRequest paging = PageRequest.of(0,10);
        //System.out.println(boardRepository.findByTitleLike(fkey, paging).getContent());
        System.out.println(Math.toIntExact(boardRepository.countBnoByTitleContains(fkey)));
    }

    @Test
    @DisplayName("board select+count")
    public void selectcountBoard(){
        int result;
        String fkey = '%' + "qwerty" + '%';
        fkey = fkey.replace("%","");
        result = Math.toIntExact(boardRepository.countBnoByUserid(fkey));
        System.out.println(result);
    }

}
