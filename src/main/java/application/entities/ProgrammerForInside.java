package application.entities;

import application.annotations.InjectRandomInt;
import lombok.ToString;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ToString (callSuper = true)
public class ProgrammerForInside extends Programmer {

    @InjectRandomInt(min = 0, max = 10)
    private int luckLevel;

    @PostConstruct
    private void init() {
    }
}
