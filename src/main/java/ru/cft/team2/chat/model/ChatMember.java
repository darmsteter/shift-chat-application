package ru.cft.team2.chat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Участник чата")
public class ChatMember {
    @ApiModelProperty(
            value = "Идентификатор пользователя",
            required = true,
            example = "1"
    )
    Integer userId;

    @ApiModelProperty(
            value = "Идентификатор чата",
            required = true,
            example = "1"
    )
    Integer chatId;

    public ChatMember(Integer userId, Integer chatId) {
        this.userId = userId;
        this.chatId = chatId;
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

    @Override
    public String toString() {
        return "ChatMember{" +
                "userId=" + userId +
                ", chatId=" + chatId +
                '}';
    }
}
