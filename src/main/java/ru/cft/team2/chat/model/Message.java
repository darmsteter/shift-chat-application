package ru.cft.team2.chat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.*;

@Entity(name = "messages")
@ApiModel(description = "Сообщение")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(
            value = "Идентификатор сообщения",
            required = true,
            example = "1"
    )
    private Integer messageId;

    @Column(nullable = false)
    @ApiModelProperty(
            value = "Идентификатор автора сообщения",
            required = true,
            example = "1"
    )
    private Integer userId;

    @ApiModelProperty(
            value = "Идентификатор чата",
            example = "1"
    )
    private Integer chatId;

    @Column(nullable = false)
    @ApiModelProperty(
            value = "Текст сообщения",
            required = true,
            example = "Adventure time"
    )
    private String text;

    @Column(nullable = false)
    @ApiModelProperty(
            value = "Время отправки сообщения",
            required = true,
            example = "2021-07-12 17:46:00"
    )
    private String sendTime;

    @Column(nullable = false)
    @ApiModelProperty(
            value = "Время отправки сообщения в секундах",
            required = true,
            example = "15"
    )
    private Long sendTimeSec;

    @ApiModelProperty(
            value = "Время жизни сообщения в секундах",
            example = "10"
    )
    private Long lifetimeSec;

    @ApiModelProperty(
            value = "Количество секунд задержки отправки сообщения",
            example = "10"
    )
    private Long delaySec;

    @ManyToMany(fetch = FetchType.LAZY)
    @ApiModelProperty(
            value = "Список пользователей чата, которые не прочитали данное сообщение"
    )
    private List<User> usersWhoDidNotRead = new ArrayList<>();

    public Message(Integer messageId, Integer userId, Integer chatId, String text, String sendTime) {
        this.messageId = messageId;
        this.userId = userId;
        this.chatId = chatId;
        this.text = text;
        this.sendTime = sendTime;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Long getSendTimeSec() {
        return sendTimeSec;
    }

    public void setSendTimeSec(Long sendTimeSec) {
        this.sendTimeSec = sendTimeSec;
    }

    public Long getLifetimeSec() {
        return lifetimeSec;
    }

    public void setLifetimeSec(Long lifetimeSec) {
        this.lifetimeSec = lifetimeSec;
    }

    public Long getDelaySec() {
        return delaySec;
    }

    public void setDelaySec(Long delaySec) {
        this.delaySec = delaySec;
    }

    public List<User> getUsersWhoDidNotRead() {
        return usersWhoDidNotRead;
    }

    public void setUsersWhoDidNotRead(List<User> usersWhoDidNotRead) {
        this.usersWhoDidNotRead = usersWhoDidNotRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", chatId=" + chatId +
                ", text='" + text + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", sendTimeInMillis=" + sendTimeSec +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) && Objects.equals(userId, message.userId) && Objects.equals(chatId, message.chatId) && Objects.equals(text, message.text) && Objects.equals(sendTime, message.sendTime) && Objects.equals(sendTimeSec, message.sendTimeSec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, userId, chatId, text, sendTime, sendTimeSec);
    }
}
