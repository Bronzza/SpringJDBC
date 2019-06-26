package application.configuration;

import application.annotations.InjectRandomInt;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

@Log4j
@Component
public class InjectRandomIntBeanPostProcess implements BeanPostProcessor {
   @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
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
        return bean;
    }
}
