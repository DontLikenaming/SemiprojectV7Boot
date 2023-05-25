package dontlikenaming.springboot.semiprojectv7.controller;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
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
import org.springframework.web.util.UriUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Controller
@RequestMapping(value = "/pds")
public class PdsController {
    @Autowired private PdsService pdssrv;
    @Autowired private PdsUtils pdsUtils;

    @GetMapping(value = "/list")
    public ModelAndView list(Integer page){
        if((page==null)||(page<=0)){page = 1;}
        Map<String, Object> pds = pdssrv.readPds(page);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("pds/list");
        mv.addObject("pdslist", pds.get("pdslist"));
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

        return "pds/view";
    }

    @GetMapping("/down")
    public ResponseEntity<Resource> down(int pno) throws IOException {
/*        Map<String, Object> pds = pdssrv.downAttach(pno);
        String fname = (String) pds.get("fname");
        String uuid = (String) pds.get("uuid");
        String savePath = "C:/Java/bootUpload/";

        String filename = pdsUtils.makeFname(fname, uuid);

        UrlResource resource = new UrlResource("file:"+(savePath+filename));

        filename = UriUtils.encode(fname, StandardCharsets.UTF_8);

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", Files.probeContentType(Paths.get(savePath+filename)));
        header.add("Content-Disposition", "attachment; filename=" + filename);*/

        // 업로드 파일의 uuid와 파일명 알아내기
        String fname = pdssrv.readOneAttach(pno).getFname();
        String uuid = pdssrv.readOnePds(pno).getUuid();

        // 알아낼 uuid와 파일명을 이용해서 header와 리소스 객체 생성
        HttpHeaders header = pdssrv.getHeader(fname);
        UrlResource resource = pdssrv.getResource(fname, uuid);

        return ResponseEntity.ok().headers(header).body(resource);
    }
}
