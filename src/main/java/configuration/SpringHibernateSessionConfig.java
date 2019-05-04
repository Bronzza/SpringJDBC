package configuration;


import entities.Programmer;
import entities.ProgrammerTask;
import entities.Task;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import utils.HibernateUtil;

@Configuration
@ComponentScan("dao")
@ComponentScan ("service")
public class SpringHibernateSessionConfig {
    @Bean
    public SessionFactory configuration() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(Programmer.class);
        configuration.addAnnotatedClass(ProgrammerTask.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
