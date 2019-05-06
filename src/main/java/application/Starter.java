package application;

import application.configuration.DataSourseConfigurator;
import application.dao.ProgrammerDao;
import application.entities.Programmer;
import application.service.ProgrammerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DataSourseConfigurator.class);

        ProgrammerDao programmerDao = context.getBean(ProgrammerDao.class);
        programmerDao.getAll().forEach(System.out::println);
        ProgrammerService programmerService = context.getBean(ProgrammerService.class);
        programmerService.getAllProgrammers().get().forEach(System.out::println);
//        System.out.println(programmerDao.get(7L));
//        Programmer id7Programmer = programmerDao.get(7L);
//        id7Programmer.setName("Mikle");
//        programmerDao.update(id7Programmer);
//        System.out.println();
//        System.out.println(programmerDao.get(7L));
//        programmerDao.delete("Mikle");
//        System.out.println();
//        programmerDao.getAll().forEach(System.out::println);
//        id7Programmer.setName("Douglas");
//        programmerDao.save(id7Programmer);
//        System.out.println();
//        programmerDao.getAll().forEach(System.out::println);

    }
}
