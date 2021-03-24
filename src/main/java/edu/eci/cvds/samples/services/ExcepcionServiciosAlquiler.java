package edu.eci.cvds.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception{
    public ExcepcionServiciosAlquiler(String message, PersistenceException ex){
        super(message);
    }

    public ExcepcionServiciosAlquiler(String message) {

    }
}
