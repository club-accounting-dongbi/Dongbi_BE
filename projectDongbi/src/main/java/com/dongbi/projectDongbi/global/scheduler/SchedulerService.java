package com.dongbi.projectDongbi.global.scheduler;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final GenerationRepository generationRepository;

    @Scheduled(cron = "0 5 0 0 * *")
    @Transactional(readOnly = true)
    public void findByGeneration(){
        LocalDate localDate = LocalDate.now();
        List<Generation> list =  generationRepository.findByEndDate(localDate);
        for (Generation generation : list) {
            generation.generationActFalse();
        }
    }
}
