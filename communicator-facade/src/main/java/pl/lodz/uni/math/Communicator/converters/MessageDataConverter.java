package pl.lodz.uni.math.Communicator.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.data.MessageData;
import pl.lodz.uni.math.model.MessageModel;

import java.text.SimpleDateFormat;

@Component
public class MessageDataConverter implements Converter<MessageModel, MessageData> {

    @Override
    public MessageData convert(MessageModel messageModel) {
        MessageData messageData = new MessageData();

        messageData.setId(messageModel.getId());
        messageData.setAuthor(messageModel.getAuthor().getFirstName() + ' ' + messageModel.getAuthor().getSurname());
        messageData.setContent(messageModel.getContent());
        messageData.setTime(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(messageModel.getCreationDate()));

        return messageData;
    }
}
