package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Actividad;
import mx.edu.ittepic.pmdapp.entidades.Empleado;
import mx.edu.ittepic.pmdapp.entidades.Usuariorol;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-21T21:13:08")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile ListAttribute<Usuario, Actividad> actividadList;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, Empleado> idemp;
    public static volatile SingularAttribute<Usuario, Integer> idusuario;
    public static volatile ListAttribute<Usuario, Usuariorol> usuariorolList;

}