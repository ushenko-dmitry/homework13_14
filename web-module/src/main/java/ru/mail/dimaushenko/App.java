package ru.mail.dimaushenko;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mail.dimaushenko.config.AppConfig;
import ru.mail.dimaushenko.controller.ConvertController;

public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        ConvertController convertController = ctx.getBean(ConvertController.class);
        convertController.sum();
    }
}
