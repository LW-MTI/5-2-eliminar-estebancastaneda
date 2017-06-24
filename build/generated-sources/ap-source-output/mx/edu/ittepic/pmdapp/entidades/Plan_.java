package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Administracion;
import mx.edu.ittepic.pmdapp.entidades.Categoriaplan;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T21:28:45")
@StaticMetamodel(Plan.class)
public class Plan_ { 

    public static volatile SingularAttribute<Plan, String> claveplan;
    public static volatile SingularAttribute<Plan, Integer> idplan;
    public static volatile SingularAttribute<Plan, Administracion> idadmon;
    public static volatile ListAttribute<Plan, Categoriaplan> categoriaplanList;
    public static volatile SingularAttribute<Plan, String> nombreplan;

}