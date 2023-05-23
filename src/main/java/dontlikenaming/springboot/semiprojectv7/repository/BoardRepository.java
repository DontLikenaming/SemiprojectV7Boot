package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


//public interface BoardRepository extends JpaRepository<Board, Long> {
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {

    // BoardRepository에서는 DML은 지원하지 않음
    // @Modifying, @Transactional을 추가하여 사용 가능
    @Modifying
    @Transactional
    @Query("update Board set views = views + 1 where bno = :bno")
    int countViewBoard(@Param("bno") Long bno);

    Long countBoardBy();

    Page<Board> findByTitleContains(Pageable pageable, @Param("title") String title);

    Page<Board> findByContentContains(Pageable pageable, @Param("content") String content);

    Page<Board> findByUserid(PageRequest paging, @Param("userid") String userid);

    Page<Board> findByTitleContainsOrContentContains(PageRequest paging, @Param("title") String title, @Param("content") String content);

    /*Long countBnoByTitleContains(@Param("title") String title);

    Long countBnoByContentContains(@Param("content") String content);

    Long countBnoByUserid(@Param("userid") String userid);

    Long countBnoByTitleContainsOrContentContains(@Param("title") String title, @Param("content") String content);*/
}
