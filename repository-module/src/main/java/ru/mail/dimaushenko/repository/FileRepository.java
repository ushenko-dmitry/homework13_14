package ru.mail.dimaushenko.repository;

import java.util.List;


public interface FileRepository {

    List<String> getStrings(String filename);
    
}
