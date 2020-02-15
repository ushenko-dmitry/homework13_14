package ru.mail.dimaushenko.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.FileRepository;
import static ru.mail.dimaushenko.repository.constants.LogConstants.LOG_END_GET_STRINGS;
import static ru.mail.dimaushenko.repository.constants.LogConstants.LOG_ERROR_FILE_NOT_FOUND;
import static ru.mail.dimaushenko.repository.constants.LogConstants.LOG_IN_PROCESS_GET_STRINGS_FILE_WAS_FOUND;
import static ru.mail.dimaushenko.repository.constants.LogConstants.LOG_I_O_EXCEPTION;
import static ru.mail.dimaushenko.repository.constants.LogConstants.LOG_START_GET_STRINGS;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public List<String> getStrings(String filename) {
        logger.info(LOG_START_GET_STRINGS);
        List<String> strings = new ArrayList<>();

        File file = new File(filename);
        try (FileInputStream fis = new FileInputStream(file)) {
            logger.info(LOG_IN_PROCESS_GET_STRINGS_FILE_WAS_FOUND);
            String string = "";
            while (fis.available() > 0) {
                char symbol = (char) fis.read();
                string += symbol;
                if (symbol == 13) {
                    fis.read();
                    strings.add(string.substring(0, string.length() - 1));
                    string = "";
                }
            }
            strings.add(string);
        } catch (FileNotFoundException ex) {
            logger.error(LOG_ERROR_FILE_NOT_FOUND);
            throw new RuntimeException(LOG_ERROR_FILE_NOT_FOUND);
        } catch (IOException ex) {
            logger.error(LOG_I_O_EXCEPTION);
            throw new RuntimeException(LOG_I_O_EXCEPTION);
        }
        logger.info(LOG_END_GET_STRINGS);
        return strings;
    }

}
