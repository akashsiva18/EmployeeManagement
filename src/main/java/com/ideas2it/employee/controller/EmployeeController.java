package com.ideas2it.employee.controller;

import com.ideas2it.employee.DTO.TraineeDTO;
import com.ideas2it.employee.DTO.TrainerDTO;
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

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final TrainerService trainerService;
    private final TraineeService traineeService;

    @Autowired
    public EmployeeController (TrainerService trainerService, TraineeService traineeService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

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
        model.addAttribute("trainerDTO", new TrainerDTO());
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
        model.addAttribute("traineeDTO", new TraineeDTO());
        ModelAndView modelAndView = new ModelAndView("/addOrUpdateTrainee");
        List<TrainerDTO> trainerDTOs = trainerService.getTrainers();
        modelAndView.addObject("trainerDTOs", trainerDTOs);
        return modelAndView;
    }

    /**
     * It gets the url request with trainee object and add to the database
     * then shows message to the user in the view page of trainee else
     * show error page with the error information.
     *
     * @param trainerDTO - trainer DTO object that set in previous request.
     * @param action - which process is activated that from the request.
     * @param redirectAttributes - redirect the page to view if error arise redirect to error page.
     * @return String- redirect page.
     **/
    @PostMapping(value = "/addTrainer")
    public String addTrainerDetails(@ModelAttribute TrainerDTO trainerDTO, @RequestParam("method") String action,
                                    RedirectAttributes redirectAttributes) {
        try {
            trainerService.validateAndAddOrUpdateTrainerDetails(trainerDTO);
            if (action.equals("addTrainer")) {
                redirectAttributes.addFlashAttribute("message", "Trainer "
                        + trainerDTO.getName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("message", "Trainer "
                        + trainerDTO.getName() + " Updated Successfully");
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
     * @param traineeDTO - trainee DTO object from the model
     * @param action - which process is activated that from the request
     * @param redirectAttributes - redirect the page to view if error arise redirect to error page.
     * @return String - redirect page.
     **/
    @PostMapping(value = "/addTrainee")
    public String addTraineeDetails(@ModelAttribute TraineeDTO traineeDTO, @RequestParam("method") String action,
                                    RedirectAttributes redirectAttributes) {
        try {
            traineeService.validateAndAddOrUpdateTraineeDetails(traineeDTO);
            if (action.equals("addTrainee")) {
                redirectAttributes.addFlashAttribute("message", "Trainee "
                        + traineeDTO.getName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("message", "Trainee "
                        + traineeDTO.getName() + " Updated Successfully");
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
        TrainerDTO trainerDTO = trainerService.getTrainerById(id);
        model.addAttribute("trainerDTO", trainerDTO);
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
    public ModelAndView updateTraineeForm(Model model, @RequestParam("ID") int id) {
        TraineeDTO traineeDTO = traineeService.getTraineeById(id);
        model.addAttribute("traineeDTO", traineeDTO);
        ModelAndView modelAndView = new ModelAndView("/addOrUpdateTrainee");
        List<TrainerDTO> trainerDTOs = trainerService.getTrainers();
        modelAndView.addObject("trainerDTOs", trainerDTOs);
        return modelAndView;
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
        ModelAndView modelAndView = new ModelAndView();
        logger.info("\nDetails of TrainerDTO\n");
        modelAndView.setViewName("viewTrainer");
        List<TrainerDTO> trainerDTOs = trainerService.getTrainers();
        trainerDTOs.forEach(trainer -> logger.info(trainer + "\n"));
        modelAndView.addObject("trainerDTOs", trainerDTOs);
        return modelAndView;
    }

    /**
     * It retrieves all the trainees from the database and sent to the JSP to show.
     *
     * @return ModelAndView - It show the viewTrainer page and add list of trainees to the session.
     */
    @GetMapping(value = "/viewTrainee")
    private ModelAndView showTraineeDetails() {
        List<TraineeDTO> listOfTraineesDTO = traineeService.getTrainees();
        ModelAndView modelAndView = new ModelAndView();
        logger.info("\nDetails of Trainee\n");
        listOfTraineesDTO.forEach((trainee) -> logger.info(trainee + "\n"));
        modelAndView.setViewName("viewTrainee");
        modelAndView.addObject("trainees", listOfTraineesDTO);
        return modelAndView;
    }

    /**
     * It deletes trainer from the database by using I'd and
     * redirect to the view trainer page with message.
     *
     * @param id - id of the trainer
     * @param redirectAttributes - used for redirect the request.
     * @return String - redirect to view TrainerDTO form
     */
    @GetMapping("/deleteTrainer")
    private String deleteTrainerDetailsById(@RequestParam("ID") int id, RedirectAttributes redirectAttributes) {
        trainerService.removeTrainerById(id);
        redirectAttributes.addFlashAttribute("message", "Trainer " + id
                + " deleted Successfully");
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
    private String deleteTraineeDetailsById(@RequestParam("ID") int id,
                                            RedirectAttributes redirectAttributes) {
        traineeService.removeTraineeById(id);
        redirectAttributes.addFlashAttribute("message", "Trainee " + id
                + " deleted Successfully");
        return "redirect:/viewTrainee";
    }
}