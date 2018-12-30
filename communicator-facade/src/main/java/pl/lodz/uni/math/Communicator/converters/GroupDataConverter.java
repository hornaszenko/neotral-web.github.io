package pl.lodz.uni.math.Communicator.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.data.GroupData;
import pl.lodz.uni.math.model.GroupModel;

@Component
public class GroupDataConverter implements Converter<GroupModel, GroupData> {

    @Override
    public GroupData convert(GroupModel groupModel) {
        GroupData groupData = new GroupData();

        groupData.setId(groupModel.getId());
        groupData.setName(groupModel.getName());
        groupData.setPrivateGroup(groupModel.getPrivateGroup());
        groupData.setImagePath(groupModel.getImage());

        return groupData;
    }
}
