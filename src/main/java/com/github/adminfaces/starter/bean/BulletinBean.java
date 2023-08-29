package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import static com.github.adminfaces.persistence.util.Messages.getMessage;
import static com.github.adminfaces.template.util.Assert.has;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.github.adminfaces.persistence.bean.CrudMB;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.service.BulletinService;
import com.github.adminfaces.template.exception.BusinessException;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class BulletinBean extends CrudMB<Bulletin> implements Serializable {

    @Inject
    BulletinService bulletinService;

    List<Bulletin> bulletins;

    @Inject
    public void initService() {
        setCrudService(bulletinService);
    }

    public void delete() {
        int numBulletins = 0;
        for (Bulletin selectedBulletin : selectionList) {
            numBulletins++;
            bulletinService.remove(selectedBulletin);
        }
        selectionList.clear();
        addDetailMessage(numBulletins + " bulletins deleted successfully!");
        clear();
    }

    public List<Bulletin> getBulletins() {
        bulletins = bulletinService.liste();
        return bulletins;
    }

    public void setBulletins(List<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }
}
