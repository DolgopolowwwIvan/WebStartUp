package ru.pmi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pmi.utils.KeyboardUtils;

@Component
@Log4j
public class CommandControl {

    private TelegramBot telegramBot;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void handleCommand(Update update) {
        // Проверяем, что это сообщение и оно содержит текст
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            log.error("Получено сообщение без текста");
            return;
        }

        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        log.debug("Обработка команды: " + messageText + " от чата: " + chatId);

        // Создаём объект ответного сообщения
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        // Обрабатываем команды
        switch (messageText) {
            case "/start":
                message.setText("Добро пожаловать в бота для бронирования игрового столика \"Монополия\"! \n\nВыберите действие из меню:");
                message.setReplyMarkup(KeyboardUtils.getMainKeyboard());
                break;

            case "/book":
            case "📋 Забронировать столик":
                message.setText("Для бронирования столика укажите:\n1. Дата (например: 25.03.2024)\n2. Время (например: 19:00)\n3. Количество гостей");
                break;

            case "/info":
            case "ℹ️ О боте":
                message.setText("Этот бот помогает бронировать столики в Rich Club.\n\nДоступные команды:\n" +
                        "/book - забронировать столик\n" +
                        "/info - информация о боте\n" +
                        "/help - помощь");
                break;

            case "/help":
                message.setText("Помощь по использованию бота:\n" +
                        "- Используйте кнопки меню для навигации\n" +
                        "- Для бронирования нажмите 📋 Забронировать столик\n" +
                        "- По всем вопросам обращайтесь к администратору");
                message.setReplyMarkup(KeyboardUtils.getMainKeyboard());
                break;

            default:
                // Если команда не распознана
                message.setText("Неизвестная команда. Пожалуйста, используйте кнопки меню.");
                message.setReplyMarkup(KeyboardUtils.getMainKeyboard());
                break;
        }

        sendMessage(message); // Отправляем ответ через бота
    }

    private void sendMessage(SendMessage message) {
        if (telegramBot != null) {
            telegramBot.sendAnswerMessage(message);
        } else {
            log.error("TelegramBot не инициализирован в CommandControl");
        }
    }
}