package dontlikenaming.springboot.semiprojectv7.pilot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/pilot")
public class PilotController {

    @GetMapping("/write")   // 입력폼
    public String write(){
        return "pilot/write";
    }

    @PostMapping("/write")  // 전송된 데이터 처리
    public String writeok(String title, String content, MultipartFile attach, Model m) throws IOException {

        // 일반 폼 요소 처리
        m.addAttribute("title", title);
        m.addAttribute("content", content);

        // 멀티파트 폼 요소 처리
        if(attach.isEmpty())m.addAttribute("attach", "첨부 파일이 없습니다.");
        else {
            // 업로드한 파일 이름 출력
            m.addAttribute("filename", attach.getOriginalFilename());
            // 업로드한 파일 종류 출력
            m.addAttribute("filetype", attach.getContentType());
            // 업로드한 파일 크기 출력
            m.addAttribute("filesize", attach.getSize()/1024);

            // 파일명 중복을 막기 위해 유니크한 값 생성 1
            UUID uuid = UUID.randomUUID();
            String fname = attach.getOriginalFilename();
            String fnames[] = fname.split("[.]");
            fname = fnames[0] + uuid + "." + fnames[1];
            m.addAttribute("uuid", uuid);

            // 파일명 중복을 막기 위해 유니크한 값 생성 2


            // 업로드 한 파일 저장하기
            attach.transferTo(new File("C:/Java/bootUpload/" + fname));
        }

        return "pilot/list";
    }

    @GetMapping("/list")
    public String list(){
        return "pilot/list";
    }
}