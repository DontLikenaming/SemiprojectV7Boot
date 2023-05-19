package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    MemberRepository memberRepository;

    @Override
    public int selectLogin(Member m) {
        int result = 0;

        if(memberRepository.findByUseridAndPasswd(m.getUserid(), m.getPasswd())!=null){
            result = 1;
        }
        return result;
    }
}
