package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.exceptions.PersistenceException;

import javax.inject.Inject;
import java.sql.Date;
import java.util.List;

public class MyBatisClienteDAO implements ClienteDAO {
    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente c) throws PersistenceException {
        try{
            clienteMapper.insertarCliente(c);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el producto al cliente "+c.getDocumento(),e);
        }
    }



    @Override
    public Cliente load(long id) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(id);
        }
        catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar cliente "+id,e);
        }
    }

    @Override
    public List<Cliente> load() throws PersistenceException{
        try{
            return clienteMapper.consultarClientes();
        }
        catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los clientes");
        }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws PersistenceException {
        try {
            int flag = estado?1:0;
            clienteMapper.vetarCliente(docu, flag);
        } catch (PersistenceException e){
            throw new PersistenceException("Error al vetar el cliente "+docu,e);
        }
    }

    @Override
    public void agregarItemRentadoCliente(long docu, int idItem, Date inicio, Date fin) {
        try{
            clienteMapper.agregarItemRentadoACliente((int)docu,idItem,inicio,fin);
        }catch (PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+idItem+" al cliente "+docu);
        }
    }
}
