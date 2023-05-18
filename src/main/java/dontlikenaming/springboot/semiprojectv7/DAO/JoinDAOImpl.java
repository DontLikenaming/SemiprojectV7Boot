package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jndoa")
public class JoinDAOImpl implements JoinDAO{
    @Autowired private SqlSession sqlSession;

    @Override
    public List<Zipcode> selectZipcode(String dong) {
        return sqlSession.selectList("join.findZipcode", dong);
    }

    @Override
    public int insertMember(Member m) {
        return sqlSession.insert("join.insertMember", m);
    }

    @Override
    public int selectOneUserid(String uid) {
        return sqlSession.selectOne("join.selectOneUid", uid);
    }

    @Override
    public int selectOneMember(Member m) {
        return 0;
    }
}
