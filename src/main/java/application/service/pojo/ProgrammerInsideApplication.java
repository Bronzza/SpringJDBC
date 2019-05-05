package application.service.pojo;

import application.annotations.InjectRandomInt;
import application.entities.Programmer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@ToString(callSuper = true)
@AllArgsConstructor
public class ProgrammerInsideApplication extends Programmer {

    @InjectRandomInt(min = 1, max = 10)
    private int luckLevel;

    @PostConstruct
    private void init() {
    }
}
