/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author clayton
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CadPessoa")
public class CadPessoa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;
    private String login;

    public CadPessoa(String login) {
        this.login = login;
       
    }

    public CadPessoa(Integer iduser, String login) {
        this.iduser = iduser;
        this.login = login;
        
    }

    public CadPessoa() {
    }

     
    

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    
    
    
}
