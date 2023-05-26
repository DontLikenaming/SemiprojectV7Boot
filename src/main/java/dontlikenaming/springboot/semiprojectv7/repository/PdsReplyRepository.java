package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.PdsReply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PdsReplyRepository extends PagingAndSortingRepository<PdsReply, Long> {

    //@Query("from PdsReply where pno = :pno order by refno, regdate asc")
    List<PdsReply> findByPnoOrderByRefnoAscRegdateAsc(Integer pno);

    @Modifying
    @Transactional
    @Query("update PdsReply set refno = :rpno where rpno = :rpno")
    void updateRefno(@Param("rpno") Integer rpno);
}
