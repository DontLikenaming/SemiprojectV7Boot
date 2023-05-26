package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.PdsReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PdsReplyRepository extends PagingAndSortingRepository<PdsReply, Long> {

    //@Query("from PdsReply where pno = :pno order by refno, regdate asc")
    List<PdsReply> findByPnoOrderByRefnoAscRegdateAsc(Integer pno);
}
