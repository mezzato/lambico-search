package org.lambico.search.example.test;

import org.lambico.test.spring.hibernate.DBTest;
import org.lambico.search.example.po.Recipe;
import org.lambico.search.example.po.Person;

public abstract class BaseTest extends DBTest {

//    @SuppressWarnings(value = "unchecked")
//    protected ApplicationContext getTestContext() {
//        BeanFactoryLocator bfl = SingletonBeanFactoryLocator.getInstance(
//                "beanRefFactory_test.xml");
//        BeanFactoryReference bf = bfl.useBeanFactory("org.parancoe.example");
//        ApplicationContext lctx = (ApplicationContext) bf.getFactory();
//        Map daoMap = (Map) lctx.getBean("daoMap");
//        Map ldaos = DaoUtils.getDaos(lctx);
//        daoMap.putAll(ldaos);
//        return lctx;
//    }
    @Override
    public Class[] getFixtureClasses() {
        return new Class[]{Person.class, Recipe.class};
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{
                    "classpath:org/lambico/spring/dao/hibernate/genericDao.xml",
                    "classpath:org/lambico/spring/dao/hibernate/applicationContextBase.xml",
                    "classpath:database_test.xml",
                    "classpath:applicationContext.xml"
                };
    }
}
