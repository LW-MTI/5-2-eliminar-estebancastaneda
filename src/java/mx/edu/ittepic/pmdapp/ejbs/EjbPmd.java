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
    
    public String consultarEntidades() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Entidad> listaEntidades
                = em.createNamedQuery("Entidad.findAll").getResultList();

        for (int i = 0; i < listaEntidades.size(); i++) {
            listaEntidades.get(i).setMunicipioList(null);
        }

        return gson.toJson(listaEntidades);
    }

}
