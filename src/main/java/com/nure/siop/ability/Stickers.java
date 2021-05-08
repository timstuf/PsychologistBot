package com.nure.siop.ability;

import org.telegram.telegrambots.api.methods.send.SendSticker;

public enum Stickers {
    FUNNY_JIM_CARREY("CAADBQADiQMAAukKyAPZH7wCI2BwFxYE"),
    ANTON_NAHUY("CAACAgIAAxkBAAECSCVgln1TUrOs6JKJ6hFJfKjJqC5xuwACWwADSCDiGCuKZxwQhvirHwQ"),
    ;

    String stickerId;

    Stickers(String stickerId) {
        this.stickerId = stickerId;
    }

    public SendSticker getSendSticker(String chatId) {
        if ("".equals(chatId)) throw new IllegalArgumentException("ChatId cant be null");
        SendSticker sendSticker = getSendSticker();
        sendSticker.setChatId(chatId);
        return sendSticker;
    }

    public SendSticker getSendSticker() {
        SendSticker sendSticker = new SendSticker();
        sendSticker.setSticker(stickerId);
        return sendSticker;
    }
}