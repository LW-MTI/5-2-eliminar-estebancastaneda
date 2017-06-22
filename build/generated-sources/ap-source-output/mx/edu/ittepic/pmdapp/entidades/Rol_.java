package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Usuariorol;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-21T21:13:08")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, String> claverol;
    public static volatile SingularAttribute<Rol, Integer> idrol;
    public static volatile SingularAttribute<Rol, String> nombrerol;
    public static volatile ListAttribute<Rol, Usuariorol> usuariorolList;

}