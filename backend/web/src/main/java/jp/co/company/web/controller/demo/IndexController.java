package jp.co.company.web.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.company.web.controller.demo.helper.index.IndexHelper;
import jp.co.company.web.controller.demo.response.index.View;
import jp.co.company.web.dto.session.SampleSessionData;

@Controller
@RequestMapping("/demo")
public class IndexController {
    private final SampleSessionData sampleSessionData;

    public IndexController(SampleSessionData sessionData) {
        this.sampleSessionData = sessionData;
    }

    @GetMapping({ "", "/" })
    String index(Model model) {
        sampleSessionData.setTest("test session scope");

        View view = new View();
        view.setGreeting(IndexHelper.getGreeting());
        model.addAttribute("view", view);
        return "pages/demo/index";
    }
}