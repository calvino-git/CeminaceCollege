package com.github.adminfaces.starter.infra.security;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.service.AnneeAcademiqueService;
import com.github.adminfaces.template.session.AdminSession;
import lombok.NoArgsConstructor;
import org.omnifaces.util.Faces;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Specializes;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import com.github.adminfaces.starter.model.Utilisateur;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

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
@Component
@SessionScope
@Specializes
@NoArgsConstructor
public class LogonMB extends AdminSession implements Serializable {

    private Utilisateur currentUser;
    private String email;
    private String password;
    private boolean remember;
    private String msg;

    
//    @Inject
//    EntityManager em;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public void login() throws IOException {
//        Criteria<Utilisateur,Utilisateur> criteria = new QueryCriteria<>(Utilisateur.class, Utilisateur.class, em);
//        currentUser = criteria.eq(Utilisateur_.code, email)
//                .eq(Utilisateur_.secret, password)
//                .getOptionalResult();
//        if (currentUser != null) {
//            addDetailMessage("Connecté en tant que <b>" + currentUser.toString() + "</b>");
//            Faces.getExternalContext().getFlash().setKeepMessages(true);
//            Faces.redirect("index.xhtml");
//        }else{
//            currentUser = null;
//            setMsg("User ou mot de passe incorrect.");
//            Faces.redirect("login.xhtml");
//        }
//    }
//
    @Override
    public boolean isLoggedIn() {

        return currentUser != null;
    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isRemember() {
//        return remember;
//    }
//
//    public void setRemember(boolean remember) {
//        this.remember = remember;
//    }
//
//    public Utilisateur getCurrentUser() {
//        return currentUser;
//    }

//    public void setCurrentUser(Utilisateur currentUser) {
//        this.currentUser = currentUser;
//    }
}
