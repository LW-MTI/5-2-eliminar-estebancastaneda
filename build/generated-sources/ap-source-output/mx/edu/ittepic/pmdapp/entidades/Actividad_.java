package mx.edu.ittepic.pmdapp.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Categoriaplan;
import mx.edu.ittepic.pmdapp.entidades.Dependencia;
import mx.edu.ittepic.pmdapp.entidades.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T21:28:45")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, Float> latitud;
    public static volatile SingularAttribute<Actividad, Float> longitud;
    public static volatile SingularAttribute<Actividad, Date> fechaini;
    public static volatile SingularAttribute<Actividad, Date> fechafin;
    public static volatile SingularAttribute<Actividad, Dependencia> iddepe;
    public static volatile SingularAttribute<Actividad, Integer> idactividad;
    public static volatile SingularAttribute<Actividad, Categoriaplan> idcat;
    public static volatile SingularAttribute<Actividad, String> actividad;
    public static volatile SingularAttribute<Actividad, Usuario> idusuario;

}