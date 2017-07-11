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
import javax.persistence.EntityExistsException;
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
import mx.edu.ittepic.pmdapp.util.Util;

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
        String msg;
        Entidad entidad = new Entidad();

        entidad.setClaveent(clave);
        entidad.setNombreent(nombre);
        entidad.setAbrevent(abrev);

        try {
            em.persist(entidad);
            return "{\"msg\": \"OK. Entidad insertada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar la Entidad.\n" + e.getMessage() + "\"}";
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
            return "{\"msg\": \"OK. Municipio insertado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar el Municipio.\n" + e.getMessage() + "\"}";
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
            return "{\"msg\": \"OK. Administración insertada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar la Administración.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarDependencia(String clave, String nombre) {
        try {
            Dependencia dependencia = new Dependencia();

            dependencia.setClavedepe(clave);
            dependencia.setNombredepe(nombre);

            em.persist(dependencia);
            return "{\"msg\": \"OK. Dependencia insertada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar la Dependencia.\n" + e.getMessage() + "\"}";
        }

    }

    public String insertarAdmondepe(String idadmon, String iddepe) {
        try {
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", Integer.parseInt(idadmon))
                    .getSingleResult();
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", Integer.parseInt(iddepe))
                    .getSingleResult();
            Admondepe admondepe = new Admondepe();

            admondepe.setIdadmon(admon);
            admondepe.setIddepe(depe);

            em.persist(admondepe);
            return "{\"msg\": \"OK. Registro almacenado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo relacionar la Dependencia con la Administración.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarDepartamento(String clave, String nombre, String iddepe) {
        try {
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", Integer.parseInt(iddepe))
                    .getSingleResult();
            Departamento depto = new Departamento();

            depto.setClavedepto(clave);
            depto.setNombredepto(nombre);
            depto.setIddepe(depe);

            em.persist(depto);
            return "{\"msg\": \"OK. Departamento insertado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar el Departamento.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarEmpleado(String paterno, String materno, String nombre, String iddepto) {
        try {
            Departamento depto = (Departamento) em.createNamedQuery("Departamento.findByIddepto")
                    .setParameter("iddepto", Integer.parseInt(iddepto))
                    .getSingleResult();
            Empleado empleado = new Empleado();

            empleado.setPaternoemp(paterno);
            empleado.setMaternoemp(materno);
            empleado.setNombreemp(nombre);
            empleado.setIddepto(depto);

            em.persist(empleado);
            return "{\"msg\": \"OK. Empleado insertado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar el Empleado.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarRol(String clave, String nombre) {
        Rol rol = new Rol();

        rol.setClaverol(clave);
        rol.setNombrerol(nombre);

        try {
            em.persist(rol);
            return "{\"msg\": \"OK. Rol Insertado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar el Rol.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarUsuario(String user, String pass, String idemp) {
        try {
            Empleado empleado = (Empleado) em.createNamedQuery("Empleado.findByIdemp")
                    .setParameter("idemp", Integer.parseInt(idemp))
                    .getSingleResult();
            Usuario usuario = new Usuario();

            usuario.setUsuario(user);
            usuario.setContrasena(pass);
            usuario.setIdemp(empleado);

            em.persist(usuario);

            return "{\"msg\": \"OK. Usuario insertado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar el Usuario.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarUsuariorol(String idusuario, String idrol) {
        try {
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", Integer.parseInt(idusuario))
                    .getSingleResult();
            Rol rol = (Rol) em.createNamedQuery("Rol.findByIdrol")
                    .setParameter("idrol", Integer.parseInt(idrol))
                    .getSingleResult();
            Usuariorol usuariorol = new Usuariorol();

            usuariorol.setIdusuario(usuario);
            usuariorol.setIdrol(rol);

            em.persist(usuariorol);
            return "{\"msg\": \"OK. Rol de Usuario registrado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo registrar el Rol para el Usuario indicado.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarPlan(String clave, String nombre, String idadmon) {
        try {
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", Integer.parseInt(idadmon))
                    .getSingleResult();
            Plan plan = new Plan();

            plan.setClaveplan(clave);
            plan.setNombreplan(nombre);
            plan.setIdadmon(admon);

            em.persist(plan);
            return "{\"msg\": \"OK. Plan registrado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo registrar el Plan.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarCategoriaplan(String clave, String nombre, String idcatpadre, String idplan, String descrip) {
        try {

            Plan plan = (Plan) em.createNamedQuery("Plan.findByIdplan")
                    .setParameter("idplan", Integer.parseInt(idplan))
                    .getSingleResult();
            Categoriaplan categoria = new Categoriaplan();

            categoria.setClavecat(clave);
            categoria.setNombrecat(nombre);
            categoria.setIdcatpadre(Integer.parseInt(idcatpadre));
            categoria.setIdplan(plan);
            categoria.setDescripcat(descrip);

            em.persist(categoria);
            return "{\"msg\": \"OK. Categoría insertada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar la Categoría.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarParticipacion(String iddepe, String idcat) {
        try {
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", Integer.parseInt(iddepe))
                    .getSingleResult();
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", Integer.parseInt(idcat))
                    .getSingleResult();
            Participacion participacion = new Participacion();

            participacion.setIddepe(depe);
            participacion.setIdcat(cat);

            em.persist(participacion);
            return "{\"msg\": \"OK. Participación Dependencia->Categoría registrada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo registrar la Participación Dependencia->Categoría.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarObjetivo(String clave, String obj, String idcat) {
        try {
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", Integer.parseInt(idcat))
                    .getSingleResult();
            Objetivo objetivo = new Objetivo();

            objetivo.setClaveobjetivo(clave);
            objetivo.setObjetivo(obj);
            objetivo.setIdcat(cat);

            em.persist(objetivo);
            return "{\"msg\": \"OK. Objetivo insertado correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar el Objetivo.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarEstrategia(String clave, String estrat, String idobjetivo) {
        try {
            Objetivo objetivo = (Objetivo) em.createNamedQuery("Objetivo.findByIdobjetivo")
                    .setParameter("idobjetivo", Integer.parseInt(idobjetivo))
                    .getSingleResult();
            Estrategia estrategia = new Estrategia();

            estrategia.setClaveestrategia(clave);
            estrategia.setEstrategia(estrat);
            estrategia.setIdobjetivo(objetivo);

            em.persist(estrategia);
            return "{\"msg\": \"OK. Estrategia insertada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar la Estrategia.\n" + e.getMessage() + "\"}";
        }
    }

    public String insertarActividad(String activ, String inicio, String fin,
            String latitud, String longitud, String idcat, String idusuario, String iddepe) {
        
        try {
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", Integer.parseInt(idcat))
                    .getSingleResult();
            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", Integer.parseInt(idusuario))
                    .getSingleResult();
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", Integer.parseInt(iddepe))
                    .getSingleResult();
            Actividad actividad = new Actividad();

            actividad.setActividad(activ);
            actividad.setFechaini(Util.strToDate(inicio, "dd-MM-yyyy"));
            actividad.setFechafin(Util.strToDate(fin, "dd-MM-yyyy"));
            actividad.setLatitud(Float.parseFloat(latitud));
            actividad.setLongitud(Float.parseFloat(longitud));
            actividad.setIdcat(cat);
            actividad.setIdusuario(usuario);
            actividad.setIddepe(depe);

            em.persist(actividad);
            return "{\"msg\": \"OK. Actividad registrada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo insertar la actividad.\n" + e.getMessage() + "\"}";
        }
    }

    public String consultarEntidades() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Entidad> listaEntidades = em.createNamedQuery("Entidad.findAll").getResultList();

        for (int i = 0; i < listaEntidades.size(); i++) {
            listaEntidades.get(i).setMunicipioList(null);
        }

        return gson.toJson(listaEntidades);
    }

    public String consultarMunicipios() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Municipio> listaMunicipios
                = em.createNamedQuery("Municipio.findAll").getResultList();

        for (int i = 0; i < listaMunicipios.size(); i++) {
            listaMunicipios.get(i).setAdministracionList(null);
            listaMunicipios.get(i).getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaMunicipios);
    }

    public String consultarMunicipiosXIdent(String ident) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Entidad entidad = (Entidad) em.createNamedQuery("Entidad.findByIdent")
                .setParameter("ident", Integer.parseInt(ident)).getSingleResult();

        List<Municipio> listaMunicipios
                = em.createNamedQuery("Municipio.findByIdent")
                        .setParameter("ident", entidad).getResultList();

        for (int i = 0; i < listaMunicipios.size(); i++) {
            listaMunicipios.get(i).setAdministracionList(null);
            listaMunicipios.get(i).getIdent().setMunicipioList(null);
        }

        return gson.toJson(listaMunicipios);
    }

    public String consultarAdministraciones() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Administracion> listaAdministraciones
                = em.createNamedQuery("Administracion.findAll").getResultList();

        for (int i = 0; i < listaAdministraciones.size(); i++) {
            listaAdministraciones.get(i).setAdmondepeList(null);
            listaAdministraciones.get(i).setPlanList(null);
            listaAdministraciones.get(i).getIdmun().setAdministracionList(null);
            listaAdministraciones.get(i).getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaAdministraciones);
    }

    public String consultarAdministracionesXIdmun(String idmun) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Municipio mun = (Municipio) em.createNamedQuery("Municipio.findByIdmun")
                .setParameter("idmun", Integer.parseInt(idmun)).getSingleResult();
        List<Administracion> listaAdministraciones
                = em.createNamedQuery("Administracion.findByIdmun")
                        .setParameter("idmun", mun).getResultList();

        for (int i = 0; i < listaAdministraciones.size(); i++) {
            listaAdministraciones.get(i).setAdmondepeList(null);
            listaAdministraciones.get(i).setPlanList(null);
            listaAdministraciones.get(i).getIdmun().setAdministracionList(null);
            listaAdministraciones.get(i).getIdmun().getIdent().setMunicipioList(null);
        }
        return gson.toJson(listaAdministraciones);
    }

    public String consultarDependencias() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Dependencia> listaDepe = em.createNamedQuery("Dependencia.findAll").getResultList();

        for (int i = 0; i < listaDepe.size(); i++) {
            listaDepe.get(i).setActividadList(null);
            listaDepe.get(i).setAdmondepeList(null);
            listaDepe.get(i).setDepartamentoList(null);
            listaDepe.get(i).setParticipacionList(null);
        }
        return gson.toJson(listaDepe);
    }

    public String consultarAdmondepe() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Admondepe> listaAdmondepe = em.createNamedQuery("Admondepe.findAll").getResultList();

        for (int i = 0; i < listaAdmondepe.size(); i++) {
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

    public String consultarDepartamentos() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Departamento> listaDeptos = em.createNamedQuery("Departamento.findAll").getResultList();

        for (int i = 0; i < listaDeptos.size(); i++) {
            listaDeptos.get(i).setEmpleadoList(null);

            listaDeptos.get(i).getIddepe().setActividadList(null);
            listaDeptos.get(i).getIddepe().setAdmondepeList(null);
            listaDeptos.get(i).getIddepe().setDepartamentoList(null);
            listaDeptos.get(i).getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaDeptos);
    }

    public String consultarEmpleados() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Empleado> listaEmpleados = em.createNamedQuery("Empleado.findAll").getResultList();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            listaEmpleados.get(i).setUsuarioList(null);
            listaEmpleados.get(i).getIddepto().setEmpleadoList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setActividadList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setAdmondepeList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setDepartamentoList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaEmpleados);
    }

    public String consultarEmpleadosXIddepto(String iddepto) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Departamento depto = (Departamento) em.createNamedQuery("Departamento.findByIddepto")
                .setParameter("iddepto", Integer.parseInt(iddepto))
                .getSingleResult();
        List<Empleado> listaEmpleados = em.createNamedQuery("Empleado.findByIddepto")
                .setParameter("iddepto", depto).getResultList();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            listaEmpleados.get(i).setUsuarioList(null);
            listaEmpleados.get(i).getIddepto().setEmpleadoList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setActividadList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setAdmondepeList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setDepartamentoList(null);
            listaEmpleados.get(i).getIddepto().getIddepe().setParticipacionList(null);
        }
        return gson.toJson(listaEmpleados);
    }

    public String consultarRoles() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Rol> listaRoles = em.createNamedQuery("Rol.findAll").getResultList();

        for (Rol rol : listaRoles) {
            rol.setUsuariorolList(null);
        }
        return gson.toJson(listaRoles);
    }

    public String consultarUsuarios() {
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

    public String consultarUsuariorol() {
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

    public String consultarPlanes() {
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

    public String consultarCategorias() {
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

    public String consultarCategoriasXIdplan(String idplan) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Plan plan = (Plan) em.createNamedQuery("Plan.findByIdplan")
                .setParameter("idplan", Integer.parseInt(idplan))
                .getSingleResult();
        List<Categoriaplan> listaCategorias = em.createNamedQuery("Categoriaplan.findByIdplan")
                .setParameter("idplan", plan).getResultList();

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

    public String consultarParticipacion() {
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

    public String consultarObjetivos() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Objetivo> listaObjetivos = em.createNamedQuery("Objetivo.findAll").getResultList();

        for (Objetivo o : listaObjetivos) {
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

    public String consultarEstrategias() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Estrategia> listaEstrategias = em.createNamedQuery("Estrategia.findAll").getResultList();

        for (Estrategia e : listaEstrategias) {
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

    public String consultarActividades() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Actividad> listaActividades = em.createNamedQuery("Actividad.findAll").getResultList();

        for (Actividad a : listaActividades) {
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

    public String actualizarEntidad(int ident, String clave, String nombre, String abrev) {
        try {
            Entidad ent = (Entidad) em.createNamedQuery("Entidad.findByIdent")
                    .setParameter("ident", ident)
                    .getSingleResult();

            ent.setIdent(ident);
            ent.setClaveent(clave);
            ent.setNombreent(nombre);
            ent.setAbrevent(abrev);

            em.merge(ent);
            return "{\"msg\": \"OK. Entidad actualizada correctamente.\"}";
        } catch (Exception e) {
            return "{\"msg\": \"ERROR: No se pudo actualizar la Entidad.\n" + e.getMessage() + "\"}";
        }
    }

    public String actualizarMunicipio(int idmun, String clavemun, String nombremun, int ident) {
        try {
            Municipio mun = (Municipio) em.createNamedQuery("Municipio.findByIdmun")
                    .setParameter("idmun", idmun)
                    .getSingleResult();
            Entidad ent = (Entidad) em.createNamedQuery("Entidad.findByIdent")
                    .setParameter("ident", ident)
                    .getSingleResult();

            mun.setIdmun(idmun);
            mun.setClavemun(clavemun);
            mun.setNombremun(nombremun);
            mun.setIdent(ent);

            em.merge(mun);
            return "{msg:'OK. Municipio actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo actualizar el Municipio.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarAdministracion(int idadmon, String claveadmon, String nombreadmon,
            Date fechaini, Date fechafin, int idmun) {
        try {
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", idadmon)
                    .getSingleResult();
            Municipio mun = (Municipio) em.createNamedQuery("Municipio.findByIdmun")
                    .setParameter("idmun", idmun)
                    .getSingleResult();

            admon.setIdadmon(idadmon);
            admon.setClaveadmon(claveadmon);
            admon.setNombreadmon(nombreadmon);
            admon.setFechaini(fechaini);
            admon.setFechafin(fechafin);
            admon.setIdmun(mun);

            em.merge(admon);
            return "{msg:'OK. Administración actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo actualizar la Administración.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarDependencia(int iddepe, String clavedepe, String nombredepe) {
        try {
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();

            //depe.setIddepe(iddepe);
            depe.setClavedepe(clavedepe);
            depe.setNombredepe(nombredepe);

            em.merge(depe);
            return "{msg:'OK. Dependencia actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo actualizar la Dependencia.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarAdmondepe(int idadmondepe, int idadmon, int iddepe) {
        try {
            Admondepe admondepe = (Admondepe) em.createNamedQuery("Admondepe.findByIdadmondepe")
                    .setParameter("idadmondepe", idadmondepe)
                    .getSingleResult();
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", idadmon)
                    .getSingleResult();
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();

            admondepe.setIdadmon(admon);
            admondepe.setIddepe(depe);

            em.merge(admondepe);
            return "{msg:'OK. Administración -> Dependencia actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo actualizar la Administración -> Dependencia.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarDepartamento(int iddepto, String clave, String nombre, int iddepe) {
        try {
            Departamento depto = (Departamento) em.createNamedQuery("Departamento.findByIddepto")
                    .setParameter("iddepto", iddepto)
                    .getSingleResult();
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe)
                    .getSingleResult();

            depto.setClavedepto(clave);
            depto.setNombredepto(nombre);
            depto.setIddepe(depe);

            em.merge(depto);
            return "{msg: 'OK. Departamento actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo actualizar el Departamento.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarEmpleado(int idemp, String paterno, String materno, String nombre, int iddepto) {
        try {
            Empleado emp = (Empleado) em.createNamedQuery("Empleado.findByIdemp")
                    .setParameter("idemp", idemp).getSingleResult();
            Departamento depto = (Departamento) em.createNamedQuery("Departamento.findByIddepto")
                    .setParameter("iddepto", iddepto).getSingleResult();

            emp.setPaternoemp(paterno);
            emp.setMaternoemp(materno);
            emp.setNombreemp(nombre);
            emp.setIddepto(depto);

            em.merge(emp);
            return "{msg: 'OK. Empleado actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg:'ERROR: No se pudo actualizar el Empleado.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarRol(int idrol, String claverol, String nombrerol) {
        try {
            Rol rol = (Rol) em.createNamedQuery("Rol.findByIdrol")
                    .setParameter("idrol", idrol).getSingleResult();

            rol.setClaverol(claverol);
            rol.setNombrerol(nombrerol);

            em.merge(rol);
            return "{msg: 'OK. Rol actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar el Rol.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarUsuario(int idusuario, String usuario, String contrasena, int idemp) {
        try {
            Usuario u = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", idusuario).getSingleResult();
            Empleado e = (Empleado) em.createNamedQuery("Empleado.findByIdemp")
                    .setParameter("idemp", idemp).getSingleResult();

            u.setUsuario(usuario);
            u.setContrasena(contrasena);
            u.setIdemp(e);

            em.merge(u);
            return "{msg: 'OK. Usuario actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar el Usuario.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarUsuariorol(int idusuariorol, int idusuario, int idrol) {
        try {
            Usuariorol ur = (Usuariorol) em.createNamedQuery("Usuariorol.findByIdusuariorol")
                    .setParameter("idusuariorol", idusuariorol).getSingleResult();
            Usuario u = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", idusuario).getSingleResult();
            Rol r = (Rol) em.createNamedQuery("Rol.findByIdrol")
                    .setParameter("idrol", idrol).getSingleResult();

            ur.setIdusuario(u);
            ur.setIdrol(r);

            em.merge(ur);
            return "{msg: 'OK. Usuario -> Rol actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar el Usuario -> Rol.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarPlan(int idplan, String claveplan, String nombreplan, int idadmon) {
        try {
            Plan p = (Plan) em.createNamedQuery("Plan.findByIdplan")
                    .setParameter("idplan", idplan).getSingleResult();
            Administracion a = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", idadmon).getSingleResult();

            p.setClaveplan(claveplan);
            p.setNombreplan(nombreplan);
            p.setIdadmon(a);

            em.merge(p);
            return "{msg: 'OK. Plan actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar el Plan.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarCategoriaplan(int idcat, String clavecat, String nombrecat, int idcatpadre, int idplan, String descripcat) {
        try {
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat).getSingleResult();
            Plan plan = (Plan) em.createNamedQuery("Plan.findByIdplan")
                    .setParameter("idplan", idplan).getSingleResult();

            cat.setClavecat(clavecat);
            cat.setNombrecat(nombrecat);
            cat.setIdcatpadre(idcatpadre);
            cat.setIdplan(plan);
            cat.setDescripcat(descripcat);

            em.merge(cat);
            return "{msg: 'OK. Categoría del Plan actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar la Categoría.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarParticipacion(int idparticipacion, int iddepe, int idcat) {
        try {
            Participacion p = (Participacion) em.createNamedQuery("Participacion.findByIdparticipacion")
                    .setParameter("idparticipacion", idparticipacion).getSingleResult();
            Dependencia d = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe).getSingleResult();
            Categoriaplan c = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat).getSingleResult();

            p.setIddepe(d);
            p.setIdcat(c);

            em.merge(p);
            return "{msg: 'OK. Participación Dependencia -> Categoría actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar la Participación de la Dependencia en la Categoría.\n"
                    + e.getMessage() + "'}";
        }

    }

    public String actualizarObjetivo(int id, String clave, String objetivo, int idcat) {
        try {
            Objetivo o = (Objetivo) em.createNamedQuery("Objetivo.findByIdobjetivo")
                    .setParameter("idobjetivo", id).getSingleResult();
            Categoriaplan c = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat).getSingleResult();

            o.setClaveobjetivo(clave);
            o.setObjetivo(objetivo);
            o.setIdcat(c);

            em.merge(o);
            return "{msg: 'OK. Objetivo actualizado correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar el Objetivo.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarEstrategia(int idestrategia, String claveestrategia, String estrategia, int idobjetivo) {
        try {
            Estrategia e = (Estrategia) em.createNamedQuery("Estrategia.findByIdestrategia")
                    .setParameter("idestrategia", idestrategia).getSingleResult();
            Objetivo o = (Objetivo) em.createNamedQuery("Objetivo.findByIdobjetivo")
                    .setParameter("idobjetivo", idobjetivo).getSingleResult();

            e.setClaveestrategia(claveestrategia);
            e.setEstrategia(estrategia);
            e.setIdobjetivo(o);

            em.merge(e);
            return "{msg: 'OK. Estrategia actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar la Estrategia.\n" + e.getMessage() + "'}";
        }
    }

    public String actualizarActividad(int idact, String activ, Date inicio, Date fin,
            Float latitud, Float longitud, int idcat, int idusuario, int iddepe) {
        try {
            Actividad act = (Actividad) em.createNamedQuery("Actividad.findByIdactividad")
                    .setParameter("idactividad", idact).getSingleResult();
            Categoriaplan cat = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", idcat).getSingleResult();
            Usuario usu = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", idusuario).getSingleResult();
            Dependencia dep = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", iddepe).getSingleResult();

            act.setActividad(activ);
            act.setFechaini(inicio);
            act.setFechafin(fin);
            act.setLatitud(latitud);
            act.setLongitud(longitud);
            act.setIdcat(cat);
            act.setIdusuario(usu);
            act.setIddepe(dep);

            em.merge(act);
            return "{msg: 'OK. Actividad actualizada correctamente.'}";
        } catch (Exception e) {
            return "{msg: 'ERROR: No se pudo actualizar la Actividad.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarEntidad(String ident) {
        try {
            Entidad ent = (Entidad) em.createNamedQuery("Entidad.findByIdent")
                    .setParameter("ident", Integer.parseInt(ident)).getSingleResult();

            em.remove(ent);
            return "{msg: 'OK. La Entidad fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Entidad debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarMunicipio(String idmun) {
        try {
            Municipio mun = (Municipio) em.createNamedQuery("Municipio.findByIdmun")
                    .setParameter("idmun", Integer.parseInt(idmun)).getSingleResult();

            em.remove(mun);
            return "{msg: 'OK. El Municipio fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Municipio debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarAdministracion(String idadmon) {
        try {
            Administracion admon = (Administracion) em.createNamedQuery("Administracion.findByIdadmon")
                    .setParameter("idadmon", Integer.parseInt(idadmon)).getSingleResult();

            em.remove(admon);
            return "{msg: 'OK. La Administración fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Administración debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarDependencia(String iddepe) {
        try {
            Dependencia depe = (Dependencia) em.createNamedQuery("Dependencia.findByIddepe")
                    .setParameter("iddepe", Integer.parseInt(iddepe)).getSingleResult();

            em.remove(depe);
            return "{msg: 'OK. La Dependencia fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Dependencia debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarAdmondepe(String idadmondepe) {
        try {
            Admondepe ad = (Admondepe) em.createNamedQuery("Admondepe.findByIdadmondepe")
                    .setParameter("idadmondepe", Integer.parseInt(idadmondepe)).getSingleResult();

            em.remove(ad);
            return "{msg: 'OK. La relación Administración -> Dependencia fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de Administracion -> Dependencia debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarDepartamento(String iddepto) {
        try {
            Departamento d = (Departamento) em.createNamedQuery("Departamento.findByIddepto")
                    .setParameter("iddepto", Integer.parseInt(iddepto)).getSingleResult();

            em.remove(d);
            return "{msg: 'OK. El Departamento fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Departamento debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarEmpleado(String idemp) {
        try {
            Empleado e = (Empleado) em.createNamedQuery("Empleado.findByIdemp")
                    .setParameter("idemp", Integer.parseInt(idemp)).getSingleResult();

            em.remove(e);
            return "{msg: 'OK. El Empleado fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Empleado debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarRol(String idrol) {
        try {
            Rol r = (Rol) em.createNamedQuery("Rol.findByIdrol")
                    .setParameter("idrol", Integer.parseInt(idrol)).getSingleResult();

            em.remove(r);
            return "{msg: 'OK. El Rol fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Rol debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarUsuario(String idusuario) {
        try {
            Usuario u = (Usuario) em.createNamedQuery("Usuario.findByIdusuario")
                    .setParameter("idusuario", Integer.parseInt(idusuario)).getSingleResult();

            em.remove(u);
            return "{msg: 'OK. El Usuario fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Usuario debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarUsuariorol(String idusuariorol) {
        try {
            Usuariorol ur = (Usuariorol) em.createNamedQuery("Usuariorol.findByIdusuariorol")
                    .setParameter("idusuariorol", Integer.parseInt(idusuariorol)).getSingleResult();

            em.remove(ur);
            return "{msg: 'OK. El Usuario -> Rol fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Usuario -> Rol debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarPlan(String idplan) {
        try {
            Plan p = (Plan) em.createNamedQuery("Plan.findByIdplan")
                    .setParameter("idplan", Integer.parseInt(idplan)).getSingleResult();

            em.remove(p);
            return "{msg: 'OK. El Plan fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Plan debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarCategoriaplan(String idcat) {
        try {
            Categoriaplan c = (Categoriaplan) em.createNamedQuery("Categoriaplan.findByIdcat")
                    .setParameter("idcat", Integer.parseInt(idcat)).getSingleResult();

            em.remove(c);
            return "{msg: 'OK. La Categoría fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Categoría debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarParticipacion(String idparticipacion) {
        try {
            Participacion p = (Participacion) em.createNamedQuery("Participacion.findByIdparticipacion")
                    .setParameter("idparticipacion", Integer.parseInt(idparticipacion)).getSingleResult();

            em.remove(p);
            return "{msg: 'OK. La Participación Dependencia -> Categoría fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Participación debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarObjetivo(String idobjetivo) {
        try {
            Objetivo o = (Objetivo) em.createNamedQuery("Objetivo.findByIdobjetivo")
                    .setParameter("idobjetivo", Integer.parseInt(idobjetivo)).getSingleResult();

            em.remove(o);
            return "{msg: 'OK. El Objetivo fue eliminado correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID del Objetivo debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarEstrategia(String idestrategia) {
        try {
            Estrategia e = (Estrategia) em.createNamedQuery("Estrategia.findByIdestrategia")
                    .setParameter("idestrategia", Integer.parseInt(idestrategia)).getSingleResult();

            em.remove(e);
            return "{msg: 'OK. La Estrategia fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Estrategia debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }

    public String eliminarActividad(String idactividad) {
        try {
            Actividad a = (Actividad) em.createNamedQuery("Actividad.findByIdactividad")
                    .setParameter("idactividad", Integer.parseInt(idactividad)).getSingleResult();

            em.remove(a);
            return "{msg: 'OK. La Actividad fue eliminada correctamente.'}";
        } catch (NumberFormatException e) {
            return "{msg: 'ERROR: El ID de la Actividad debe ser numérico.\n" + e.getMessage() + "'}";
        }
    }
}
