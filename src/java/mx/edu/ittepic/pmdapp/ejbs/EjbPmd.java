/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.ejbs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.edu.ittepic.pmdapp.entidades.Actividad;
import mx.edu.ittepic.pmdapp.entidades.Administracion;
import mx.edu.ittepic.pmdapp.entidades.Admondepe;
import mx.edu.ittepic.pmdapp.entidades.Categoriaplan;
import mx.edu.ittepic.pmdapp.entidades.Departamento;
import mx.edu.ittepic.pmdapp.entidades.Dependencia;
import mx.edu.ittepic.pmdapp.entidades.Empleado;
import mx.edu.ittepic.pmdapp.entidades.Entidad;
import mx.edu.ittepic.pmdapp.entidades.Estrategia;
import mx.edu.ittepic.pmdapp.entidades.Municipio;
import mx.edu.ittepic.pmdapp.entidades.Objetivo;
import mx.edu.ittepic.pmdapp.entidades.Participacion;
import mx.edu.ittepic.pmdapp.entidades.Plan;
import mx.edu.ittepic.pmdapp.entidades.Rol;
import mx.edu.ittepic.pmdapp.entidades.Usuario;
import mx.edu.ittepic.pmdapp.entidades.Usuariorol;

/**
 *
 * @author Esteban
 */
@Stateless
public class EjbPmd {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String insertarEntidad(String clave, String nombre, String abrev) {
        Entidad entidad = new Entidad();

        entidad.setClaveent(clave);
        entidad.setNombreent(nombre);
        entidad.setAbrevent(abrev);

        try {
            em.persist(entidad);
            return "{msg:'OK. Entidad insertada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar la Entidad.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarMunicipio(String clave, String nombre, int ident) {
        try {
            Entidad entidad = (Entidad) em.createNamedQuery("Entidad.findByIdent")
                    .setParameter("ident", ident)
                    .getSingleResult();
            Municipio municipio = new Municipio();

            municipio.setClavemun(clave);
            municipio.setNombremun(nombre);
            municipio.setIdent(entidad);

            em.persist(municipio);
            return "{msg:'OK. Municipio insertado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar el Municipio.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarAdministracion(String clave, String nombre, Date inicio, Date fin, int idmun) {
        try {
            Municipio municipio = (Municipio) em.createNamedQuery("Municipio.findByIdmun")
                    .setParameter("idmun", idmun)
                    .getSingleResult();
            Administracion administracion = new Administracion();

            administracion.setClaveadmon(clave);
            administracion.setNombreadmon(nombre);
            administracion.setFechaini(inicio);
            administracion.setFechafin(fin);
            administracion.setIdmun(municipio);

            em.persist(administracion);
            return "{msg:'OK. Administración insertada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar la Administración.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarDependencia(String clave, String nombre) {
        try {
            Dependencia dependencia = new Dependencia();

            dependencia.setClavedepe(clave);
            dependencia.setNombredepe(nombre);

            em.persist(dependencia);
            return "{msg:'OK. Dependencia insertada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar la Dependencia.\n" + e.getMessage() + "'}";
        }

    }

    public String insertarAdmondepe(int idadmon, int iddepe) {
        try {
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", idadmon)
                    .getSingleResult();
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();
            Admondepe admondepe = new Admondepe();

            admondepe.setIdadmon(admon);
            admondepe.setIddepe(depe);

            em.persist(admondepe);
            return "{msg:'OK. Registro almacenado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo relacionar la Dependencia con la Administración.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarDepartamento(String clave, String nombre, int iddepe) {
        try {
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();
            Departamento depto = new Departamento();

            depto.setClavedepto(clave);
            depto.setNombredepto(nombre);
            depto.setIddepe(depe);

            em.persist(depto);
            return "{msg:'OK. Departamento insertado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar el Departamento.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarEmpleado(String paterno, String materno, String nombre, int iddepto) {
        try {
            Departamento depto = (Departamento) em.createNamedQuery("Departamento.findByIddepto")
                    .setParameter("iddepto", iddepto)
                    .getSingleResult();
            Empleado empleado = new Empleado();

            empleado.setPaternoemp(paterno);
            empleado.setMaternoemp(materno);
            empleado.setNombreemp(nombre);
            empleado.setIddepto(depto);

            em.persist(empleado);
            return "{msg:'OK. Empleado insertado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar el Empleado.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarRol(String clave, String nombre) {
        Rol rol = new Rol();

        rol.setClaverol(clave);
        rol.setNombrerol(nombre);

        try {
            em.persist(rol);
            return "{msg:'OK. Rol Insertado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar el Rol.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarUsuario(String user, String pass, int idemp) {
        try {
            Empleado empleado = (Empleado) em.createNamedQuery("Empleado.findByIdemp")
                    .setParameter("idemp", idemp)
                    .getSingleResult();
            Usuario usuario = new Usuario();

            usuario.setUsuario(user);
            usuario.setContrasena(pass);
            usuario.setIdemp(empleado);

            em.persist(usuario);

            return "{msg:'OK. Usuario insertado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar el Usuario.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarUsuariorol(int idusuario, int idrol) {
        try {
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", idusuario)
                    .getSingleResult();
            Rol rol = (Rol) em.createNamedQuery("Rol.findByIdrol")
                    .setParameter("idrol", idrol)
                    .getSingleResult();
            Usuariorol usuariorol = new Usuariorol();

            usuariorol.setIdusuario(usuario);
            usuariorol.setIdrol(rol);

            em.persist(usuariorol);
            return "{msg:'OK. Rol de Usuario registrado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo registrar el Rol para el Usuario indicado.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarPlan(String clave, String nombre, int idadmon) {
        try {
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", idadmon)
                    .getSingleResult();
            Plan plan = new Plan();

            plan.setClaveplan(clave);
            plan.setNombreplan(nombre);
            plan.setIdadmon(admon);

            em.persist(plan);
            return "{msg:'OK. Plan registrado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo registrar el Plan.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarCategoriaplan(String clave, String nombre, int idcatpadre, int idplan, String descrip) {
        try {

            Plan plan = (Plan) em.createNamedQuery("Plan.findByIdplan")
                    .setParameter("idplan", idplan)
                    .getSingleResult();
            Categoriaplan categoria = new Categoriaplan();

            categoria.setClavecat(clave);
            categoria.setNombrecat(nombre);
            categoria.setIdcatpadre(idcatpadre);
            categoria.setIdplan(plan);
            categoria.setDescripcat(descrip);

            em.persist(categoria);
            return "{msg: 'OK. Categoría insertada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar la Categoría.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarParticipacion(int iddepe, int idcat) {
        try {
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat)
                    .getSingleResult();
            Participacion participacion = new Participacion();

            participacion.setIddepe(depe);
            participacion.setIdcat(cat);

            em.persist(participacion);
            return "{msg:'OK. Participación Dependencia->Categoría registrada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo registrar la Participación Dependencia->Categoría." + e.getMessage() + "'}";
        }
    }

    public String insertarObjetivo(String clave, String obj, int idcat) {
        try {
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat)
                    .getSingleResult();
            Objetivo objetivo = new Objetivo();
            
            objetivo.setClaveobjetivo(clave);
            objetivo.setObjetivo(obj);
            objetivo.setIdcat(cat);
            
            em.persist(objetivo);
            return "{msg:'OK. Objetivo insertado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo insertar el Objetivo.\n" + e.getMessage() + "'}";
        }
    }
    
    public String insertarEstrategia(String clave, String estrat, int idobjetivo){
        try{
            Objetivo objetivo = (Objetivo) em.createNamedQuery("Objetivo.findByIdobjetivo")
                    .setParameter("idobjetivo", idobjetivo)
                    .getSingleResult();
            Estrategia estrategia = new Estrategia();
            
            estrategia.setClaveestrategia(clave);
            estrategia.setEstrategia(estrat);
            estrategia.setIdobjetivo(objetivo);
            
            em.persist(estrategia);
            return "{msg:'OK. Estrategia insertada correctamente.'}";
        }catch(Exception e){
            return "{msg:'ERROR: No se pudo insertar la Estrategia.\n" + e.getMessage() + "'}";
        }
    }

    public String insertarActividad(String activ, Date inicio, Date fin, 
            Float latitud, Float longitud, int idcat, int idusuario, int iddepe){
        
        try{
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat)
                    .getSingleResult();
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", idusuario)
                    .getSingleResult();
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();
            Actividad actividad = new Actividad();
            
            actividad.setActividad(activ);
            actividad.setFechaini(inicio);
            actividad.setFechafin(fin);
            actividad.setLatitud(latitud);
            actividad.setLongitud(longitud);
            actividad.setIdcat(cat);
            actividad.setIdusuario(usuario);
            actividad.setIddepe(depe);
            
            em.persist(actividad);
            return "{msg:'OK. Actividad registrada correctamente.'}";
        }catch(Exception e){
            return "{msg:'ERROR: No se pudo insertar la actividad.\n" + e.getMessage() + "'}";
        }
    }
    
    public String consultarEntidades(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Entidad> listaEntidades = em.createNamedQuery("Entidad.findAll").getResultList();
        
        for(int i=0; i<listaEntidades.size(); i++){
            listaEntidades.get(i).setMunicipioList(null);
        }
        
        return gson.toJson(listaEntidades);
    }
    
    public String consultarMunicipios(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Municipio> listaMunicipios = em.createNamedQuery("Municipio.findAll").getResultList();
        
        for(int i=0; i<listaMunicipios.size(); i++){
            listaMunicipios.get(i).setAdministracionList(null);
            listaMunicipios.get(i).getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaMunicipios);
    }
    
    public String consultarAdministraciones(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Administracion> listaAdministraciones = em.createNamedQuery("Administracion.findAll").getResultList();
        
        for(int i=0; i<listaAdministraciones.size(); i++){
            listaAdministraciones.get(i).setAdmondepeList(null);
            listaAdministraciones.get(i).setPlanList(null);
            listaAdministraciones.get(i).getIdmun().setAdministracionList(null);
            listaAdministraciones.get(i).getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaAdministraciones);
    }
    
    public String consultarDependencias(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Dependencia> listaDepe = em.createNamedQuery("Dependencia.findAll").getResultList();
        
        for(int i=0; i<listaDepe.size(); i++){
            listaDepe.get(i).setActividadList(null);
            listaDepe.get(i).setAdmondepeList(null);
            listaDepe.get(i).setDepartamentoList(null);
            listaDepe.get(i).setParticipacionList(null);
        }
        return gson.toJson(listaDepe);
    }
    
    public String consultarAdmondepe(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Admondepe> listaAdmondepe = em.createNamedQuery("Admondepe.findAll").getResultList();
        
        for(int i=0; i<listaAdmondepe.size(); i++){
            listaAdmondepe.get(i).getIdadmon().setAdmondepeList(null);
            listaAdmondepe.get(i).getIdadmon().setPlanList(null);
            listaAdmondepe.get(i).getIdadmon().getIdmun().setAdministracionList(null);
            listaAdmondepe.get(i).getIdadmon().getIdmun().getIdent().setMunicipioList(null);
            
            listaAdmondepe.get(i).getIddepe().setActividadList(null);
            listaAdmondepe.get(i).getIddepe().setAdmondepeList(null);
            listaAdmondepe.get(i).getIddepe().setDepartamentoList(null);
            listaAdmondepe.get(i).getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaAdmondepe);
    }
    
    public String consultarDepartamentos(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Departamento> listaDeptos = em.createNamedQuery("Departamento.findAll").getResultList();
        
        for(int i=0; i<listaDeptos.size(); i++){
            listaDeptos.get(i).setEmpleadoList(null);
            
            listaDeptos.get(i).getIddepe().setActividadList(null);
            listaDeptos.get(i).getIddepe().setAdmondepeList(null);
            listaDeptos.get(i).getIddepe().setDepartamentoList(null);
            listaDeptos.get(i).getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaDeptos);
    }
    
    public String consultarEmpleados(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Empleado> listaEmpleados = em.createNamedQuery("Empleado.findAll").getResultList();
        
        for(int i=0; i<listaEmpleados.size(); i++){
            listaEmpleados.get(i).setUsuarioList(null);
            listaEmpleados.get(i).getIddepto().setEmpleadoList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setActividadList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setAdmondepeList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setDepartamentoList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaEmpleados);
    }
    
    public String consultarRoles(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Rol> listaRoles = em.createNamedQuery("Rol.findAll").getResultList();
        
        for (Rol rol : listaRoles) {
            rol.setUsuariorolList(null);
        }
        return gson.toJson(listaRoles);
    }
    
    public String consultarUsuarios(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Usuario> listaUsuarios = em.createNamedQuery("Usuario.findAll").getResultList();
        
        for (Usuario usuario : listaUsuarios) {
            usuario.setActividadList(null);
            usuario.setUsuariorolList(null);
            usuario.getIdemp().setUsuarioList(null);
            usuario.getIdemp().getIddepto().setEmpleadoList(null);
            usuario.getIdemp().getIddepto().getIddepe().setActividadList(null);
            usuario.getIdemp().getIddepto().getIddepe().setAdmondepeList(null);
            usuario.getIdemp().getIddepto().getIddepe().setDepartamentoList(null);
            usuario.getIdemp().getIddepto().getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaUsuarios);
    }
    
    public String consultarUsuariorol(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Usuariorol> listaUsuariorol = em.createNamedQuery("Usuariorol.findAll").getResultList();
        
        for (Usuariorol usuariorol : listaUsuariorol) {
            usuariorol.getIdrol().setUsuariorolList(null);
            
            usuariorol.getIdusuario().setActividadList(null);
            usuariorol.getIdusuario().setUsuariorolList(null);
            usuariorol.getIdusuario().getIdemp().setUsuarioList(null);
            usuariorol.getIdusuario().getIdemp().getIddepto().setEmpleadoList(null);
            usuariorol.getIdusuario().getIdemp().getIddepto().getIddepe().setActividadList(null);
            usuariorol.getIdusuario().getIdemp().getIddepto().getIddepe().setAdmondepeList(null);
            usuariorol.getIdusuario().getIdemp().getIddepto().getIddepe().setDepartamentoList(null);
            usuariorol.getIdusuario().getIdemp().getIddepto().getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaUsuariorol);
    }
    
    public String consultarPlanes(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Plan> listaPlanes = em.createNamedQuery("Plan.findAll").getResultList();
        
        for (Plan plan : listaPlanes) {
            plan.setCategoriaplanList(null);
            plan.getIdadmon().setAdmondepeList(null);
            plan.getIdadmon().setPlanList(null);
            plan.getIdadmon().getIdmun().setAdministracionList(null);
            plan.getIdadmon().getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaPlanes);
    }
    
    public String consultarCategorias(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Categoriaplan> listaCategorias = em.createNamedQuery("Categoriaplan.findAll").getResultList();
        
        for (Categoriaplan cat : listaCategorias) {
            cat.setActividadList(null);
            cat.setObjetivoList(null);
            cat.setParticipacionList(null);
            cat.getIdplan().setCategoriaplanList(null);
            cat.getIdplan().getIdadmon().setAdmondepeList(null);
            cat.getIdplan().getIdadmon().setPlanList(null);
            cat.getIdplan().getIdadmon().getIdmun().setAdministracionList(null);
            cat.getIdplan().getIdadmon().getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaCategorias);
    }
    
    public String consultarParticipacion(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Participacion> listaParticipaciones = em.createNamedQuery("Participacion.findAll").getResultList();
        
        for (Participacion p : listaParticipaciones) {
            p.getIdcat().setActividadList(null);
            p.getIdcat().setObjetivoList(null);
            p.getIdcat().setParticipacionList(null);
            p.getIdcat().getIdplan().setCategoriaplanList(null);
            p.getIdcat().getIdplan().getIdadmon().setAdmondepeList(null);
            p.getIdcat().getIdplan().getIdadmon().setPlanList(null);
            p.getIdcat().getIdplan().getIdadmon().getIdmun().setAdministracionList(null);
            p.getIdcat().getIdplan().getIdadmon().getIdmun().getIdent().setMunicipioList(null);
            
            p.getIddepe().setActividadList(null);
            p.getIddepe().setAdmondepeList(null);
            p.getIddepe().setDepartamentoList(null);
            p.getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaParticipaciones);
    }
    
    public String consultarObjetivos(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Objetivo> listaObjetivos = em.createNamedQuery("Objetivo.findAll").getResultList();
        
        for(Objetivo o : listaObjetivos){
            o.setEstrategiaList(null);
            o.getIdcat().setActividadList(null);
            o.getIdcat().setObjetivoList(null);
            o.getIdcat().setParticipacionList(null);
            o.getIdcat().getIdplan().setCategoriaplanList(null);
            o.getIdcat().getIdplan().getIdadmon().setAdmondepeList(null);
            o.getIdcat().getIdplan().getIdadmon().setPlanList(null);
            o.getIdcat().getIdplan().getIdadmon().getIdmun().setAdministracionList(null);
            o.getIdcat().getIdplan().getIdadmon().getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaObjetivos);
    }
    
    public String consultarEstrategias(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Estrategia> listaEstrategias = em.createNamedQuery("Estrategia.findAll").getResultList();
        
        for(Estrategia e : listaEstrategias){
            e.getIdobjetivo().setEstrategiaList(null);
            e.getIdobjetivo().getIdcat().setActividadList(null);
            e.getIdobjetivo().getIdcat().setObjetivoList(null);
            e.getIdobjetivo().getIdcat().setParticipacionList(null);
            e.getIdobjetivo().getIdcat().getIdplan().setCategoriaplanList(null);
            e.getIdobjetivo().getIdcat().getIdplan().getIdadmon().setAdmondepeList(null);
            e.getIdobjetivo().getIdcat().getIdplan().getIdadmon().setPlanList(null);
            e.getIdobjetivo().getIdcat().getIdplan().getIdadmon().setPlanList(null);
            e.getIdobjetivo().getIdcat().getIdplan().getIdadmon().getIdmun().setAdministracionList(null);
            e.getIdobjetivo().getIdcat().getIdplan().getIdadmon().getIdmun().setAdministracionList(null);
            e.getIdobjetivo().getIdcat().getIdplan().getIdadmon().getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaEstrategias);
    }
    
    public String consultarActividades(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Actividad> listaActividades = em.createNamedQuery("Actividad.findAll").getResultList();
        
        for(Actividad a : listaActividades){
            a.getIdcat().setActividadList(null);
            a.getIdcat().setObjetivoList(null);
            a.getIdcat().setParticipacionList(null);
            a.getIdcat().getIdplan().setCategoriaplanList(null);
            a.getIdcat().getIdplan().getIdadmon().setAdmondepeList(null);
            a.getIdcat().getIdplan().getIdadmon().setPlanList(null);
            a.getIdcat().getIdplan().getIdadmon().getIdmun().setAdministracionList(null);
            a.getIdcat().getIdplan().getIdadmon().getIdmun().getIdent().setMunicipioList(null);
            
            a.getIddepe().setActividadList(null);
            a.getIddepe().setAdmondepeList(null);
            a.getIddepe().setDepartamentoList(null);
            a.getIddepe().setParticipacionList(null);
            
            a.getIdusuario().setActividadList(null);
            a.getIdusuario().setUsuariorolList(null);
            a.getIdusuario().getIdemp().setUsuarioList(null);
            a.getIdusuario().getIdemp().getIddepto().setEmpleadoList(null);
            a.getIdusuario().getIdemp().getIddepto().getIddepe().setActividadList(null);
            a.getIdusuario().getIdemp().getIddepto().getIddepe().setAdmondepeList(null);
            a.getIdusuario().getIdemp().getIddepto().getIddepe().setDepartamentoList(null);
            a.getIdusuario().getIdemp().getIddepto().getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaActividades);
    }
}
