package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.DAO.MemberDAO;
import dontlikenaming.springboot.semiprojectv7.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("msrv")
public class MemberServiceImpl implements MemberService{
    @Autowired private MemberDAO mdao;

    @Override
    public boolean checkLogin(Member m, HttpSession sess) {
        // 로그인 가능 여부 확인
        boolean isLogin = false;

        if(mdao.selectLogin(m)>0){
            // 로그인 가능하면 세션 변수에 아이디를 저장
            sess.setAttribute("UID", m.getUserid());
            isLogin = true;
        }

        return isLogin;
    }
}
