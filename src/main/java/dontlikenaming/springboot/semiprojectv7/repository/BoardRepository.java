package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select count(b.bno) from Board b")
    Long find();


}
