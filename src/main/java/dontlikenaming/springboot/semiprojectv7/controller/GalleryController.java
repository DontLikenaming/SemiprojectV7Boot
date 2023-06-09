package dontlikenaming.springboot.semiprojectv7.controller;

import dontlikenaming.springboot.semiprojectv7.model.Gallery;
import dontlikenaming.springboot.semiprojectv7.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
    @Autowired
    private GalleryService galsrv;

    @GetMapping(value = "/list")
    public String list(){
        return "gallery/list";
    }

    @GetMapping(value = "/write")
    public String write(Model m){
        m.addAttribute("gallery", new Gallery());
        return "gallery/write";
    }

    @PostMapping("/write")
    public String writeok(@Valid Gallery gallery, BindingResult br, List<MultipartFile> attachs){
        String view = "error";

        if(!br.hasErrors()){

            if (!attachs.isEmpty()) { // 첨부파일이 존재한다면
                Map<String, Object> ginfo = galsrv.newGallery(gallery);
                galsrv.newGalAttach(attachs, ginfo);
            }

            view = "redirect:/gallery/list";
        }

        return view;
    }
}
