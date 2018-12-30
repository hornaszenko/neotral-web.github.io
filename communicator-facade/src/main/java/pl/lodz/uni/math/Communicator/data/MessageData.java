package pl.lodz.uni.math.Communicator.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageData {

    private Long id;

    private String content;

    private String author;

    private String time;
}
