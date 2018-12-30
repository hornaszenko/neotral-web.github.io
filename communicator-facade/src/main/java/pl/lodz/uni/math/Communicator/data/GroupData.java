package pl.lodz.uni.math.Communicator.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupData {

    private Long id;

    private String name;

    private boolean privateGroup;

    private String imagePath;

    private boolean member;
}
