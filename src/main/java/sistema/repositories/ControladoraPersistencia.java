package sistema.repositories;

import sistema.entities.*;
import sistema.entities.state.Abierto;
import sistema.repositories.exceptions.NonexistentEntityException;

import java.util.List;

public class ControladoraPersistencia {

    ClienteRepository cr = new ClienteRepository();

    ProblemaRepository pr = new ProblemaRepository();

    TecnicoRepository tr = new TecnicoRepository();

    EspecialidadRepository er = new EspecialidadRepository();

    IncidenteRepository ir = new IncidenteRepository();

    ServicioRepository sr = new ServicioRepository();

    AbiertoRepository ar = new AbiertoRepository();

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

    public void bajarTecnico(int id) throws NonexistentEntityException {

        tr.destroy(id);
    }

    public void modificarTecnico(Tecnico tecnico) throws Exception {

        tr.edit(tecnico);

    }

    public void modificarCliente(Cliente cliente) throws Exception {

        cr.edit(cliente);

    }

    public List<Tecnico> listarTecnicos() {

        return tr.findTecnicoEntities();

    }

    public void crearAbierto(Abierto abierto){

        ar.create(abierto);

    }

}
