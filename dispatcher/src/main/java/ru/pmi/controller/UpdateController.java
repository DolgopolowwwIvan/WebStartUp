package ru.pmi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Log4j
public class UpdateController {
    private TelegramBot telegramBot;

    public void registerBot(TelegramBot telegramBot){
        this.telegramBot = telegramBot;
    }

    // Первичная валидация входящих данных
    public void processUpdate(Update update){
        if(update == null){
            log.error("Received update is null");
            return;
        }

        // Работает только с сообщениями из приватных чатов.
        // То есть исправленные или пересланные сообщения не обрабатываются
        if(update.getMessage() != null){
            distributeMessagesByType(update);
        }else {
            log.error("Received unsupported message type " + update);
        }
    }

    private void distributeMessagesByType(Update update) {

    }

    private void setUnsupportedMessageTypeView(Update update) {
    }

    private void processPhotoMessage(Update update) {
    }

    private void processDocMessage(Update update) {
    }

    private void processTextMessage(Update update) {
    }
}
