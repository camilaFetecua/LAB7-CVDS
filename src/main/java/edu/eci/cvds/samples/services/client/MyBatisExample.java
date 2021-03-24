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
package edu.eci.cvds.samples.services.client;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;


/**
 *
 * @author hcadavid
 */
public class MyBatisExample {
    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws ExcepcionServiciosAlquiler
     */
    public static void main(String args[]) throws ExcepcionServiciosAlquiler {

        ServiciosAlquiler serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarClientes());
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarCliente(71));
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarItemsDisponibles());
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarItem(1));
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarTipoItem(1));
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarTiposItem());
        System.out.println("--------------------------------------------");
        //serviciosAlquiler.vetarCliente(2,true);
        /*serviciosAlquiler.registrarCliente(new Cliente("Daniel",1019139154,"3059231873",
                "Carrera 123","d.mejia98@gmail.com",false,new ArrayList<ItemRentado>()));*/
        System.out.println(serviciosAlquiler.consultarCliente(1019139154));
        System.out.println("--------------------------------------------");
        System.out.println(serviciosAlquiler.consultarCostoAlquiler(1,2));
        System.out.println("--------------------------------------------");
        //serviciosAlquiler.actualizarTarifaItem(1,15);
        System.out.println(serviciosAlquiler.consultarCostoAlquiler(1,1));
        System.out.println("--------------------------------------------");
        /*serviciosAlquiler.registrarItem(new Item(new TipoItem(2,"Accion"),18,"Consola",
                "Videojuegos",Date.valueOf(LocalDate.now()),1000,"hola","cosa"));*/
        System.out.println(serviciosAlquiler.consultarItem(18));
        /*serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(LocalDate.now()),1019139154,
                new Item(new TipoItem(2,"Accion"), 18,"Consola","Videojuegos",
                        Date.valueOf(LocalDate.now()),1000,"hola","cosa"),10);*/
        System.out.println(serviciosAlquiler.consultarCliente(1019139154));


    }


}
