package com.mycompany.tcs.model;

import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Forumques;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-21T14:35:54")
@StaticMetamodel(Forumanswer.class)
public class Forumanswer_ { 

    public static volatile SingularAttribute<Forumanswer, Employees> rateEid;
    public static volatile SingularAttribute<Forumanswer, Integer> rate;
    public static volatile SingularAttribute<Forumanswer, String> answer;
    public static volatile SingularAttribute<Forumanswer, Forumques> qid;
    public static volatile SingularAttribute<Forumanswer, Employees> postEid;
    public static volatile SingularAttribute<Forumanswer, Date> datePost;
    public static volatile SingularAttribute<Forumanswer, Integer> ansid;

}