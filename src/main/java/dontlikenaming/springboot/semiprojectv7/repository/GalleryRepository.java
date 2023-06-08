package dontlikenaming.springboot.semiprojectv7.repository;

import dontlikenaming.springboot.semiprojectv7.model.Gallery;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface GalleryRepository extends PagingAndSortingRepository<Gallery, Long> {

    /*@Modifying
    @Transactional
    @Query("update Gallery set views = views + 1 where gno = :gno")
    int countViewGallery(@Param("gno") Long gno);

    Long countPdsBy();

    Page<Pds> findByTitleContains(Pageable pageable, @Param("title") String title);

    Page<Pds> findByContentContains(Pageable pageable, @Param("content") String content);

    Page<Pds> findByUserid(PageRequest paging, @Param("userid") String userid);

    Page<Pds> findByTitleContainsOrContentContains(PageRequest paging, @Param("title") String title, @Param("content") String content);

    Pds findPdsByPno(long pno);

    @Modifying
    @Transactional
    @Query("update PdsAttach set fdown = fdown + 1 where fname = :fname")
    int countFdownPds(@Param("fname") String fname);*/



}
