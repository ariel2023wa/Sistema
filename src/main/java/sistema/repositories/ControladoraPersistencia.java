package sistema.repositories;

import sistema.entities.*;
import sistema.repositories.exceptions.NonexistentEntityException;

import java.util.List;

public class ControladoraPersistencia {

    ClienteRepository cr = new ClienteRepository();

    ProblemaRepository pr = new ProblemaRepository();

    TecnicoRepository tr = new TecnicoRepository();

    EspecialidadRepository er = new EspecialidadRepository();

    IncidenteRepository ir = new IncidenteRepository();

    ServicioRepository sr = new ServicioRepository();

    public void crearCliente(Cliente cliente){

        cr.create(cliente);

    }

    public Cliente buscarClienteCUIT(String cuit) {

       return cr.findCUITCliente(cuit);

    }


    public List<Problema> listarProblemas() {

        return pr.findProblemaEntities();

    }

    public List<Tecnico> listarTecnicosPorEspecialidad(List<Especialidad> especialidades) {

        return tr.findTecnicosByEspecialidades(especialidades);

    }

    public List<Especialidad> listarEspecialidadesbyProblema(List<Problema> listaproblemas){

        return er.findEspecialidadesByProblemas(listaproblemas);

    }

    public List<Especialidad> listarEspecialidades(){

        return er.findEspecialidadEntities();

    }

    public void crearIncidente(Incidente incidente) {

        ir.create(incidente);

    }

    public List<Servicio> listarServicios() {

        return sr.findServicioEntities();

    }

    public void bajarCliente(int id) throws NonexistentEntityException {

        cr.destroy(id);

    }

    public Tecnico buscarTecnico(int id) {

        return tr.findTecnico(id);

    }

    public void crearTecnico(Tecnico tecnico) {

        tr.create(tecnico);

    }

    public Tecnico buscarTecnicoPorLegajo(String nroLegajo) {

        return tr.findTecnicoByLegajo(nroLegajo);

    }
}
