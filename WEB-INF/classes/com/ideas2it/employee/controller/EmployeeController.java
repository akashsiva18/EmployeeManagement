package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.intf.TrainerServiceIntf;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.intf.TraineeServiceIntf;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.common.Constant;
import com.ideas2it.employee.util.StringUtil;
import com.ideas2it.employee.common.EmployeeType;
import com.ideas2it.employee.common.Gender;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
public class EmployeeController extends HttpServlet{
    private static final Scanner userInput = new Scanner(System.in);
    private TrainerServiceIntf trainerService = new TrainerServiceImpl();
    private TraineeServiceIntf traineeService = new TraineeServiceImpl();
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String choice = req.getParameter("flag");

        switch (choice) {
           case "addTrainer" :
               addOrUpdateEmployee(EmployeeType.TRAINER.type, req, res);
               break;

            case "addTrainee":
               addOrUpdateEmployee(EmployeeType.TRAINEE.type, req, res);
               break;
 
            case "deleteTrainer":
               deleteTrainerDetailsById(req, res);
               break;

            case "deleteTrainee":
               deleteTraineeDetailsById(req, res);
               break;

            case "updateTrainer":
               getTrainerDetailsById(req, res);
               break;

            case "updateTrainee":
               getTraineeDetailsById(req, res);
               break;   

            case "viewTrainers":
               showTrainerDetails(req, res);
               break;

            case "viewTrainees":
               showTraineeDetails(req, res);
               break;
        }
    }

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }
  
    /**
     * <p>
     * This method is get input from user and sent the inputs to service for validation 
     * then get list of errors from the add details method in service class and 
     * check the list for the validation error. If errors in the list then get the particular 
     * invalid details from the user and sent back the value for validation in loop
     * once get all valid details, the loop will end.
     * </p>
     * @param {@link String} employee - 
     *        for which employee is need to add is specified by the parameter.      
     * @return {@link void} return nothing
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
                String[] tempTrainerIds = trainerIds.split("[,]",0);
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
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");  
            requestDispatcher.include(req, res);
        } catch (BadRequest e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());
            RequestDispatcher rd=req.getRequestDispatcher("index.html");  
            rd.include(req, res);
        } catch (EmployeeNotFound e1) {
            out.println(e1.getMessage() + "<br>");
            out.println("\n\nDetails Of " + employee + " Added Successfully.");
            RequestDispatcher rd=req.getRequestDispatcher("index.html");  
            rd.include(req, res);
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
     * @return {@link void} return nothing 
     **/
    private void showTrainerDetails(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (listOfTrainers.isEmpty()) {
            out.println("<h2>No Trainer Date Found</h2>");
            logger.info("\nNo Trainer Data Found\n");
        } else {
            logger.info("\nDetails of Trainer\n");
            req.setAttribute("Trainers", listOfTrainers);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewTrainer.jsp");
            requestDispatcher.include(req, res);
            listOfTrainers.forEach( (trainer) -> logger.info(trainer + "\n"));
        }
    }

    /**
     * <p>
     * It prints the Trainer details stored as Object in a list by using a loop.
     * get list Trainee List from the service class by a method. 
     * If the list is empty, it shows an message.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void showTraineeDetails(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        if (listOfTrainees.isEmpty()) {
            logger.info("\nNo Trainee Data Found\n");
        } else {
            logger.info("\nDetails of Trainee\n");
            req.setAttribute("Trainees", listOfTrainees);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewTrainee.jsp");
            requestDispatcher.include(req, res);
            listOfTrainees.forEach( (trainee) -> logger.info(trainee + "\n"));
        }
    }

    /**
     * <p>
     * Get list from service class, remove Trainer object from the list by using
     * id that get from the user and sent the id to a method in service class
     * If the list is empty, it shows an message.
     * If Object deleted or not it shows message.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void deleteTrainerDetailsById(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        int id = Integer.parseInt(req.getParameter("ID"));
        trainerService.removeTrainerById(id);
        out.print("<h3>Trainer " + id + " Successfully deleted.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");  
        requestDispatcher.include(req, res);
        logger.info("Trainer " + id + " Successfully deleted.");
    }

    /**
     * <p>
     * Get list from service class, remove Trainee object from the list by using
     * id that get from the user and sent the id to a method in service class
     * If the list is empty, it shows an message.
     * If Object deleted or not it shows message.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void deleteTraineeDetailsById(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        int id = Integer.parseInt(req.getParameter("ID"));
        traineeService.removeTraineeById(id);
        out.print("<h3>Trainee " + id + " Successfully deleted.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");  
        requestDispatcher.include(req, res);
        logger.info("Trainee " + id + " Successfully deleted.");
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
     * @return {@link void} return nothing 
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
     * @return {@link void} return nothing 
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