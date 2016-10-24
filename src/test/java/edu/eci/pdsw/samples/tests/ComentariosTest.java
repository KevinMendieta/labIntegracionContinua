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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ServiciosForoStub;

/**
 *
 * @author hcadavid
 */
public class ComentariosTest {

    /*public ComentariosTest() {
         
    PRUEBAS DE EQUIVALENCIA
   
    - SI el comentario no tiene asociado un usuario.
    - El ID asociado no se encuentra
    
    }
    */
    private ServiciosForo nuevo;
    @Before
    public void setUp() {
         nuevo = nuevo.getInstance();
    }
     @Test
     public void registroPacineteTestC1() throws ExcepcionServiciosForos  {
        Comentario com= new Comentario();
        try{
            nuevo.agregarRespuestaForo(0, com);
            fail("No ingresa excepcion");
        }catch(ExcepcionServiciosForos e){
        }
    }    
    @Test
     public void registroPacineteTestC2() throws ExcepcionServiciosForos{
        Usuario us=new Usuario();
        Comentario com= new Comentario();
        try{
            nuevo.consultarEntradaForo(-1);
            fail("No ingresa excepcion");
        }catch(ExcepcionServiciosForos e){
        }
    } 
}
    
    
    

