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
    private Cliente cliente;
}
