package ru.mail.dimaushenko.controller.impl;

import java.lang.invoke.MethodHandles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import ru.mail.dimaushenko.controller.ConvertController;
import static ru.mail.dimaushenko.controller.constants.LogConstants.SUM;
import static ru.mail.dimaushenko.controller.constants.PropertyName.FILENAME;
import ru.mail.dimaushenko.service.ConvertService;

@Controller
public class ConvertControllerImpl implements ConvertController {

    @Autowired
    private Environment environment;
    @Autowired
    private ConvertService convertService;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    
    @Override
    public void sum() {
        String filename = environment.getProperty(FILENAME);
        Integer sum = convertService.getSum(filename);
        logger.info(SUM + sum);
    }
    
    
}
