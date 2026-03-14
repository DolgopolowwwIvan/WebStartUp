package ru.pmi.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardUtils {

    public static ReplyKeyboardMarkup getMainKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(); // объект клава

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("/start"));
        row1.add(new KeyboardButton("/book"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("/info"));
        row2.add(new KeyboardButton("/help"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("📋 Забронировать столик"));
        row3.add(new KeyboardButton("ℹ️ О боте"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        // Настраиваем клавиатуру
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true); // изменение размера клавиатуры
        keyboardMarkup.setInputFieldPlaceholder("Выберите действие...");
        keyboardMarkup.setOneTimeKeyboard(false); // клавиатура остаётся после использования
        keyboardMarkup.setSelective(false); // для всех пользователей

        return keyboardMarkup;
    }
}