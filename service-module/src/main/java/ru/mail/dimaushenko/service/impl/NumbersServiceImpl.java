package ru.mail.dimaushenko.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.service.NumbersService;
import static ru.mail.dimaushenko.service.constants.LogConstants.SEPARATED_BY;
import static ru.mail.dimaushenko.service.constants.LogConstants.START_PARSE_STRING;
import static ru.mail.dimaushenko.service.constants.LogConstants.STRING_HAVE_MORE_THAN_2_NUMBERS;
import static ru.mail.dimaushenko.service.constants.LogConstants.STRING_HAVE_WRONG_SYMBOL;
import static ru.mail.dimaushenko.service.constants.LogConstants.STRING_SEPARATED_BY;

@Service
public class NumbersServiceImpl implements NumbersService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public int add(String numbers) {
        int sum = 0;
        if (isStringNotEmpty(numbers)) {
            logger.info(START_PARSE_STRING + numbers.replace("\n", "\\n"));
            List<String> numpersStr = parseString(numbers);
            for (String string : numpersStr) {
                sum += Integer.parseInt(string);
            }
        }
        logger.info("Summ = " + sum);
        return sum;
    }

    private List<String> parseString(String string) {
        List<String> subStrings = new ArrayList<>();
        String subString = "";
        for (int i = 0; i < string.length(); i++) {
            switch (string.charAt(i)) {
                case ',':
                case ':':
                case '\n':
                case '|':
                    if (isStringNotEmpty(subString)) {
                        if (subStrings.size() < 2) {
                            subStrings.add(subString);
                        } else {
                            logger.error(STRING_HAVE_MORE_THAN_2_NUMBERS);
                            throw new RuntimeException();
                        }
                        subString = "";
                        if (string.charAt(i) != '\n') {
                            logger.info(STRING_SEPARATED_BY + string.charAt(i));
                        } else {
                            logger.info(SEPARATED_BY + "\\n");
                        }
                    }
                    break;
                default:
                    if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
                        subString += string.charAt(i);
                    } else {
                        logger.error(STRING_HAVE_WRONG_SYMBOL + string.charAt(i));
                        throw new RuntimeException(STRING_HAVE_WRONG_SYMBOL + string.charAt(i));
                    }
                    break;
            }
        }
        if (isStringNotEmpty(subString)) {
            if (subStrings.size() < 2) {
                subStrings.add(subString);
            } else {
                logger.error(STRING_HAVE_MORE_THAN_2_NUMBERS);
                throw new RuntimeException(STRING_HAVE_MORE_THAN_2_NUMBERS);
            }
        }
        return subStrings;
    }

    private static boolean isStringNotEmpty(String subString) {
        return subString.length() > 0;
    }

}
