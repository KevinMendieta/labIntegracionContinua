/*
 * Copyright (C) 2015 hcadavid
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

import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class EntradasForoTest {
    
    /*
     *Definicion de clases de equivalencia.
     * CE1 : Que la entrada al foro tenga asociado un usuario Tipo: Correcta.
     * CE2 : Que la entrada al foro no tenga asociado un usuario Tipo: Incorrecto.
     */
    
    private ServiciosForo sfs;
    
    public EntradasForoTest() {
    }
    
    @Before
    public void setUp() {
        sfs = sfs.getInstance();
    }
    
    @Test
    public void registroEntradaTestCE1(){
        try{
            sfs.registrarNuevaEntradaForo(new EntradaForo(0,new Usuario("pedro.perez@gmail.com","Pedro Perez"),"asd","asd",new Date(java.util.Calendar.getInstance().getTime().getTime())));
        }catch(ExcepcionServiciosForos e){
            fail();
        }
    }
    
    @Test
    public void registroEntradaTestCE2(){
        try{
            sfs.registrarNuevaEntradaForo(new EntradaForo());
            fail();
        }catch(ExcepcionServiciosForos e){
            
        }
    }
}