package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(m.userid) from Member m where m.userid = :userid")
    int findByUserid(@Param("userid") String userid);

    // 로그인 처리 1
    // Member findByUseridAndPasswd(@Param("userid")String userid, @Param("passwd")String passwd);

    // 로그인 처리 2
    int countByUseridAndPasswd(String userid, String passwd);
}
