package kz.loader.service;

public interface ParsingLogService {
    void save(String url, String message, String type, Boolean success);
}
