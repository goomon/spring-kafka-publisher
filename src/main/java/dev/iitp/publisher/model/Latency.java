package dev.iitp.publisher.model;

import lombok.Getter;
import lombok.Setter;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import java.time.LocalDateTime;

//@Entity
@Getter @Setter
public class Latency {

//    @Id @GeneratedValue
    private Long id;
    private int nodeId;
    private LocalDateTime timestamp;
    private long latencyMilli;
}
