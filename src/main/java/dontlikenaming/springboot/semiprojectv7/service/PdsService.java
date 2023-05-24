package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.model.Board;
import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PdsService {
    Map<String, Object> readPds(Integer cpage);

    Map<String, Object> readPds(Integer cpage, String ftype, String fkey);

    int newPds(Pds pds);

    Pds readOnePds(Integer pno);

    boolean newPdsAttach(MultipartFile attach, int pno);

    List<PdsAttach> selectAttech(Integer pno);
}