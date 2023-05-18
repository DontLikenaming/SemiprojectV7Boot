package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{
    @Autowired private SqlSession sqlSession;

    @Override
    public int selectLogin(Member m) {
        return sqlSession.selectOne("member.selectLogin",m);
    }
}
