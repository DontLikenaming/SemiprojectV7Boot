package dontlikenaming.springboot.semiprojectv7.controller;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.service.PdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/pds")
public class PdsController {
    @Autowired private PdsService pdssrv;

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

        int pno = pdssrv.newPds(pds);
        if(pdssrv.newPdsAttach(attach, pno))view = "redirect:/pds/list";

        return view;
    }
}
