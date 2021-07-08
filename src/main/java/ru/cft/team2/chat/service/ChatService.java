package ru.cft.team2.chat.service;

import org.springframework.stereotype.Service;
import ru.cft.team2.chat.model.Chat;
import ru.cft.team2.chat.model.ChatView;
import ru.cft.team2.chat.repository.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatView create(Chat someChat) throws Exception {
        if (someChat.getName() == null || someChat.getName().equals("")) {
            throw new Exception();
        }
        return new ChatView(chatRepository.save(someChat));
    }
}
