package dontlikenaming.springboot.semiprojectv7.controller;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/join")
public class JoinController {
    @Autowired private JoinService jnsrv;

    @GetMapping(value = "/agree")
    public String agree(){
        return "join/agree";
    }

    @GetMapping(value = "/checkme")
    public String checkme(){
        return "join/checkme";
    }

    @PostMapping(value = "/joinme")
    public ModelAndView joinme(Member mb){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("join/joinme");
        mv.addObject("mb", mb);
        return mv;
    }

    @PostMapping(value = "/joinok")
    public String joinok(Member m, String grecaptcha){
        String view = "error";
        grecaptcha = null;

        if(jnsrv.newMember(m)){
            view = "join/joinok";
        }

        return view;
    }

    // 우편번호 검색
    // /join/zipcode?dong="동 이름"
    // 검색 결과는 뷰 페이지를 따로 불러오지 않고
    // 응답으로 출력 : RESTful 방식
    // 서블릿에서 제공하는 HttpServletResponse 객체를 이용하면
    // spring의 view resolver 없이 바로 응답으로 출력할 수 있음
    @ResponseBody
    @GetMapping("/zipcode")
    public void zipcode(String dong, HttpServletResponse res) {
        try {
            // 응답 유형은 JSON으로 설정, 인코딩은 utf-8로 해야 깨지지 않음
            res.setContentType("application/json; charset=utf-8");
            // 검색된 우편번호 결과를 view 없이 바로 응답으로 출력
            res.getWriter().println(jnsrv.findZipcode(dong));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    // 아이디 사용 가능 여부 검사
    // /join/checkuid?uid='아이디'
    // 사용 가능 0
    // 사용 불가 1
    @ResponseBody
    @GetMapping("/checkuid")
    public void checkuid(String uid, HttpServletResponse res) {
        try {
            // 아이디 사용 여부를 뷰 없이 바로 응답으로 출력
            res.getWriter().println(jnsrv.checkUserid(uid));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
