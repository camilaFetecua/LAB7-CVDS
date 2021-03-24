package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;

import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {

    @Inject
    private ItemDAO itemDAO;
    @Inject
    private ClienteDAO clienteDAO;
    @Inject
    private TipoItemDAO tipoItemDAO;

    //Cliente
    @Transactional
    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try{
            clienteDAO.vetarCliente(docu,estado);
        } catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("No se pudo vetar el cliente "+docu,ex);
        }
    }

    @Transactional
    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try{
            clienteDAO.save(c);
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al registrar al cliente "+c.getDocumento(),ex);
        }
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try{
            return clienteDAO.load(docu);
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("No se encuentra el cliente "+docu,ex);
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try{
            return clienteDAO.load();
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("No se encuentran clientes",ex);
        }
    }

    //Items

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        Item item = null;
        try {
             item = consultarItem(iditem);
            return item.getTarifaxDia() * numdias;
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar item "+iditem,ex);
        }catch (NullPointerException ex){
            throw new ExcepcionServiciosAlquiler("El item no existe");
        }

    }

    @Transactional
    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.actualizarTarifaItem(id,tarifa);
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa del item "+id,ex);
        }
    }

    @Transactional
    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try{
            itemDAO.save(i);
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al registrar item "+i.getId(),ex);
        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los items ",ex);
        }
    }

    //TipoItem
    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return tipoItemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item "+id,ex);
        }
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        try{
            return tipoItemDAO.load();
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar los items",ex);
        }
    }

    //ItemRentado

    @Transactional
    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        try{
            LocalDate entrega = LocalDate.now().plusDays(numdias);
            clienteDAO.agregarItemRentadoCliente(docu,item.getId(),date,Date.valueOf(entrega));
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al registrar el item rentado "+item.getId()
                    +" al cliente "+docu,ex);
        }
    }

    @Override
    public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
        try{
            return (int)itemDAO.load(itemId).getTarifaxDia();
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("El item no se encuentra registrado "+itemId,ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return consultarCliente(idcliente).getRentados();
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar los items alquilados por el cliente "+idcliente,ex);
        }
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        try {
            long multa = 0;
            List<Cliente> clientes = consultarClientes();
            for (Cliente i : clientes) {
                for (ItemRentado j : i.getRentados()) {
                    if (j.getItem().getId() == iditem) {
                        LocalDate fin = j.getFechafinrenta().toLocalDate();
                        LocalDate inicio = j.getFechainiciorenta().toLocalDate();
                        long retraso = ChronoUnit.DAYS.between(fin, inicio);
                        if (retraso < 0) {
                            return multa;
                        }
                        else{ multa = retraso*valorMultaRetrasoxDia(iditem);}
                    }
                }
            }
            return multa;
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar la multa total del item",ex);
        }
    }











}