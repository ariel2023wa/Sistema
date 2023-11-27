package sistema.repositories;

import sistema.entities.Cliente;
import sistema.entities.Especialidad;
import sistema.entities.Problema;
import sistema.entities.Tecnico;

import java.util.List;

public class ControladoraPersistencia {

    ClienteRepository cr = new ClienteRepository();

    ProblemaRepository pr = new ProblemaRepository();

    TecnicoRepository tr = new TecnicoRepository();

    EspecialidadRepository er = new EspecialidadRepository();

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

}
