package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ManagedBean(name = "AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean{
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente clienteSeleccionado;

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
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
}
