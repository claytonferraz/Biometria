/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.telas.cadastrardigital;

import java.io.File;

/**
 *
 * @author clayton
 */
public class TemplateFileFilter extends javax.swing.filechooser.FileFilter{
    @Override
        public boolean accept(File f) {
            return f.getName().endsWith(".fpt");
        }

        @Override
        public String getDescription() {
            return "Arquivo de Impressão Digital (*.fpt)";
        }
    
}
