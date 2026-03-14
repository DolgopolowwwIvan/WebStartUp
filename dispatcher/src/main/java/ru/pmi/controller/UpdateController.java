package ru.pmi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Log4j
public class UpdateController {
    private TelegramBot telegramBot;
    private CommandControl commandControl;

    public UpdateController(CommandControl commandControl) { // конструктор команд
        this.commandControl = commandControl;
    }

    public void registerBot(TelegramBot telegramBot){
        this.telegramBot = telegramBot;
    }

    // Первичная валидация входящих данных
    public void processUpdate(Update update){
        if(update == null){
            log.error("Received update is null");
            return;
        }

        // Передаём обработку в CommandControl
        if (update.hasMessage() && update.getMessage().hasText()) {
            commandControl.handleCommand(update);
        } else {
            log.error("Received unsupported message type: " + update);
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
