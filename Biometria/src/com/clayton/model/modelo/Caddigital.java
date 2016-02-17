/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.model.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Clayton Ferraz
 */
@Entity
@Table(catalog = "biometria", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caddigital.findAll", query = "SELECT c FROM Caddigital c"),
    @NamedQuery(name = "Caddigital.findById", query = "SELECT c FROM Caddigital c WHERE c.id = :id"),
    @NamedQuery(name = "Caddigital.findByAtivo", query = "SELECT c FROM Caddigital c WHERE c.ativo = :ativo")})
public class Caddigital implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Lob
    private byte[] anelardir;
    @Lob
    private byte[] anelaresq;
    private Boolean ativo;
    @Lob
    private byte[] indicadordir;
    @Lob
    private byte[] indicadoresq;
    @Lob
    private byte[] mediodir;
    @Lob
    private byte[] medioesq;
    @Lob
    private byte[] minidir;
    @Lob
    private byte[] miniesq;
    @Lob
    private byte[] polegardir;
    @Lob
    private byte[] polegaresq;
    @JoinColumn(name = "idpessoa_iduser", referencedColumnName = "iduser")
    @ManyToOne
    private Cadpessoa idpessoaIduser;

    public Caddigital() {
    }

    public Caddigital(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getAnelardir() {
        return anelardir;
    }

    public void setAnelardir(byte[] anelardir) {
        this.anelardir = anelardir;
    }

    public byte[] getAnelaresq() {
        return anelaresq;
    }

    public void setAnelaresq(byte[] anelaresq) {
        this.anelaresq = anelaresq;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public byte[] getIndicadordir() {
        return indicadordir;
    }

    public void setIndicadordir(byte[] indicadordir) {
        this.indicadordir = indicadordir;
    }

    public byte[] getIndicadoresq() {
        return indicadoresq;
    }

    public void setIndicadoresq(byte[] indicadoresq) {
        this.indicadoresq = indicadoresq;
    }

    public byte[] getMediodir() {
        return mediodir;
    }

    public void setMediodir(byte[] mediodir) {
        this.mediodir = mediodir;
    }

    public byte[] getMedioesq() {
        return medioesq;
    }

    public void setMedioesq(byte[] medioesq) {
        this.medioesq = medioesq;
    }

    public byte[] getMinidir() {
        return minidir;
    }

    public void setMinidir(byte[] minidir) {
        this.minidir = minidir;
    }

    public byte[] getMiniesq() {
        return miniesq;
    }

    public void setMiniesq(byte[] miniesq) {
        this.miniesq = miniesq;
    }

    public byte[] getPolegardir() {
        return polegardir;
    }

    public void setPolegardir(byte[] polegardir) {
        this.polegardir = polegardir;
    }

    public byte[] getPolegaresq() {
        return polegaresq;
    }

    public void setPolegaresq(byte[] polegaresq) {
        this.polegaresq = polegaresq;
    }

    public Cadpessoa getIdpessoaIduser() {
        return idpessoaIduser;
    }

    public void setIdpessoaIduser(Cadpessoa idpessoaIduser) {
        this.idpessoaIduser = idpessoaIduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caddigital)) {
            return false;
        }
        Caddigital other = (Caddigital) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clayton.model.modelo.Caddigital[ id=" + id + " ]";
    }
    
}
