package com.mycompany.tcs.model;

import com.mycompany.tcs.model.Employees;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-21T14:35:54")
@StaticMetamodel(Notifications.class)
public class Notifications_ { 

    public static volatile SingularAttribute<Notifications, Employees> receiveEid;
    public static volatile SingularAttribute<Notifications, Integer> nid;
    public static volatile SingularAttribute<Notifications, String> notificationType;
    public static volatile SingularAttribute<Notifications, Employees> genEid;

}