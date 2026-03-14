package ru.pmi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
@Log4j
public class TelegramBot extends TelegramLongPollingBot {

    private final CommandControl commandControl;
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    private UpdateController updateController;

    public TelegramBot(UpdateController updateController, CommandControl commandControl){
        this.updateController = updateController;
        this.commandControl = commandControl;
    }

    @PostConstruct
    public void init(){
        updateController.registerBot(this);
        commandControl.registerBot(this);
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateController.processUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    // Возврат сообщения в часть телеграмма
    public void sendAnswerMessage(SendMessage message){
        if (message != null){
            try{
                execute(message);
            } catch (TelegramApiException e){
                log.error(e);
            }
        }
    }

}
