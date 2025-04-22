/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominios;

/**
 *
 * @author CRISTHIAN
 */
public class AdministradorDTO {

    /**
     * @return the nombreAdmin
     */
    public String getNombreAdmin() {
        return nombreAdmin;
    }

    /**
     * @param nombreAdmin the nombreAdmin to set
     */
    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    private String nombreAdmin;
    private String email;
    
    
}
