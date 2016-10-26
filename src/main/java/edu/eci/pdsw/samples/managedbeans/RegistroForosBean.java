/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Date;
import static java.util.Collections.list;
import java.util.Set;
/**
 *
 * @author hcadavid
 */
@ManagedBean(name = "registroForoBean")
@SessionScoped
public class RegistroForosBean implements Serializable {

    private String contenidoActual, emailActual, nombreActual;
    private ServiciosForo sp = ServiciosForo.getInstance();
    private EntradaForo selecc;
    private String autorUsuario; 
    private String comenta;
    private String tituloIni;
    
    
    public void adicionar() throws ExcepcionServiciosForos{
        Date fecha = new Date(java.util.Calendar.getInstance().getTime().getTime());
        Usuario usuario = new Usuario(emailActual,autorUsuario);
        EntradaForo adicion = new  EntradaForo(sp.consultarEntradasForo().size()+1,usuario,comenta,tituloIni,fecha);
        sp.registrarNuevaEntradaForo(adicion);
    }
    
    public EntradaForo getSelecc() {
        return selecc;
    }
    
    public boolean puedeMostrar(){
        return selecc!=null;
    }
    
     public void setSelecc(EntradaForo selecc) {
        this.selecc = selecc;
    }
    
    public List<Comentario> getComentariosEntrada() throws ExcepcionServiciosForos {        
        return new ArrayList<Comentario>(sp.consultarEntradaForo(selecc.getIdentificador()).getRespuestas());
    }

    public void addRespuesta() throws ExcepcionServiciosForos {
        EntradaForo entrada = sp.consultarEntradaForo(selecc.getIdentificador());
        Usuario usuario = new Usuario(emailActual, nombreActual);
        Date fecha = new Date(java.util.Calendar.getInstance().getTime().getTime());
        entrada.getRespuestas().add(new Comentario(usuario, contenidoActual, fecha));
    }

    public void setContenidoActual(String contenidoActual) {
        this.contenidoActual = contenidoActual;
    }

    public void setEmailActual(String emailActual) {
        this.emailActual = emailActual;
    }

    public void setNombreActual(String nombreActual) {
        this.nombreActual = nombreActual;
    }    

    public String getContenidoActual() {
        return contenidoActual;
    }

    public String getEmailActual() {
        return emailActual;
    }

    public String getNombreActual() {
        return nombreActual;
    }

    //================================================
   
    public List<EntradaForo> getEntradasForo() throws ExcepcionServiciosForos {
        return sp.consultarEntradasForo();
    }
    
    public String getAutorUsuario() {
        return autorUsuario;
    }

    public String getComenta() {
        return comenta;
    }


    public String getTituloIni() {
        return tituloIni;
    }

    

    public void setAutorUsuario(String autorUsuario) {
        this.autorUsuario = autorUsuario;
    }

    public void setComenta(String comenta) {
        this.comenta = comenta;
    }

    public void setTituloIni(String tituloIni) {
        this.tituloIni = tituloIni;
    }
}
