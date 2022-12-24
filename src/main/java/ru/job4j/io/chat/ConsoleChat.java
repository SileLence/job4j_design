package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String logPath;
    private final String botAnswers;
    private final Random random = new Random();
    private final Scanner sc = new Scanner(System.in);
    private final List<String> chatLog = new ArrayList<>();
    private final List<String> phrases;
    private final DateTimeFormatter timeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private boolean isWait = false;
    private boolean isFinish = false;

    public ConsoleChat(String logPath, String botAnswers) {
        this.logPath = logPath;
        this.botAnswers = botAnswers;
        this.phrases = readPhrases();
    }

    public void run() {
        chatLog.add(formatMessage(
                ChatConstants.SYSTEM,
                ChatConstants.GET_USERNAME));
        System.out.println(chatLog.get(0));
        String username = sc.nextLine();
        chatLog.add(formatMessage(username, username));
        chatLog.add(formatMessage(
                ChatConstants.SYSTEM,
                String.format(ChatConstants.START_MSG, username)));
        System.out.println(chatLog.get(2));
        while (!isFinish) {
            String userMsg = sc.nextLine();
            chatLog.add(formatMessage(username, userMsg));
            this.defineMessage(userMsg);
            if (!isWait && !isFinish) {
                this.getAnswer();
            }
        }
    }

    private void defineMessage(String userMsg) {
        if (isWait && ChatConstants.CONTINUE.equalsIgnoreCase(userMsg)) {
            this.continueChat();
        }
        if (userMsg.equalsIgnoreCase(ChatConstants.STOP)) {
            this.stopChat();
        }
        if (userMsg.equalsIgnoreCase(ChatConstants.FINISH)) {
            this.closeChat();
        }
    }

    private void continueChat() {
        String continueMsg = formatMessage(
                ChatConstants.SYSTEM,
                ChatConstants.CONTINUE_MSG);
        chatLog.add(continueMsg);
        System.out.println(continueMsg);
        isWait = false;
    }

    private void stopChat() {
        String stopMsg = formatMessage(
                ChatConstants.SYSTEM,
                ChatConstants.STOP_MSG);
        chatLog.add(stopMsg);
        System.out.println(stopMsg);
        isWait = true;
    }

    private void closeChat() {
        String finishMsg = formatMessage(
                ChatConstants.SYSTEM,
                ChatConstants.FINISH_MSG);
        chatLog.add(finishMsg);
        System.out.println(finishMsg);
        isFinish = true;
        saveLog(chatLog);
    }

    private void getAnswer() {
        String answer = formatMessage(
                ChatConstants.BOT,
                phrases.get(random.nextInt(phrases.size() - 1)));
        chatLog.add(answer);
        System.out.println(answer);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader
                     = new BufferedReader(new FileReader(botAnswers))) {
            phrases = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(logPath, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatMessage(String user, String message) {
        return String.format(
                "%s %s: %s",
                LocalDateTime.now().format(timeFormatter),
                user,
                message
        );
    }

    public static void main(String[] args) throws IOException {
        new FileWriter(args[0], false).close();
        ConsoleChat consoleChat = new ConsoleChat(args[0], args[1]);
        consoleChat.run();
    }
}
