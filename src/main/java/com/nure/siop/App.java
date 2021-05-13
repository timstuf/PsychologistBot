package com.nure.siop;

import com.nure.siop.service.MessageReciever;
import com.nure.siop.service.MessageSender;
import com.nure.siop.textprocessing.NurePsychologist;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class App {
    private static final Logger log = Logger.getLogger(App.class);
    private static final int PRIORITY_FOR_SENDER = 1;
    private static final int PRIORITY_FOR_RECEIVER = 3;
    private static final String BOT_ADMIN = "305471571";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot test_habr_bot = new Bot("NurePsychologistBot", "1777628087:AAHGYaVvZoNX5DrMEE67D3HWIGKC4VzpVH0");
        NurePsychologist nurePsychologist = new NurePsychologist();
        MessageReciever messageReciever = new MessageReciever(test_habr_bot, nurePsychologist);
        MessageSender messageSender = new MessageSender(test_habr_bot);

        test_habr_bot.botConnect();

        Thread receiver = new Thread(messageReciever);
        receiver.setDaemon(true);
        receiver.setName("MsgReciever");
        receiver.setPriority(PRIORITY_FOR_RECEIVER);
        receiver.start();

        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(PRIORITY_FOR_SENDER);
        sender.start();

        sendStartReport(test_habr_bot);
    }

    private static void sendStartReport(Bot bot) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(BOT_ADMIN);
        sendMessage.setText("Запустился");
        bot.sendQueue.add(sendMessage);
    }
}
