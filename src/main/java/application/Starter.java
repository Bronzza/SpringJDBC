package application;

import application.configuration.SpringHibernateSessionConfig;
import application.dao.ProgrammerDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import application.service.pojo.ProgrammerInsideApplication;
import application.service.ProgrammerService;

import java.util.List;

public class Starter {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringHibernateSessionConfig.class);
        ProgrammerDao programmerDao = context.getBean(ProgrammerDao.class);
        ProgrammerService programmerService = context.getBean(ProgrammerService.class);
       programmerDao.getAll().forEach(System.out::println);
        System.out.println();
        List<ProgrammerInsideApplication> all = (List<ProgrammerInsideApplication>) programmerService.getAll();
        all.forEach(System.out::println);

    }
}
