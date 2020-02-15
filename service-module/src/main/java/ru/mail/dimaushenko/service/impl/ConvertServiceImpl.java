package ru.mail.dimaushenko.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.FileRepository;
import ru.mail.dimaushenko.service.ConvertService;
import ru.mail.dimaushenko.service.NumbersService;
import static ru.mail.dimaushenko.service.constants.LogConstants.END_GET_SUM;
import static ru.mail.dimaushenko.service.constants.LogConstants.START_GET_SUM;
import static ru.mail.dimaushenko.service.constants.LogConstants.START_SUM;
import static ru.mail.dimaushenko.service.constants.LogConstants.TRYING_TO_GET_STRINGS_FROM_FILE;

@Service
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private NumbersService numbersService;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Integer getSum(String filename) {
        logger.info(START_GET_SUM);
        logger.info(TRYING_TO_GET_STRINGS_FROM_FILE);
        List<String> strings = fileRepository.getStrings(filename);
        logger.info(START_SUM);
        Integer sum = 0;
        for (String string : strings) {
            sum += numbersService.add(string);
        }
        logger.info(END_GET_SUM);
        return sum;
    }

}
