package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachRepository extends PagingAndSortingRepository<PdsAttach, Long> {

    List<PdsAttach> findPdsattachBypno(@Param("pno") Long pno);
}
