package application.configuration;

import application.annotations.InjectRandomInt;
import application.service.pojo.ProgrammerInsideApplication;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;


@Log4j
public class InjectRandomIntBeanPostProcess implements BeanFactoryPostProcessor, BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getFields();
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                Random rnd = new Random();
                int result = (min + rnd.nextInt(max - min));
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, result);
            }
        }
        log.info("Before init message " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ProgrammerInsideApplication) {
            log.info("Before destroy message " + beanName);
        }
        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        configurableListableBeanFactory.addBeanPostProcessor(this);
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);

        }
    }

}
