package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Municipio;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-22T23:02:38")
@StaticMetamodel(Entidad.class)
public class Entidad_ { 

    public static volatile SingularAttribute<Entidad, String> nombreent;
    public static volatile SingularAttribute<Entidad, Integer> ident;
    public static volatile SingularAttribute<Entidad, String> abrevent;
    public static volatile ListAttribute<Entidad, Municipio> municipioList;
    public static volatile SingularAttribute<Entidad, String> claveent;

}