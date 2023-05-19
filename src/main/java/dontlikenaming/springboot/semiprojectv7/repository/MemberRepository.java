package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(m.userid) from Member m where m.userid = :userid")
    int findMemberByUserid(@Param("userid")String userid);

    @Query("select count(m.userid) from Member m where m.userid = :userid and m.passwd = :passwd")
    int findMemberByUseridAndPasswd(@Param("userid")String userid, @Param("passwd")String passwd);

}
