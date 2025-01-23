package com.example.selfcare.service;

import com.example.selfcare.entity.Reschedule;
import com.example.selfcare.repository.RescheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RescheduleService {
    @Autowired
    private RescheduleRepository rescheduleRepository;

    public String insertReschedule(Long pid, Long docid, String date) {
        Reschedule reschedule = new Reschedule();
        reschedule.setPid(pid);
        reschedule.setDocid(docid);
        reschedule.setScheduledate(date);

        rescheduleRepository.save(reschedule);

        return "Success";
    }
}
