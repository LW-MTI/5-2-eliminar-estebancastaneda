package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Dependencia;
import mx.edu.ittepic.pmdapp.entidades.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-22T23:02:38")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, String> clavedepto;
    public static volatile SingularAttribute<Departamento, String> nombredepto;
    public static volatile ListAttribute<Departamento, Empleado> empleadoList;
    public static volatile SingularAttribute<Departamento, Integer> iddepto;
    public static volatile SingularAttribute<Departamento, Dependencia> iddepe;

}