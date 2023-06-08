package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.GalAttach;
import dontlikenaming.springboot.semiprojectv7.model.Gallery;
import dontlikenaming.springboot.semiprojectv7.repository.GalattachRepository;
import dontlikenaming.springboot.semiprojectv7.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("galdao")
public class GalleryDAOImpl implements GalleryDAO {

    // 생성자를 이용한 스프링 빈 주입
    private final GalleryRepository galleryRepository;
    private final GalattachRepository galattachRepository;

    @Autowired
    public GalleryDAOImpl(GalleryRepository galleryRepository, GalattachRepository galattachRepository) {
        this.galleryRepository = galleryRepository;
        this.galattachRepository = galattachRepository;
    }


    @Override
    public int insertGal(Gallery gallery) {
        return Math.toIntExact(galleryRepository.save(gallery).getGno());
    }

    @Override
    public int insertGalAttach(GalAttach ga) {
        System.out.println(ga);
        return Math.toIntExact(galattachRepository.save(ga).getGano());
    }

    @Override
    public Map<String, Object> selectGallery(Integer page) {
        PageRequest paging = PageRequest.of(page-1,10, Sort.by("gno").descending());

        Map<String, Object> gals = new HashMap<>();
        // Gallery와 GalAttach를 조인해서 리스트로 가져옴
        gals.put("gallist", galattachRepository.findAllBy(paging).getContent());
        gals.put("cntpg", galattachRepository.findAllBy(paging).getTotalPages());

        return gals;
    }

}
