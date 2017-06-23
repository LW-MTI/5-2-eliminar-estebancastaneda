package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Administracion;
import mx.edu.ittepic.pmdapp.entidades.Entidad;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-22T23:02:38")
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile SingularAttribute<Municipio, String> clavemun;
    public static volatile SingularAttribute<Municipio, Entidad> ident;
    public static volatile ListAttribute<Municipio, Administracion> administracionList;
    public static volatile SingularAttribute<Municipio, Integer> idmun;
    public static volatile SingularAttribute<Municipio, String> nombremun;

}