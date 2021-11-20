package com.github.adminfaces.starter.infra.security;

import com.github.adminfaces.template.session.AdminSession;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.Utilisateur;
import com.github.adminfaces.starter.model.Utilisateur_;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.data.api.criteria.Criteria;
import org.apache.deltaspike.data.impl.criteria.QueryCriteria;

/**
 * Created by rmpestano on 12/20/14.
 *
 * This is just a login example.
 *
 * AdminSession uses isLoggedIn to determine if user must be redirect to login
 * page or not. By default AdminSession isLoggedIn always resolves to true so it
 * will not try to redirect user.
 *
 * If you already have your authorization mechanism which controls when user
 * must be redirect to initial page or logon you can skip this class.
 */
@Named
@SessionScoped
@Specializes
public class LogonMB extends AdminSession implements Serializable {

    private Utilisateur currentUser;
    private String email;
    private String password;
    private boolean remember;
    private String msg;
    
    @Inject
    EntityManager em;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void login() throws IOException {
        Criteria<Utilisateur,Utilisateur> criteria = new QueryCriteria<>(Utilisateur.class, Utilisateur.class, em);
        currentUser = criteria.eq(Utilisateur_.code, email)
                .eq(Utilisateur_.secret, password)
                .getOptionalResult();
        if (currentUser != null) {
            addDetailMessage("Connecté en tant que <b>" + currentUser.toString() + "</b>");
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect("index.xhtml");
        }else{
            currentUser = null;
            setMsg("User ou mot de passe incorrect.");
            Faces.redirect("login.xhtml");
        }
    }

    @Override
    public boolean isLoggedIn() {

        return currentUser != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utilisateur currentUser) {
        this.currentUser = currentUser;
    }
}
