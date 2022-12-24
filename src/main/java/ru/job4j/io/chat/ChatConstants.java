package ru.job4j.io.chat;

record ChatConstants() {
    public static final String FINISH = "закончить";
    public static final String STOP = "стоп";
    public static final String CONTINUE = "продолжить";
    public static final String BOT = "Бот";
    public static final String SYSTEM = "Система";
    public static final String GET_USERNAME = "Чат запущен. Как Вас зовут?";
    public static final String START_MSG = """
            Привет, %s! Для приостановки ответов введите "Стоп",
            для завершения чата - "Закончить".""";
    public static final String STOP_MSG = """
            Чат приостановлен. Для продолжения введите "Продолжить".""";
    public static final String CONTINUE_MSG = "Чат продолжен.";
    public static final String FINISH_MSG = "Чат завершён.";
}
