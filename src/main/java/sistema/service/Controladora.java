package sistema.service;

import sistema.entities.*;
import sistema.repositories.ControladoraPersistencia;

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

}
