package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.intf.TrainerServiceIntf;
import com.ideas2it.employee.service.intf.TraineeServiceIntf;
import com.ideas2it.employee.exception.BadRequest;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <h2>EmployeeController</h2>
 * <p>
 * It get input from user and sent it to Service class for 
 * add, Update, view, Delete(CRUD Operations) object in the list of trainer and
 * trainee and then display Output.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/

@Controller
public class EmployeeController {
    @Autowired
    private TrainerServiceIntf trainerService;
    @Autowired
    private TraineeServiceIntf traineeService;
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping(value = "/trainerForm")
    public String TrainerForm(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "/addTrainer";
    }

    @GetMapping(value = "/traineeForm")
    public ModelAndView TraineeForm(Model model) {
        model.addAttribute("trainee", new Trainee());
        ModelAndView modelAndView = new ModelAndView("/addTrainee");
        modelAndView.addObject("trainers", trainerService.getTrainers());
        return modelAndView;
    }

    @PostMapping(value = "/error")
    public String errorPage() {
        return "/error";
    }

    @PostMapping(value = "/addTrainee")
    public String addTraineeDetails(@ModelAttribute Trainee trainee, @RequestParam("method") String action, RedirectAttributes redirectAttributes) {
        try {
            traineeService.validateAndAddOrUpdateTraineeDetails(trainee);
            if (action.equals("addTrainee")) {
                redirectAttributes.addFlashAttribute("message", "Trainee "
                        + trainee.getName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("message", "Trainee "
                        + trainee.getName() + " Updated Successfully");
            }
        } catch (BadRequest e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/error";
        }
        return "redirect:/viewTrainee";
    }

    @PostMapping(value = "/addTrainer")
    public String addTrainerDetails(@ModelAttribute Trainer trainer, @RequestParam("method") String action, RedirectAttributes redirectAttributes) {
        try {
            trainerService.validateAndAddOrUpdateTrainerDetails(trainer);
            if (action.equals("addTrainer")) {
                redirectAttributes.addFlashAttribute("message", "Trainer "
                        + trainer.getName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("message", "Trainer "
                        + trainer.getName() + " Updated Successfully");
            }
        } catch (BadRequest e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/viewTrainer";
    }

    @GetMapping(value = "/updateTrainerForm")
    public String updateTrainerForm(Model model, @RequestParam("ID") int id) {
        model.addAttribute("trainer", trainerService.getTrainerById(id));
        return "/addTrainer";
    }

    @GetMapping(value = "/updateTraineeForm")
    public String updateTraineeForm(Model model, @RequestParam("ID") int id) {
        model.addAttribute("trainee", traineeService.getTraineeById(id));
        model.addAttribute("trainers", trainerService.getTrainers());
        return "/addTrainee";
    }

    /**
     * <p>
     * It prints the Trainer details stored as Object in a list by using a loop.
     * get list Trainer List from the service class by a method.
     * If the list is empty, it shows an message.
     * </p>
     **/
    @GetMapping(value = "/viewTrainer")
    private ModelAndView showTrainerDetails() {
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        ModelAndView modelAndView = new ModelAndView();
        logger.info("\nDetails of Trainer\n");
        listOfTrainers.forEach(trainer -> System.out.println(trainer));
        listOfTrainers.forEach(trainer -> logger.info(trainer + "\n"));
        modelAndView.setViewName("viewTrainer");
        modelAndView.addObject("Trainer", listOfTrainers);
        return modelAndView;
    }

    /**
     * <p>
     * It prints the Trainer details stored as Object in a list by using a loop.
     * get list Trainee List from the service class by a method.
     * </p>
     **/
    @GetMapping(value = "/viewTrainee")
    private ModelAndView showTraineeDetails() {
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        ModelAndView modelAndView = new ModelAndView();
        logger.info("\nDetails of Trainee\n");
        listOfTrainees.forEach((trainee) -> logger.info(trainee + "\n"));
        modelAndView.setViewName("viewTrainee");
        modelAndView.addObject("trainee", listOfTrainees);
        return modelAndView;
    }

    /**
     * <p>
     * Get list from service class, remove Trainer object from the list by using
     * id that get from the user and sent the id to a method in service class
     * If the list is empty, it shows an message.
     * If Object deleted or not it shows message.
     * </p>
     *
     * @param id - id of the trainer
     * @param redirectAttributes - used for redirect the request.
     * @return String - redirect to view Trainer mapping
     */
    @GetMapping("/deleteTrainer")
    private String deleteTrainerDetailsById(@RequestParam("ID") int id, RedirectAttributes redirectAttributes) {
        trainerService.removeTrainerById(id);
        redirectAttributes.addFlashAttribute("message", "Trainer " + id + " deleted Successfully");
        return "redirect:/viewTrainer";
    }

    /**
     * <p>
     * Get list from service class, remove Trainee object from the Database by using Id
     * </p>
     **/
    @GetMapping("/deleteTrainee")
    private String deleteTraineeDetailsById(@RequestParam("ID") int id, RedirectAttributes redirectAttributes) {
        traineeService.removeTraineeById(id);
        redirectAttributes.addFlashAttribute("message", "Trainee " + id + " deleted Successfully");
        return "redirect:/viewTrainee";
    }
}