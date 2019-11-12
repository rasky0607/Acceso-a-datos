/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej1;

import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class Texto implements Serializable{
     byte[]_bloqueTexto;
    public Texto(byte []bloqTex)
    {
        _bloqueTexto=bloqTex;
    }
}
