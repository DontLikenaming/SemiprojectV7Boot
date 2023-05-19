package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    MemberRepository memberRepository;

    @Override
    public int selectLogin(Member m) {
        int result = 0;
        String userid = m.getUserid();
        String passwd = m.getPasswd();
        if(memberRepository.findMemberByUseridAndPasswd(userid, passwd)>0){
            result = 1;
        }
        return result;
    }
}
