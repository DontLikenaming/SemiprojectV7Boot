package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import dontlikenaming.springboot.semiprojectv7.repository.MemberRepository;
import dontlikenaming.springboot.semiprojectv7.repository.ZipcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jndoa")
public class JoinDAOImpl implements JoinDAO{

    @Autowired
    ZipcodeRepository zipcodeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<Zipcode> selectZipcode(String dong) {
        return zipcodeRepository.findZipcodeByDong(dong);
    }

    @Override
    public int insertMember(Member m) {
        int result = -1;

        m = memberRepository.save(m);
        if(m != null) { result = Math.toIntExact(m.getMbno()); }
        return result;
    }

    @Override
    public int selectOneUserid(String userid) {
        int result;
        result = memberRepository.findMemberByUserid(userid);
        return result;
    }

    @Override
    public int selectOneMember(Member m) {
        return 0;
    }
}
