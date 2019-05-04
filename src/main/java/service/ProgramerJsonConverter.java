package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entities.Programmer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ProgramerJsonConverter {
    private ObjectMapper objectMapper = new ObjectMapper();
//    private ObjectWriter objectWriter = objectMapper.writer();


    public String toJsonProgrammer (Programmer programmer){
        try {
            return objectMapper.writeValueAsString(programmer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJsonProgramersList (List<Programmer> programmers){
        try {
            return objectMapper.writeValueAsString(programmers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
