package com.example.msvcdojo;

import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Josep Maria on 22/07/2016.
 */
public class IndexController {

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndexViewSubscription(ModelMap model) {
        return new ModelAndView("operation/testData");
    }
}
