package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Categoriaplan;
import mx.edu.ittepic.pmdapp.entidades.Estrategia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-23T21:28:45")
@StaticMetamodel(Objetivo.class)
public class Objetivo_ { 

    public static volatile SingularAttribute<Objetivo, Integer> idobjetivo;
    public static volatile SingularAttribute<Objetivo, String> objetivo;
    public static volatile SingularAttribute<Objetivo, String> claveobjetivo;
    public static volatile SingularAttribute<Objetivo, Categoriaplan> idcat;
    public static volatile ListAttribute<Objetivo, Estrategia> estrategiaList;

}