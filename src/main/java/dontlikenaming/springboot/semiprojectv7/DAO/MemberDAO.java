package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;

public interface MemberDAO {
    int selectLogin(Member m);
}
