package com.ideas2it.employee.helper;

import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Helper {
    TrainerDao trainerDao;

    @Autowired
    public Helper (TrainerDao trainerDao){
        this.trainerDao = trainerDao;
    }

    /**
     * It returns the trainers object as list.
     *
     * @param trainerIds - list of Trainers Ids.
     * @return List<Trainers> - it contains the trainers.
     **/
    public List<Trainer> getMultipleTrainerByIds(List<Integer> trainerIds) {
        return trainerDao.findAllById(trainerIds);
    }


}
