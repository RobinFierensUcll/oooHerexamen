package controller;

import controller.factories.DbFactory;
import controller.factories.DomainFactory;

public class Controller {
    private DbFactory dbFactory;
    private DomainFactory domainFactory;

    public Controller() {
        dbFactory = new DbFactory();
        domainFactory = new DomainFactory();
    }

    public DbFactory getDbFactory() {
        return this.dbFactory;
    }

    public DomainFactory getDomainFactory() {
        return this.domainFactory;
    }

}
