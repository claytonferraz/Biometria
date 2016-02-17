/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.model.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Clayton Ferraz
 */
@Entity
@Table(catalog = "biometria", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cadpessoa.findAll", query = "SELECT c FROM Cadpessoa c"),
    @NamedQuery(name = "Cadpessoa.findByIduser", query = "SELECT c FROM Cadpessoa c WHERE c.iduser = :iduser"),
    @NamedQuery(name = "Cadpessoa.findByLogin", query = "SELECT c FROM Cadpessoa c WHERE c.login = :login")})
public class Cadpessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer iduser;
    @Column(length = 255)
    private String login;
    @OneToMany(mappedBy = "idpessoaIduser")
    private Collection<Caddigital> caddigitalCollection;

    public Cadpessoa() {
    }

    public Cadpessoa(Integer iduser) {
        this.iduser = iduser;
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

    @XmlTransient
    public Collection<Caddigital> getCaddigitalCollection() {
        return caddigitalCollection;
    }

    public void setCaddigitalCollection(Collection<Caddigital> caddigitalCollection) {
        this.caddigitalCollection = caddigitalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cadpessoa)) {
            return false;
        }
        Cadpessoa other = (Cadpessoa) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clayton.model.modelo.Cadpessoa[ iduser=" + iduser + " ]";
    }
    
}
