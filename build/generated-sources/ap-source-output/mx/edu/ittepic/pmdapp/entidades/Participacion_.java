package mx.edu.ittepic.pmdapp.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.edu.ittepic.pmdapp.entidades.Categoriaplan;
import mx.edu.ittepic.pmdapp.entidades.Dependencia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T22:37:05")
@StaticMetamodel(Participacion.class)
public class Participacion_ { 

    public static volatile SingularAttribute<Participacion, Integer> idparticipacion;
    public static volatile SingularAttribute<Participacion, Dependencia> iddepe;
    public static volatile SingularAttribute<Participacion, Categoriaplan> idcat;

}