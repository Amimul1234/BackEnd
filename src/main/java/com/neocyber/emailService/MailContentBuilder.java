package com.neocyber.emailService;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String name) {

        Context context = new Context();

        context.setVariable("welcomeName", "Welcome " + name);
        context.setVariable("helloName", "Hello " + name);

        return templateEngine.process("WelcomeEmail", context);
    }
}
