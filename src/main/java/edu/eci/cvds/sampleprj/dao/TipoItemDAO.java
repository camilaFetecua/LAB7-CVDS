package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;

public interface TipoItemDAO {
    public void save(String description) throws PersistenceException;

    public TipoItem load(int id) throws PersistenceException;

    public List<TipoItem> load() throws PersistenceException;
}
