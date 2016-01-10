/*
 * Projeto para o artigo "Extrair dados do cartão de cidadão com a linguagem de programação JAVA"
 * Para a comunidade e revista electrónica Portugal-a-Programar (http://www.portugal-a-programar.pt/)
 */
package PaPCC;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Cabral
 */
public class JMessageDialog {
    
    /**
     * Mostra uma janela com informação do erro
     * @param title título do erro
     * @param message mensagem do erro
     */
    public static void erroMsg(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Mostra uma janela com informação
     * @param title título da informação
     * @param message mensagem da informação
     */
     public static void infoMsg(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
