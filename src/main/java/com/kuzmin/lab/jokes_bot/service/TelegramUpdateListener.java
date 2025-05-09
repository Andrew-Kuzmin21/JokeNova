package com.kuzmin.lab.jokes_bot.service;

import com.kuzmin.lab.jokes_bot.exceptions.JokeNotFoundException;
import com.kuzmin.lab.jokes_bot.model.Joke;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TelegramUpdateListener implements UpdatesListener {

    //@Qualifier("jokesServiceImpl")
    private final JokesService jokesService;

    private final TelegramBot telegramBot;

    public TelegramUpdateListener(
            @Qualifier("jokesServiceImpl") JokesService jokesService,
            TelegramBot telegramBot
    ) {
        this.jokesService = jokesService;
        this.telegramBot = telegramBot;
    }

    public int process(List<Update> updates) {
        for (Update update : updates) {
            if (update.message() != null && update.message().text() != null) {
                String text = update.message().text();
                long chatId = update.message().chat().id();

                try {
                    // Команда /start
                    if (text.equalsIgnoreCase("/start")) {
                        telegramBot.execute(new SendMessage(chatId, "Привет! Используй команды:\n" +
                                "/getjoke <id> — Получить шутку по id\n" +
                                "/getalljokes — Получить все шутки\n" +
                                "/addjoke <text> — Добавить шутку\n" +
                                "/editjoke <id> <new_text> — Редактировать шутку по id\n" +
                                "/deletejoke <id> — Удалить шутку по id"));
                    }

                    // Команда /getalljokes
                    else if (text.equalsIgnoreCase("/getalljokes")) {
//                        LocalDate createdAt = LocalDate.now(); // или нужная дата
//                        int page = 0;
//                        int size = 100;
//                        String jokes = jokesService.getAllJokes(createdAt, page, size).toString();
//                        String jokes = jokesService.getAllJokes().toString();
//                        telegramBot.execute(new SendMessage(chatId, jokes.isEmpty() ? "Нет шуток" : jokes));
                    }

                    // Команда /getjoke <id>
                    else if (text.startsWith("/getjoke")) {
                        String[] commandParts = text.split(" ");
                        if (commandParts.length == 2) {
                            try {
                                Long id = Long.parseLong(commandParts[1]);
                                Joke joke = jokesService.getJokeById(id);
                                telegramBot.execute(new SendMessage(chatId, joke != null ? joke.getText() + " (Создано: " + joke.getCreatedAt() + ")" : "Шутка не найдена"));
                            } catch (NumberFormatException e) {
                                telegramBot.execute(new SendMessage(chatId, "Неверный формат id"));
                            }
                        } else {
                            telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /getjoke <id>"));
                        }
                    }

                    // Команда /addjoke <text>
                    else if (text.startsWith("/addjoke")) {
                        String[] commandParts = text.split(" ", 2);
                        if (commandParts.length == 2) {
                            Joke newJoke = new Joke();
                            newJoke.setText(commandParts[1]);
                            newJoke.setCreatedAt(LocalDate.now());
                            newJoke.setModifiedAt(LocalDate.now());
                            jokesService.addJokes(newJoke);
                            telegramBot.execute(new SendMessage(chatId, "✅ Шутка добавлена!"));
                        } else {
                            telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /addjoke <text>"));
                        }
                    }

                    // Команда /editjoke <id> <new_text>
                    else if (text.startsWith("/editjoke")) {
                        String[] commandParts = text.split(" ", 3);
                        if (commandParts.length == 3) {
                            try {
                                Long id = Long.parseLong(commandParts[1]);
                                Joke jokeToUpdate = jokesService.getJokeById(id);
                                if (jokeToUpdate != null) {
                                    jokeToUpdate.setText(commandParts[2]);
                                    jokeToUpdate.setModifiedAt(LocalDate.now());
                                    jokesService.editJoke(id, jokeToUpdate);
                                    telegramBot.execute(new SendMessage(chatId, "📝 Шутка обновлена!"));
                                } else {
                                    telegramBot.execute(new SendMessage(chatId, "Шутка с таким id не найдена"));
                                }
                            } catch (NumberFormatException e) {
                                telegramBot.execute(new SendMessage(chatId, "Неверный формат id"));
                            }
                        } else {
                            telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /editjoke <id> <new_text>"));
                        }
                    }

                    // Команда /deletejoke <id>
                    else if (text.startsWith("/deletejoke")) {
                        String[] commandParts = text.split(" ");
                        if (commandParts.length == 2) {
                            try {
                                Long id = Long.parseLong(commandParts[1]);
                                jokesService.deleteJoke(id);
                                telegramBot.execute(new SendMessage(chatId, "☠️ Шутка удалена"));
                            } catch (NumberFormatException e) {
                                telegramBot.execute(new SendMessage(chatId, "Неверный формат id"));
                            }
                        } else {
                            telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /deletejoke <id>"));
                        }
                    }


//                    if (text.startsWith("/getjoke")) {
//                        Long id = Long.parseLong(text.split(" ")[1]);
//                        var joke = jokesService.getJokeById(id);
//                        telegramBot.execute(new SendMessage(chatId, joke.getText()));
//                    } else if (text.equals("/getalljokes")) {
//                        var jokes = jokesService.getAllJokes();
//                        String response = jokes.isEmpty() ? "No jokes found." : jokes.toString();
//                        telegramBot.execute(new SendMessage(chatId, response));
//                    } else if (text.startsWith("/addjoke")) {
//                        String jokeText = text.replace("/addjoke ", "");
//                        Joke joke = new Joke();
//                        joke.setText(jokeText);
//                        joke.setCreatedAt(LocalDate.now());
//                        joke.setModifiedAt(LocalDate.now());
//                        jokesService.addJokes(joke);
//                        telegramBot.execute(new SendMessage(chatId, "Joke added."));
//                    } else if (text.startsWith("/editjoke")) {
//                        String[] parts = text.split(" ", 3);
//                        Long id = Long.parseLong(parts[1]);
//                        String newText = parts[2];
//                        Joke updated = new Joke();
//                        updated.setText(newText);
//                        updated.setModifiedAt(LocalDate.now());
//                        jokesService.editJoke(id, updated);
//                        telegramBot.execute(new SendMessage(chatId, "Joke updated."));
//                    } else if (text.startsWith("/deletejoke")) {
//                        Long id = Long.parseLong(text.split(" ")[1]);
//                        jokesService.deleteJoke(id);
//                        telegramBot.execute(new SendMessage(chatId, "Joke deleted."));
//                    }
                } catch (JokeNotFoundException e) {
                    telegramBot.execute(new SendMessage(chatId, "❌ Шутка с id " + e.getId() + " не найдена."));
                } catch (Exception e) {
                    telegramBot.execute(new SendMessage(chatId, "⚠️ Ошибка: " + e.getMessage()));
                }

//                // Команда /start
//                if (text.equalsIgnoreCase("/start")) {
//                    telegramBot.execute(new SendMessage(chatId, "Привет! Используй команды:\n" +
//                            "/getjoke <id> — Получить шутку по id\n" +
//                            "/getalljokes — Получить все шутки\n" +
//                            "/addjoke <text> — Добавить шутку\n" +
//                            "/editjoke <id> <new_text> — Редактировать шутку по id\n" +
//                            "/deletejoke <id> — Удалить шутку по id"));
//                }
//
//                // Команда /getalljokes
//                else if (text.equalsIgnoreCase("/getalljokes")) {
//                    String jokes = jokesService.getAllJokes().toString();
//                    telegramBot.execute(new SendMessage(chatId, jokes.isEmpty() ? "Нет шуток" : jokes));
//                }
//
//                // Команда /getjoke <id>
//                else if (text.startsWith("/getjoke")) {
//                    String[] commandParts = text.split(" ");
//                    if (commandParts.length == 2) {
//                        try {
//                            Long id = Long.parseLong(commandParts[1]);
//                            Joke joke = jokesService.getJokeById(id);
//                            telegramBot.execute(new SendMessage(chatId, joke != null ? joke.getText() : "Шутка не найдена"));
//                        } catch (NumberFormatException e) {
//                            telegramBot.execute(new SendMessage(chatId, "Неверный формат id"));
//                        }
//                    } else {
//                        telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /getjoke <id>"));
//                    }
//                }
//
//                // Команда /addjoke <text>
//                else if (text.startsWith("/addjoke")) {
//                    String[] commandParts = text.split(" ", 2);
//                    if (commandParts.length == 2) {
//                        Joke newJoke = new Joke();
//                        newJoke.setText(commandParts[1]);
//                        newJoke.setCreatedAt(LocalDate.now());
//                        newJoke.setModifiedAt(LocalDate.now());
//                        jokesService.addJokes(newJoke);
//                        telegramBot.execute(new SendMessage(chatId, "Шутка добавлена!"));
//                    } else {
//                        telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /addjoke <text>"));
//                    }
//                }
//
//                // Команда /editjoke <id> <new_text>
//                else if (text.startsWith("/editjoke")) {
//                    String[] commandParts = text.split(" ", 3);
//                    if (commandParts.length == 3) {
//                        try {
//                            Long id = Long.parseLong(commandParts[1]);
//                            Joke jokeToUpdate = jokesService.getJokeById(id);
//                            if (jokeToUpdate != null) {
//                                jokeToUpdate.setText(commandParts[2]);
//                                jokeToUpdate.setModifiedAt(LocalDate.now());
//                                jokesService.editJoke(id, jokeToUpdate);
//                                telegramBot.execute(new SendMessage(chatId, "Шутка обновлена!"));
//                            } else {
//                                telegramBot.execute(new SendMessage(chatId, "Шутка с таким id не найдена"));
//                            }
//                        } catch (NumberFormatException e) {
//                            telegramBot.execute(new SendMessage(chatId, "Неверный формат id"));
//                        }
//                    } else {
//                        telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /editjoke <id> <new_text>"));
//                    }
//                }
//
//                // Команда /deletejoke <id>
//                else if (text.startsWith("/deletejoke")) {
//                    String[] commandParts = text.split(" ");
//                    if (commandParts.length == 2) {
//                        try {
//                            Long id = Long.parseLong(commandParts[1]);
//                            jokesService.deleteJoke(id);
//                            telegramBot.execute(new SendMessage(chatId, "Шутка удалена"));
//                        } catch (NumberFormatException e) {
//                            telegramBot.execute(new SendMessage(chatId, "Неверный формат id"));
//                        }
//                    } else {
//                        telegramBot.execute(new SendMessage(chatId, "Используйте команду в формате: /deletejoke <id>"));
//                    }
//                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

//    @Override
//    public int process(List<Update>list) {
//        for (Update update : list) {
//            if (update.message().text().equals("/joke")) {
//                telegramBot.execute(new SendMessage(update.message().chat().id(), jokesService.getAllJokes().toString()));
//            }
//        }
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println("Bot is setting up updates listener...");
        telegramBot.setUpdatesListener(this);
    }
}
