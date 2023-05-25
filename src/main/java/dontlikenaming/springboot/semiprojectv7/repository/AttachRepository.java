package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AttachRepository extends PagingAndSortingRepository<PdsAttach, Long> {
   PdsAttach findPdsattachByPno(@Param("pno") Integer pno);

/*   String findFnameByPno(@Param("pno") Integer pno);*/
}
