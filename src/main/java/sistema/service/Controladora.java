package sistema.service;

import sistema.entities.*;
import sistema.entities.state.Abierto;
import sistema.repositories.ControladoraPersistencia;
import sistema.repositories.exceptions.NonexistentEntityException;

import java.util.List;

public class Controladora {

    ControladoraPersistencia contP = new ControladoraPersistencia();

    public void crearCliente(Cliente cliente) {

        contP.crearCliente(cliente);

    }

    public Cliente buscarClienteCUIT(String cuit) {

        return contP.buscarClienteCUIT(cuit);

    }

    public List<Problema> listarProblemas() {

        return contP.listarProblemas();

    }

    public List<Tecnico> listarTecnicosPorEspecialidades(List<Especialidad> especialidades){

        return contP.listarTecnicosPorEspecialidad(especialidades);

    }

    public List<Especialidad> listarEspecialidadesByProblema(List<Problema> listaProblemas){

        return contP.listarEspecialidadesbyProblema(listaProblemas);

    }

    public List<Especialidad> listarEspecialidades(){

        return contP.listarEspecialidades();

    }

    public void crearIncidente(Incidente incidente){

        contP.crearIncidente(incidente);

    }

    public List<Servicio> listarServicios() {

        return contP.listarServicios();

    }

    public void bajarCliente(int id) throws NonexistentEntityException {

        contP.bajarCliente(id);

    }

    public Tecnico buscarTecnico(int id) {

        return contP.buscarTecnico(id);

    }

    public void crearTecnico(Tecnico tecnico) {

        contP.crearTecnico(tecnico);

    }

    public Tecnico buscarTecnicoPorLegajo(String nroLegajo) {

        return contP.buscarTecnicoPorLegajo(nroLegajo);

    }

    public void bajarTecnico(int id) throws NonexistentEntityException {

        contP.bajarTecnico(id);

    }

    public void modificarTecnico(Tecnico tecnico) throws Exception {

        contP.modificarTecnico(tecnico);

    }

    public void modificarCliente(Cliente cliente) throws Exception {

        contP.modificarCliente(cliente);

    }

    public List<Tecnico> listarTecnicos() {

        return contP.listarTecnicos();

    }

    public void crearAbierto(Abierto abierto){

        contP.crearAbierto(abierto);

    }

}
