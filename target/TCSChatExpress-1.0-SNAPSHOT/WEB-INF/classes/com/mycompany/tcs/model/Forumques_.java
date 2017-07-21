package com.mycompany.tcs.model;

import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Forumanswer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-21T14:35:55")
@StaticMetamodel(Forumques.class)
public class Forumques_ { 

    public static volatile CollectionAttribute<Forumques, Forumanswer> forumanswerCollection;
    public static volatile SingularAttribute<Forumques, Integer> qid;
    public static volatile SingularAttribute<Forumques, Employees> postEid;
    public static volatile SingularAttribute<Forumques, String> question;
    public static volatile SingularAttribute<Forumques, Date> datePost;

}