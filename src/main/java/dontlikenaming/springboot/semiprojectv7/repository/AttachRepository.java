package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachRepository extends PagingAndSortingRepository<PdsAttach, Long> {
   PdsAttach findPdsattachByPno(@Param("pno") Integer pno);

   // 파일 확장자만 따로 조회해서 리스트에 저장
   @Query(value = "select ftype from PdsAttach", nativeQuery = true)
   List<String> findbyFtypes();

/*   String findFnameByPno(@Param("pno") Integer pno);*/
}
