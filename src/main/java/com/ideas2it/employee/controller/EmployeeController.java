package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.service.TraineeService;
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
    private TrainerService trainerService;
    @Autowired
    private TraineeService traineeService;
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    /**
     * It gets the url request and sent to index page
     *
     * @return String - index page
     */
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * It gets the url request and sent to addOrUpdateTrainer page with new object of trainer.
     *
     * @return String - addOrUpdateTrainer
     */
    @GetMapping(value = "/trainerForm")
    public String TrainerForm(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "/addOrUpdateTrainer";
    }

    /**
     * It gets the url request and sent to addOrUpdateTrainee page with new object of trainee.
     *
     * @param model - it hold the object of the trainee
     * @return ModelAndView - that contains trainers list.
     */
    @GetMapping(value = "/traineeForm")
    public ModelAndView TraineeForm(Model model) {
        model.addAttribute("trainee", new Trainee());
        ModelAndView modelAndView = new ModelAndView("/addOrUpdateTrainee");
        modelAndView.addObject("trainers", trainerService.getTrainers());
        return modelAndView;
    }

    /**
     * It gets the url request with trainee object and add to the database
     * then shows message to the user in the view page of trainee else
     * show error page with the error information.
     *
     * @param trainer - trainer object that set in previous request.
     * @param action - which process is activated that from the request.
     * @param redirectAttributes - redirect the page to view if error arise redirect to error page.
     * @return String- redirect page.
     **/
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
            return "redirect:/error";
        }
        return "redirect:/viewTrainer";
    }

    /**
     * It gets the url request with trainee object and add to the database
     * then shows message to the user in the view page of trainee else
     * show error page with the error information.
     *
     * @param trainee - trainee object from the model
     * @param action - which process is activated that from the request
     * @param redirectAttributes - redirect the page to view if error arise redirect to error page.
     * @return String - redirect page.
     **/
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


    /**
     * It gets the url request and sent to addOrUpdateTrainer page with
     * existing object of trainer that retrieve from the database.
     *
     * @param model - used to set the old trainer in the form.
     * @param id - id for get trainer details.
     * @return String - addOrUpdateTrainee Form.
     */
    @GetMapping(value = "/updateTrainerForm")
    public String updateTrainerForm(Model model, @RequestParam("ID") int id) {
        model.addAttribute("trainer", trainerService.getTrainerById(id));
        return "/addOrUpdateTrainer";
    }


    /**
     * It gets the url request and sent to addOrUpdateTrainee page with
     * existing object of trainer that retrieve from the database.
     *
     * @param model - used to set the old trainer in the form.
     * @param id - id for get trainer details.
     * @return String - addOrUpdateTrainee Form.
     */
    @GetMapping(value = "/updateTraineeForm")
    public String updateTraineeForm(Model model, @RequestParam("ID") int id) {
        model.addAttribute("trainee", traineeService.getTraineeById(id));
        model.addAttribute("trainers", trainerService.getTrainers());
        return "/addOrUpdateTrainee";
    }

    /**
     * It gets the url request and sent to the error page.
     *
     * @return String - error page
     */
    @PostMapping(value = "/error")
    public String errorPage() {
        return "/error";
    }

    /**
     * It retrieves all the trainers from the database and sent to the JSP to show.
     *
     * @return ModelAndView - It show the viewTrainer page and add list of trainers to the session.
     */
    @GetMapping(value = "/viewTrainer")
    private ModelAndView showTrainerDetails() {
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        ModelAndView modelAndView = new ModelAndView();
        logger.info("\nDetails of Trainer\n");
        listOfTrainers.forEach(trainer -> logger.info(trainer + "\n"));
        modelAndView.setViewName("viewTrainer");
        modelAndView.addObject("Trainer", listOfTrainers);
        return modelAndView;
    }

    /**
     * It retrieves all the trainees from the database and sent to the JSP to show.
     *
     * @return ModelAndView - It show the viewTrainer page and add list of trainees to the session.
     */
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
     * It deletes trainer from the database by using I'd and
     * redirect to the view trainer page with message.
     *
     * @param id - id of the trainer
     * @param redirectAttributes - used for redirect the request.
     * @return String - redirect to view Trainer form
     */
    @GetMapping("/deleteTrainer")
    private String deleteTrainerDetailsById(@RequestParam("ID") int id, RedirectAttributes redirectAttributes) {
        trainerService.removeTrainerById(id);
        redirectAttributes.addFlashAttribute("message", "Trainer " + id + " deleted Successfully");
        return "redirect:/viewTrainer";
    }

    /**
     * It deletes trainee from the database using I'd and
     * redirect to the view trainer page with message.
     *
     * @param id - id of the trainee
     * @param redirectAttributes - used for redirect the request.
     * @return String - redirect to view trainee form
     */
    @GetMapping("/deleteTrainee")
    private String deleteTraineeDetailsById(@RequestParam("ID") int id, RedirectAttributes redirectAttributes) {
        traineeService.removeTraineeById(id);
        redirectAttributes.addFlashAttribute("message", "Trainee " + id + " deleted Successfully");
        return "redirect:/viewTrainee";
    }
}