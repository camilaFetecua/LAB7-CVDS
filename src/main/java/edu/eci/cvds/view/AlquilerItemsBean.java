package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ManagedBean(name = "AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean{
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente selectedCliente;
    private long costo;

    public void setSelectedCliente(Cliente cliente){this.selectedCliente = cliente;}

    public Cliente getSelectedCliente(){
        return selectedCliente;
    }

    /**
     * consultar todos los clientes
     * @return todos los clientes
     * @throws ExcepcionServiciosAlquiler
     */
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarClientes();
        } catch (Exception e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar clientes");
        }
    }

    /**
     * consultar un cliente por su numero dedocumento
     * @param documento
     * @return cliente con el documento consultado
     * @throws ExcepcionServiciosAlquiler
     */
    public Cliente consultarCliente(long documento) throws ExcepcionServiciosAlquiler{
        try{
            return serviciosAlquiler.consultarCliente(documento);
        } catch (Exception e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar clientes");
        }
    }
    public void registrarCliente(long doc, String nombre, String telefono, String direccion, String email){
        try {
            serviciosAlquiler.registrarCliente(new Cliente(nombre,doc,telefono,direccion,email));
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }
    }

    public long consultarMulta(int idItem)  {
        long multa = 0;
        try {
            multa = serviciosAlquiler.consultarMultaAlquiler(idItem, new Date(System.currentTimeMillis()));
        } catch (ExcepcionServiciosAlquiler e){
            e.printStackTrace();
        }
        return multa;
    }

    public void registrarAlquilerCliente(int id,int numDiasAlquilar){
        try{
            Item item = serviciosAlquiler.consultarItem(id);
            System.out.println(serviciosAlquiler.consultarItem(id));
            System.out.println(getSelectedCliente().getDocumento());
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),selectedCliente.getDocumento(),item,numDiasAlquilar);
        }catch(ExcepcionServiciosAlquiler e){
            e.printStackTrace();
        }
    }

    public void consultarCosto(int id, int numDiasAlquilar){
        try {
            costo = serviciosAlquiler.consultarCostoAlquiler(id, numDiasAlquilar);
        } catch (ExcepcionServiciosAlquiler e){
            e.printStackTrace();
        }
    }
    public long getCosto(){
        return costo;
    }
}
