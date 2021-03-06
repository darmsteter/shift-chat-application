package ru.cft.team2.chat.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.cft.team2.chat.model.ChatView;
import ru.cft.team2.chat.model.Feed;
import ru.cft.team2.chat.model.Message;
import ru.cft.team2.chat.parser.RSSFeedParser;
import ru.cft.team2.chat.service.ChatService;
import ru.cft.team2.chat.service.MessageService;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    private final MessageService messageService;
    private final ChatService chatService;

    public ScheduleConfig(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduledDatabaseUpdate() {
        messageService.deleteOldMessages();
    }

    @Scheduled(fixedDelay = 600000)
    public void scheduledRssUpdate() {
        List<ChatView> availableChats = chatService.getAll();
        for (ChatView someChat : availableChats) {
            if (someChat.getRssLink() != null) {
                try {
                    RSSFeedParser rssFeedParser = new RSSFeedParser(someChat.getRssLink());
                    Feed feed = rssFeedParser.readFeed();
                    long currentTimeInMillis = System.currentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Message rssMessage = new Message(null, -1, someChat.getChatId(), "Последняя новость в ленте: " + feed.getMessages().get(0).toString(), formatter.format(currentTimeInMillis));
                    rssMessage.setSendTimeSec(currentTimeInMillis / 1000);
                    messageService.create(rssMessage);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
