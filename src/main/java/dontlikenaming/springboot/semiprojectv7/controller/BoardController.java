package dontlikenaming.springboot.semiprojectv7.controller;


import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired private BoardService bdsrv;

    @GetMapping(value = "/list")
    public ModelAndView list(Integer page){
        if((page==null)||(page<=0)){page = 1;}
        Map<String, Object> bds = bdsrv.readBoard(page);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/list");
        mv.addObject("bdlist", bds.get("bdlist"));
        mv.addObject("page", page);
        mv.addObject("stpg", (page-1)/10*10+1);
        mv.addObject("cntpg", bds.get("cntpg"));

        return mv;
    }
    
    @GetMapping(value = "/find")    // 검색 처리
    public ModelAndView find(Integer page, String ftype, String fkey){
        ModelAndView mv = new ModelAndView();
        if((page==null)||(page<=0)){page = 1;}
        Map<String, Object> bds = bdsrv.readBoard(page, ftype, fkey);

        mv.addObject("bdlist", bds.get("bdlist"));
        mv.addObject("page", page);
        mv.addObject("stpg", ((page-1)/10)*10+1);
        mv.addObject("cntpg", bds.get("cntpg"));
        mv.setViewName("board/list");
        return mv;
    }

    @GetMapping(value = "/write")
    public String write(Model m){
        // validation을 위한 첫번째 코드
        m.addAttribute("board", new Board());
        return "board/write";
    }

    @PostMapping(value = "/write")
    public String writeok(@Valid Board board, BindingResult br){
        String view = "redirect:/board/list";

        if(br.hasErrors()) view = "board/write";  // 유효성 검사 실패 시, 작성 페이지로 되돌아감
        else bdsrv.newBoard(board);

        return view;
    }

    @GetMapping(value = "/view")
    public ModelAndView view(Integer bno){
        if((bno==null)||(bno<=0)){bno = 1;}

        ModelAndView mv = new ModelAndView();
        mv.addObject("bd", bdsrv.readOneBoard(bno));
        mv.setViewName("board/view");

        return mv;
    }
}
