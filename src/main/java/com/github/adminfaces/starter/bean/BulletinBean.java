package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.service.BulletinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rmpestano on 12/02/17.
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinBean {

    private final BulletinService bulletinService;

    public BulletinBean(BulletinService bulletinService){
        this.bulletinService = bulletinService;
    }
//
//    List<Bulletin> bulletins;
//
//    @Inject
//    public void initService() {
//        setCrudService(bulletinService);
//    }
//
//    public void delete() {
//        int numBulletins = 0;
//        for (Bulletin selectedBulletin : selectionList) {
//            numBulletins++;
//            bulletinService.remove(selectedBulletin);
//        }
//        selectionList.clear();
//        addDetailMessage(numBulletins + " bulletins deleted successfully!");
//        clear();
//    }
//
//    public List<Bulletin> getBulletins() {
//        bulletins = bulletinService.liste();
//        return bulletins;
//    }
//
//    public void setBulletins(List<Bulletin> bulletins) {
//        this.bulletins = bulletins;
//    }
}
