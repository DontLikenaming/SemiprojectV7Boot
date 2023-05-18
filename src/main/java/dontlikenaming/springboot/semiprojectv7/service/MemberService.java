package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.model.Member;

import javax.servlet.http.HttpSession;

public interface MemberService {
    boolean checkLogin(Member m, HttpSession sess);
}
