package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


//public interface BoardRepository extends JpaRepository<Board, Long> {
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {

    @Query("select max(b.bno) from Board b")
    Long find();

    // BoardRepository에서는 DML은 지원하지 않음
    // @Modifying, @Transactional을 추가하여 사용 가능
    @Modifying
    @Transactional
    @Query("update Board set views = views + 1 where bno = :bno")
    int countViewBoard(@Param("bno") Long bno);

    Page<Board> findByTitleLike(@Param("title") String title, Pageable pageable);
}
