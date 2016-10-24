/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin y Jessica 
 */
public class ServiciosForoDAO extends ServiciosForo{
    
    private DaoFactory daof;
    
    public ServiciosForoDAO(){
        
        try{
            Properties properties = new PropertiesLoader().readProperties("applicationconfig.properties");
            daof = DaoFactory.getInstance(properties);
            daof.beginSession();
        }catch(PersistenceException | IOException e){
            Logger.getLogger(ServiciosForoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public List<EntradaForo> consultarEntradasForo() throws ExcepcionServiciosForos {
        List<EntradaForo> entradas = null;
        try{
            entradas = daof.getDaoEntradaForo().loadAll(); 
        }catch(PersistenceException e){
            throw new ExcepcionServiciosForos(e.getMessage());
        }
        return entradas;
    }

    @Override
    public EntradaForo consultarEntradaForo(int id) throws ExcepcionServiciosForos {
        EntradaForo entrada = null;
        try{
            entrada = daof.getDaoEntradaForo().load(id);
        }catch(PersistenceException e){
            throw new ExcepcionServiciosForos(e.getMessage());
        }
        if (entrada == null) throw new ExcepcionServiciosForos("No existe el identificador");
        return entrada;
    }

    @Override
    public void registrarNuevaEntradaForo(EntradaForo f) throws ExcepcionServiciosForos {
        if (f.getAutor() == null) throw new ExcepcionServiciosForos("No hay usuario asignado");
        try{
            daof.getDaoEntradaForo().save(f);
        }catch(PersistenceException e){
            throw new ExcepcionServiciosForos(e.getMessage());
        }
    }

    @Override
    public void agregarRespuestaForo(int idforo, Comentario c) throws ExcepcionServiciosForos {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if (c.getAutor() == null || c.getAutor().getEmail() == null) throw new ExcepcionServiciosForos("No hay correo asociado");
        try{
            daof.getDaoEntradaForo().addToForo(idforo, c);
        }catch(PersistenceException e){
            throw new ExcepcionServiciosForos(e.getMessage());
        }
    }
    
    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosForos {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Usuario usuario = null;
        try{
            daof.getDaoUsuario().load(email);
        }catch(PersistenceException e){
            throw new ExcepcionServiciosForos("No existe usuario asociado al email");
        }
        return usuario;
    }
    
}

class PropertiesLoader {

    public Properties readProperties(String fileName) throws IOException {
        InputStream input = null;
        Properties properties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream(fileName);
        properties.load(input);
        return properties;
    }
}