package com.mycompany.tcs.model;

import com.mycompany.tcs.model.Adminorg;
import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.model.Forumques;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.model.Notifications;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-21T14:35:55")
@StaticMetamodel(Employees.class)
public class Employees_ { 

    public static volatile CollectionAttribute<Employees, Forumanswer> forumanswerCollection;
    public static volatile CollectionAttribute<Employees, Notifications> notificationsCollection1;
    public static volatile SingularAttribute<Employees, Integer> loginStatus;
    public static volatile CollectionAttribute<Employees, Notifications> notificationsCollection;
    public static volatile SingularAttribute<Employees, String> depName;
    public static volatile SingularAttribute<Employees, Integer> eid;
    public static volatile SingularAttribute<Employees, String> password;
    public static volatile SingularAttribute<Employees, String> phoneNo;
    public static volatile CollectionAttribute<Employees, Forumanswer> forumanswerCollection1;
    public static volatile SingularAttribute<Employees, String> address;
    public static volatile SingularAttribute<Employees, String> email;
    public static volatile SingularAttribute<Employees, String> dob;
    public static volatile SingularAttribute<Employees, String> ename;
    public static volatile CollectionAttribute<Employees, Forumques> forumquesCollection;
    public static volatile SingularAttribute<Employees, Integer> verifykey;
    public static volatile CollectionAttribute<Employees, Messages> messagesCollection;
    public static volatile SingularAttribute<Employees, Adminorg> adminEid;

}