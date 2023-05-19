package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {
    // 메서드 쿼리 : find"엔티티 이름"All, find"엔티티 이름"By"컬럼 이름"

/*    @Query("from  Member ")
    List<Member> showall();*/

}
