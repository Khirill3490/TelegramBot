package com.skillbox.cryptobot.bot.command;

import com.skillbox.cryptobot.models.User;
import com.skillbox.cryptobot.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;


/**
 * Обработка команды начала работы с ботом
 */
@Service
@AllArgsConstructor
@Slf4j
public class StartCommand implements IBotCommand {

    private final UserRepository userRepository;

    @Override
    public String getCommandIdentifier() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Запускает бота";
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());

        answer.setText("""
                Привет! Данный бот помогает отслеживать стоимость биткоина.
                Поддерживаемые команды:
                 /get_price - получить стоимость биткоина
                 /get_subscription - Возвращает текущую подписку
                """);

//        answer.setText("Привет! Данный бот помогает отслеживать стоимость биткоина. " +
//                "Поддерживаемые команды: \n" +
//                getpc.getCommandIdentifier() + " - " + getpc.getDescription() + "\n" +
//                getsc.getCommandIdentifier() + " - " + getsc.getDescription()
//        );
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            log.error("Error occurred in /start command", e);
        }
    }
    private void registerUser(Message msg) {
        long uuId = msg.getFrom().getId();
        if (userRepository.findByUserUUID(uuId).isEmpty()) {
            User user = new User();
            user.setUserUUID(uuId);
            user.setName(msg.getChat().getFirstName());
            user.setUserId(msg.getChatId());
            user.setSubscriptionPrice(null);

            userRepository.save(user);
            log.info("Пользователь сохранён " + user);
        }
    }
}