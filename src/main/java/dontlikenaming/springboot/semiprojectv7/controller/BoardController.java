package dontlikenaming.springboot.semiprojectv7.controller;


import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired private BoardService bdsrv;

    @GetMapping(value = "/list")
    public ModelAndView list(Integer page){
        int cntpg = bdsrv.countBoard();

        if((page==null)||(page<=0)){page = 1;}
        else if((page>cntpg)){page = cntpg;}

        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/list");
        mv.addObject("bdlist", bdsrv.readBoard(page));
        mv.addObject("page", page);
        mv.addObject("stpg", (page-1)/10*10+1);
        mv.addObject("cntpg", cntpg);

        return mv;
    }
    
    @GetMapping(value = "/find")    // 검색 처리
    public ModelAndView find(Integer page, String ftype, String fkey){
        ModelAndView mv = new ModelAndView();
        if((page==null)||(page<=0)){page = 1;}
        int cntpg = bdsrv.countBoard(ftype, fkey);
        if(page>cntpg){page = cntpg;}

        System.out.println("page : "+page);
        System.out.println("cntpg : "+cntpg);
        mv.addObject("bdlist", bdsrv.readBoard(page, ftype, fkey));
        mv.addObject("page", page);
        mv.addObject("stpg", ((page-1)/10)*10+1);
        mv.addObject("cntpg", cntpg);
        mv.setViewName("board/list");
        return mv;
    }

    @GetMapping(value = "/write")
    public String write(){
        return "board/write";
    }

    @PostMapping(value = "/write")
    public String writeok(Board bd, String grecaptcha){
        String view = "error";
        grecaptcha = null;

        if(bdsrv.newBoard(bd)){ view = "redirect:/board/list"; }

        return view;
    }

    @GetMapping(value = "/view")
    public ModelAndView view(int bno){
        int allviews = bdsrv.countAllBoard();
        if(bno==0){bno = 1;}
        else if(bno>allviews){bno = allviews;}

        ModelAndView mv = new ModelAndView();
        mv.addObject("bd", bdsrv.readOneBoard(bno));
        mv.setViewName("board/view");

        return mv;
    }
}
