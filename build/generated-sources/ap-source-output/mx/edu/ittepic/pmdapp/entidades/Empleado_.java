package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Departamento;
import mx.edu.ittepic.pmdapp.entidades.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-22T23:02:38")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> paternoemp;
    public static volatile SingularAttribute<Empleado, String> maternoemp;
    public static volatile ListAttribute<Empleado, Usuario> usuarioList;
    public static volatile SingularAttribute<Empleado, Integer> idemp;
    public static volatile SingularAttribute<Empleado, Departamento> iddepto;
    public static volatile SingularAttribute<Empleado, String> nombreemp;

}