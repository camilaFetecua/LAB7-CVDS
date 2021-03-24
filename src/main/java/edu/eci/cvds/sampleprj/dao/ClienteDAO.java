package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.Date;
import java.util.List;

public interface ClienteDAO {

    public void save(Cliente c) throws PersistenceException;

    public Cliente load(long id) throws PersistenceException;

    public List<Cliente> load() throws PersistenceException;

    void vetarCliente(long docu, boolean estado);

    void agregarItemRentadoCliente(long docu, int id, Date date, Date valueOf);
}
