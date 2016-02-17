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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author clayton
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CadDigital")
public class CadDigital implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte[] polegardir;
    private byte[] polegaresq;
    private byte[] indicadordir;
    private byte[] indicadoresq;
    private byte[] mediodir;
    private byte[] medioesq;
    private byte[] anelardir;
    private byte[] anelaresq;
    private byte[] minidir;
    private byte[] miniesq;
    private boolean ativo;
    
    @ManyToOne()
    private CadPessoa idpessoa;

    public CadDigital() {
    }

    public CadPessoa getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(CadPessoa idpessoa) {
        this.idpessoa = idpessoa;
    }

    public CadDigital(Integer id, boolean ativo, CadPessoa idpessoa) {
        this.id = id;
        this.ativo = ativo;
        this.idpessoa = idpessoa;
    }

    public CadDigital(CadPessoa idpessoa) {
        this.idpessoa = idpessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
    

    @Override
    public String toString() {
        return "Digital{" + "id=" + id + ", IDPessoa=" + idpessoa + '}';
    }

}
