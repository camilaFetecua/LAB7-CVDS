package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;

import java.sql.Date;
import java.util.List;

public interface ItemRentadoMapper {
    public List<ItemRentado> consultarItemsRentados();

    public ItemRentado consultarItemRentado(int id);

}
