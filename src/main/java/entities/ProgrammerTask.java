package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tasks")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ProgrammerTask extends Task {

    @Column (name = "name")
    private String name;

    @Column (name = "dead_line")
    private Date deadLineDate;

    @ManyToOne
    @JoinColumn (name="id", nullable = false, insertable = false, updatable = false)
    private Programmer programmer;
}
