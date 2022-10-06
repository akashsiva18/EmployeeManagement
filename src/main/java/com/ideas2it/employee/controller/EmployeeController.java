package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.intf.TrainerServiceIntf;
import com.ideas2it.employee.service.intf.TraineeServiceIntf;
import com.ideas2it.employee.common.EmployeeType;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class EmployeeController extends HttpServlet {
    @Autowired
    private TrainerServiceIntf trainerService;
    @Autowired
    private TraineeServiceIntf traineeService;
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);


    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @PostMapping(value = "/addTrainer")
    public String addTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String pageToShow;
        try {
            addOrUpdateEmployee(EmployeeType.TRAINER.type, req, res);
            pageToShow = "index";
        } catch (BadRequest e) {
            logger.warn(e.getMessage());
            pageToShow = "index";
        } catch (EmployeeNotFound e1) {
            out.println(e1.getMessage() + "<br>");
            pageToShow = "index";
            logger.warn(e1.getMessage());
        }
        return pageToShow;
    }

    /**
     * <p>
     * It sent the data that get from JSP and send it to the Add or Update the details.
     * </p>
     * @param {@link String} employee - 
     *        for which employee is need to add is specified by the parameter.
     **/
    private void addOrUpdateEmployee(String employee, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Trainer oldTrainer = null;
        Trainee oldTrainee = null;
        String trainingPeriod = "";
        List<Integer> validationError = new ArrayList<>();
        List<String> trainerIdAsList = new ArrayList<>();
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String dateOfBirth = req.getParameter("dateOFBirth");
        String gender = req.getParameter("Gender");
        String qualification = req.getParameter("qualification");
        String address = req.getParameter("address");
        String mobileNumber = req.getParameter("mobileNumber");
        String emailId = req.getParameter("EmailID");
        String dateOfJoining = req.getParameter("DateOfJoining");
        String action = req.getParameter("flag");
        String method = req.getParameter("method");
        if (employee.equals(EmployeeType.TRAINEE.type)) {
            trainerIdAsList = new ArrayList<>();
            String trainerIds = req.getParameter("TrainerIDs");
            if (!trainerIds.isEmpty()) {
                String[] tempTrainerIds = trainerIds.split(",",0);
                for (String id : tempTrainerIds) {
                    trainerIdAsList.add(id);
                }
            }
            trainingPeriod = req.getParameter("TrainingPeriod");
        }
        HttpSession session = req.getSession(false);
        if (method.equals("updateTrainer")) {
            oldTrainer = (Trainer)session.getAttribute("trainer");
        } else if (method.equals("updateTrainee")) {
            oldTrainee = (Trainee)session.getAttribute("trainee");
        }
            
        try {
            if (employee.equals(EmployeeType.TRAINER.type)) {
                validationError = trainerService.validateAndAddOrUpdateTrainerDetails(
                                  name, dateOfBirth, gender, qualification,
                                  address, mobileNumber, emailId, dateOfJoining, oldTrainer);
            } else {
                validationError = traineeService.validateAndAddOrUpdateTraineeDetails(name,
                                  dateOfBirth,gender,qualification,address,
                                  mobileNumber, emailId, dateOfJoining,
                                  trainerIdAsList, trainingPeriod, oldTrainee);   
            }
            if (null == oldTrainer && null == oldTrainee) {
                out.println("<h3>Details Of " + employee + " Added Successfully.</h3>");
                logger.info("Details Of " + employee + " Added Successfully.");
            } else {
                out.println("<h2>Details Of " + employee + " Updated Successfully.</h3>");
                logger.info("Details Of " + employee + " Updated Successfully.");
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index");
            requestDispatcher.include(req, res);
        } catch (BadRequest e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());

        } catch (EmployeeNotFound e1) {
            out.println(e1.getMessage() + "<br>");
            out.println("\n\nDetails Of " + employee + " Added Successfully.");

            logger.warn(e1.getMessage());
        }
    }

    /**
     * <p>
     * It prints the Trainer details stored as Object in a list by using a loop.
     * get list Trainer List from the service class by a method. 
     * If the list is empty, it shows an message.
     * </p>
     *
     **/
    @RequestMapping(value = "/viewTrainer", method = RequestMethod.GET)
    private ModelAndView showTrainerDetails() {
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        ModelAndView modelAndView = new ModelAndView();
        if (listOfTrainers.isEmpty()) {
            logger.info("\nNo Trainer Data Found\n");
        } else {
            logger.info("\nDetails of Trainer\n");
            modelAndView.setViewName("viewTrainer");
            modelAndView.addObject("Trainer",listOfTrainers);
        }
        return modelAndView;
    }

    /**
     * <p>
     * It prints the Trainer details stored as Object in a list by using a loop.
     * get list Trainee List from the service class by a method. 
     * If the list is empty, it shows an message.
     * </p>
     *
     **/
    @GetMapping(value = "/viewTrainee")
    private ModelAndView showTraineeDetails() {
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        ModelAndView modelAndView = new ModelAndView();
        if (listOfTrainees.isEmpty()) {
            logger.info("\nNo Trainee Data Found\n");
        } else {
            logger.info("\nDetails of Trainee\n");
            listOfTrainees.forEach( (trainee) -> logger.info(trainee + "\n"));
            modelAndView.setViewName("viewTrainee");
            modelAndView.addObject("trainee",listOfTrainees);
        }
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
     **/
    @GetMapping("/deleteTrainer")
    @RequestMapping(value = "/deleteTrainer", method = RequestMethod.GET)
    private void deleteTrainerDetailsById(@RequestParam int ID) {
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        trainerService.removeTrainerById(ID);

    }

    /**
     * <p>
     * Get list from service class, remove Trainee object from the list by using
     * id that get from the user and sent the id to a method in service class
     * If the list is empty, it shows an message.
     * If Object deleted or not it shows message.
     * </p>
     *
     **/
    @GetMapping("/deleteTrainee")
    private ModelAndView deleteTraineeDetailsById(@RequestParam int ID) {
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        traineeService.removeTraineeById(ID);
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("viewTrainee");
        modelView.addObject("message","Trainer " + ID + " deleted Successfully");
        return modelView;
   }

    /**
     * <p>
     * Method that checks the List of trainers if it empties it shows a 
     * message and the method will end. if the list contains objects it 
     * asks a user to enter which id to update then search the id in 
     * the objects, once id found it will ask for the updated details 
     * by using another method. if not found it shows a message.
     * </p>
     *
     **/
    private void getTrainerDetailsById(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        int id = Integer.parseInt(req.getParameter("ID"));
        Trainer oldTrainer = trainerService.getTrainerById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addTrainer.jsp?flag=updateTrainer"); 
        req.setAttribute("oldTrainer", oldTrainer);
        requestDispatcher.include(req,res);
        logger.info(oldTrainer);
    }

    /**
     * <p>
     * A method that checks the List of trainees if it empties it
     * shows a message and the method will end. if the list contains objects
     * it asks a user to enter which id to update and then search the id in 
     * the objects, once id found it will ask for the updated details by 
     * using another method. If not found it shows a message.
     * </p>
     *
     **/
    private void getTraineeDetailsById(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        int id = Integer.parseInt(req.getParameter("ID"));
        Trainee oldTrainee = traineeService.getTraineeById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addTrainee.jsp?flag=updateTrainee"); 
        req.setAttribute("oldTrainee", oldTrainee);
        requestDispatcher.include(req,res);
        logger.info(oldTrainee);
    }
}