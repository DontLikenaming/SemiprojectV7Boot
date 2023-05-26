package dontlikenaming.springboot.semiprojectv7.controller;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import dontlikenaming.springboot.semiprojectv7.model.PdsReply;
import dontlikenaming.springboot.semiprojectv7.service.PdsService;
import dontlikenaming.springboot.semiprojectv7.utils.PdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/pds")
public class PdsController {
    @Autowired private PdsService pdssrv;

    @GetMapping(value = "/list")
    public ModelAndView list(Integer page){
        if((page==null)||(page<=0)){page = 1;}
        Map<String, Object> pds = pdssrv.readPds(page);
        List<String> ftypes = pdssrv.readFtype();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("pds/list");
        mv.addObject("pdslist", pds.get("pdslist"));
        mv.addObject("ftypes", ftypes);
        mv.addObject("page", page);
        mv.addObject("stpg", (page-1)/10*10+1);
        mv.addObject("cntpg", pds.get("cntpg"));

        return mv;
    }

    @GetMapping(value = "/write")
    public String write(Model m){
        m.addAttribute("pds", new Pds());
        return "pds/write";
    }

    @PostMapping("/write")
    public String writeok(@Valid Pds pds, BindingResult br, MultipartFile attach){
        String view = "pds/write";

        if(!br.hasErrors()){
            Map<String, Object> pinfo = pdssrv.newPds(pds);

            if (!attach.isEmpty()) { // 첨부파일이 존재한다면
                pdssrv.newPdsAttach(attach, pinfo);
            }

            view = "redirect:/pds/list";
        }

        return view;
    }

    @GetMapping(value = "/view")
    public String view(Integer pno, Model m){
        if((pno==null)||(pno<=0)){pno = 1;}

        m.addAttribute("pds", pdssrv.readOnePds(pno));
        m.addAttribute("attach", pdssrv.readOneAttach(pno));
        m.addAttribute("rplist", pdssrv.readPdsReply(pno));

        return "pds/view";
    }

    @GetMapping("/down")
    public ResponseEntity<Resource> down(int pno) throws IOException {

        // 업로드 파일의 uuid와 파일명 알아내기
        String fname = pdssrv.readOneAttach(pno).getFname();
        String uuid = pdssrv.readOnePds(pno).getUuid();

        // 알아낼 uuid와 파일명을 이용해서 header와 리소스 객체 생성
        HttpHeaders header = pdssrv.getHeader(fname);
        UrlResource resource = pdssrv.getResource(fname, uuid);

        return ResponseEntity.ok().headers(header).body(resource);
    }

    @PostMapping("replyok")
    public String replyok(@Valid PdsReply pry, BindingResult br){
        String view = "redirect:/pds/view?pno="+pry.getPno();

        if(!br.hasErrors()){
            pdssrv.newPdsReply(pry);
        }
        return view;
    }

    @PostMapping("rreplyok")
    public String rreplyok(@Valid PdsReply pry, BindingResult br){
        String view;

        if(!br.hasErrors()&&pdssrv.newPdsRreply(pry)) {
            view = "redirect:/pds/view?pno="+pry.getPno();
        } else {
            view = "error";
        }

        return view;
    }
}
