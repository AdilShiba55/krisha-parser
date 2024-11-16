package kz.loader.service.impl;

import kz.loader.model.ParsingLog;
import kz.loader.repository.ParsingLogRepository;
import kz.loader.service.ParsingLogService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParsingLogLogServiceImpl implements ParsingLogService {

    private final ParsingLogRepository parsingLogRepository;

    public ParsingLogLogServiceImpl(ParsingLogRepository parsingLogRepository) {
        this.parsingLogRepository = parsingLogRepository;
    }

    public void save(String url, String message, String type, Boolean success) {
        ParsingLog log = new ParsingLog(url, message, type, success, new Date());
        parsingLogRepository.save(log);
    }
}
