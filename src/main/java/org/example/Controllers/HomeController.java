package org.example.Controllers;
// Model - библиотека для передачи параметров в html шаблон

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller - аннотация с формированием html страницы
@Controller
public class HomeController {
    // @GetMapping - аннотация для объявления маршрута для обработки
    @GetMapping("/")
    public String home(Model model){
        String title = "База данных сотрудников";
        // передает параметр title в html страницу
        model.addAttribute("title", title);
        return "home";
    }
}
