package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select count(b.bno) from Board b")
    Long find();

    // BoardRepository에서는 DML은 지원하지 않음
    // @Modifying, @Transactional을 추가하여 사용 가능
    @Modifying
    @Transactional
    @Query("update Board set views = views + 1 where bno = :bno")
    void countViewBoard(@Param("bno") Long bno);

}
