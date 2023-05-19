package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(m.userid) from Member m where m.userid = :userid")
    int findByUserid(@Param("userid")String userid);


    Member findByUseridAndPasswd(@Param("userid")String userid, @Param("passwd")String passwd);
}
