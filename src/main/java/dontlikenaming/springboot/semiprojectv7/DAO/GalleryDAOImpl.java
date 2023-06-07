package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.GalAttach;
import dontlikenaming.springboot.semiprojectv7.model.Gallery;
import dontlikenaming.springboot.semiprojectv7.repository.AttachRepository;
import dontlikenaming.springboot.semiprojectv7.repository.PdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("galdao")
public class GalleryDAOImpl implements GalleryDAO {

    @Autowired PdsRepository galRepository;
    @Autowired AttachRepository attachRepository;

    @Override
    public int insertGalAttach(GalAttach ga) {
        return 0;
    }

    @Override
    public int insertGal(Gallery gallery) {
        return 0;
    }
}
