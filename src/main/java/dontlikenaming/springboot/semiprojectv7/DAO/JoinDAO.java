package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.model.Zipcode;

import java.util.List;

public interface JoinDAO {
    List<Zipcode> selectZipcode(String dong);
    int insertMember(Member m);
    int selectOneUserid(String uid);
    int selectOneMember(Member m);
}
