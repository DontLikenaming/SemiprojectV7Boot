package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface PdsRepository extends PagingAndSortingRepository<Pds, Long> {

    @Modifying
    @Transactional
    @Query("update Pds set views = views + 1 where pno = :pno")
    int countViewPds(@Param("pno") Long pno);

    Long countPdsBy();

    Page<Pds> findByTitleContains(Pageable pageable, @Param("title") String title);

    Page<Pds> findByContentContains(Pageable pageable, @Param("content") String content);

    Page<Pds> findByUserid(PageRequest paging, @Param("userid") String userid);

    Page<Pds> findByTitleContainsOrContentContains(PageRequest paging, @Param("title") String title, @Param("content") String content);
}
