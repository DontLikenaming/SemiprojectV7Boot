package dontlikenaming.springboot.semiprojectv7;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import dontlikenaming.springboot.semiprojectv7.repository.BoardRepository;
import dontlikenaming.springboot.semiprojectv7.repository.ZipcodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

}
