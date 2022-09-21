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

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

       String choice = req.getParameter("flag");

       switch (choice) {
           case "addTrainer" :
               addEmployees(EmployeeType.TRAINER.type, req, res);
               break;

            case "addTrainee":
               addEmployees(EmployeeType.TRAINEE.type, req, res);
               break;

            case "viewTrainers":
               showTrainerDetails(req, res);
               break;
            
            case "viewTrainees":
               showTraineeDetails(req, res);
               break;

            case "deleteTrainer":
               deleteTrainerDetailsById(req, res);
               break;

            case "deleteTrainee":
               deleteTraineeDetailsById(req, res);
               break;

       }
               


/*
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String gender = req.getParameter("gender");
        String qualification = req.getParameter("qualification");
        String address = req.getParameter("Address");
        String mobileNumber = req.getParameter("mobileNumber");
        String emailId = req.getParameter("EmailId");
        String dateOfJoining = req.getParameter("DateOfJoining");
        List<Integer> validationError = trainerService.validateAndAddTrainerDetails(
                            name, dateOfBirth, gender, qualification,
                            address, mobileNumber, emailId, dateOfJoining);
*/
    }

    public static void main(String[] args) {
        EmployeeController employeeControl = new EmployeeController();
//        employeeControl.showUserMenu();
    }

    /**
     * <p>
     * It is used show menu to user and have option to select methods 
     * in switch to add, delete, update and remove of trainer and trainee objects.
     * </p>
     *
     * @return {@link void} return nothing
     **/
/*    private void showUserMenu() {
        do {
            int userChoice;
            logger.info("\nSelect Option\n\n" +
                    "1. Add Trainer Details\n" +
                    "2. Add Trainee Details\n" +
                    "3. View Trainer Details\n" +
                    "4. View Trainee Details\n" +
                    "5. Remove Trainer Details\n" +
                    "6. Remove Trainee Details\n" +
                    "7. Update Trainer Details\n" +
                    "8. Update Trainee Details\n" +
                    "9. Exit\n");
            String tempUserInput= userInput.next();
            userChoice = (StringUtil.isvalidNumberInput(tempUserInput))
                          ? Integer.parseInt(tempUserInput)
                          : 0;
            switch (userChoice) {
                case 1:
                    addEmployees(EmployeeType.TRAINER.type);
                    break;

                case 2:
                    addEmployees(EmployeeType.TRAINEE.type);
                    break;

                case 3:
                    showTrainerDetails();
                    break;

                case 4:
                    showTraineeDetails();
                    break;

                case 5:
                    deleteTrainerDetailsById();
                    break;

                case 6:
                    deleteTraineeDetailsById();
                    break;

                case 7:
                    updateTrainerDetailsById();
                    break;

                case 8:
                    updateTraineeDetailsById();
                    break;

                case 9:
                    logger.info("Exited");
                    System. exit(0);
                    break;

                default:
                    logger.warn("Invalid Option\n");
                    break;
            }
        } while (true);
    } */

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
    private void addEmployees(String employee, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

        if (employee.equals(EmployeeType.TRAINEE.type)) {
            trainerIdAsList = new ArrayList<>();
            String trainerIds = req.getParameter("TrainerIDs");
            String[] tempTrainerIds = trainerIds.split("[,]",0);
            for (String id : tempTrainerIds) {
                trainerIdAsList.add(id);
            }
            trainingPeriod = req.getParameter("TrainingPeriod");
        }
        try {
            if (employee.equals(EmployeeType.TRAINER.type)) {
                validationError = trainerService.validateAndAddTrainerDetails(
                        name, dateOfBirth, gender, qualification,
                        address, mobileNumber, emailId, dateOfJoining);
                RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
                redirect.include(req, res);
            } else {
                validationError = traineeService.validateAndAddTraineeDetails(name,
                        dateOfBirth,gender,qualification,address,
                        mobileNumber, emailId, dateOfJoining,
                        trainerIdAsList, trainingPeriod);
                RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
                redirect.include(req, res);
            }
            out.println("Details Of " + employee + " Added Successfully.");
        } catch (BadRequest e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());
            RequestDispatcher rd=req.getRequestDispatcher("index.html");  
            rd.include(req, res);
        } catch (EmployeeNotFound e1) {
            logger.warn(e1.getMessage());   
        }
    }

    /**
     * <p>
     * Get id from user and store it in the list and then return List of Id's.
     * </p>
     *
     * @param {@link int} noOfTrainers - no of trainer to add in the list.
     * @return {@link List<String>} trainerNames -  return List of trainer ID.
     **/
    private List<String> addTrainersIdAsList(int noOfTrainers) {
        List<String> trainersId = new ArrayList<>();
        for (int i = 1; i <= noOfTrainers; i++) {
            logger.info("id of Trainer " + i + " :");
            trainersId.add(userInput.nextLine());
        }
        return trainersId;
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
    private void showTrainerDetails(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (listOfTrainers.isEmpty()) {
            out.println("<h2>No Trainer Date Found</h2>");
            logger.info("\nNo Trainer Data Found\n");
        } else {
            logger.info("\nDetails of Trainer\n");
            out.print("<html><style>table, th, td {border: 1px solid black;border-collapse: collapse;}</style><body><h2>Details of Trainer</h2>" 
                + "<table style='width:100%'><tr><th>Employee Id</th><th>Name</th><th>Gender</th><th>Qualification</th><th>Address</th>"
                + "<th>MobileNumber</th><th>Email Id</th><th>Date Of Joining</th><th>Training Experience</th>"
                + "<th>No of Trainees</th><th></th></tr>");
            for (Trainer trainer : listOfTrainers) {
                Employee employee = trainer.getEmployee();
                out.print("<tr><td>" + employee.getId() + "</td><td>" + employee.getName() + "</td><td>" + employee.getGender() 
                        + "</td><td>" + employee.getQualification() + "</td><td>" + employee.getAddress() + "</td><td>" 
                        + employee.getMobileNumber() + "</td><td>" + employee.getEmailId() + "</td><td>" + employee.getDateOfJoining()
                        + "</td><td>" + trainer.getExperience() + "</td><td>" + trainer.getTrainees().size() +"</td><tr>");
            }
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
    private void showTraineeDetails(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        if (listOfTrainees.isEmpty()) {
            logger.info("\nNo Trainee Data Found\n");
        } else {
            logger.info("\nDetails of Trainee\n");
            out.print("<html><style>table, th, td {border: 1px solid black;border-collapse: collapse;}</style><body><h2>Details of Trainee</h2>" 
                + "<table style='width:100%'><tr><th>Employee Id</th><th>Name</th><th>Gender</th><th>Qualification</th><th>Address</th>"
                + "<th>MobileNumber</th><th>Email Id</th><th>Date Of Joining</th><th>Training Period</th>"
                + "<th>Trainer Id</th><th></th></tr>");
            for (Trainee trainee : listOfTrainees) {
                Employee employee = trainee.getEmployee();
                List<Integer> trainerIds = new ArrayList<>();
                for (Trainer trainer : trainee.getTrainers()) {
                    trainerIds.add(trainer.getEmployee().getId());
                }
                out.print("<tr><td>" + employee.getId() + "</td><td>" + employee.getName() + "</td><td>" + employee.getGender() 
                        + "</td><td>" + employee.getQualification() + "</td><td>" + employee.getAddress() + "</td><td>" 
                        + employee.getMobileNumber() + "</td><td>" + employee.getEmailId() + "</td><td>" + employee.getDateOfJoining()
                        + "</td><td>" + trainee.getTrainingPeriod() + "</td><td>" + trainerIds.toString().replaceAll("[\\[\\]]","") +"</td></tr>");
            }
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
        String tempId;
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (listOfTrainers.isEmpty()) {
            out.println("<h3>No Trainer Data Found</h3>");
            RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
            redirect.include(req, res);
            logger.info("\nNo Trainer Data Found\n");
        } else {
            logger.info("\nEnter Trainer Id to delete data\n");
            int id = Integer.parseInt(req.getParameter("ID"));
            try {
                trainerService.removeTrainerById(id);
                out.print("<h3>Trainer " + id + " Successfully deleted.");
                RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
                redirect.include(req, res);
                logger.info("Trainer " + id + " Successfully deleted.");
            } catch (EmployeeNotFound e) {
                out.print("<h3>Trainer " + id + " Successfully deleted.");
                RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
                redirect.include(req, res);
                logger.error(e.getMessage());
            }
        }
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
        if (listOfTrainees.isEmpty()) {
            out.println("<h3>No Trainee Data Found</h3>");
            RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
            redirect.include(req, res);
            logger.info("\nNo Trainee Data Found\n");
        } else {
            logger.info("\nEnter Trainee Id to delete data\n");
            int id = Integer.parseInt(req.getParameter("ID"));
            try {
                traineeService.removeTraineeById(id);
                out.print("<h3>Trainee " + id + " Successfully deleted.");
                RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
                redirect.include(req, res);
                logger.info("Trainee " + id + " Successfully deleted.</h3>");
            } catch (EmployeeNotFound e) {
                out.print("<h3>" + e.getMessage() + "</h3>");
                RequestDispatcher redirect = req.getRequestDispatcher("index.html");  
                redirect.include(req, res);
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * <p>
     * Method asks for selecting a valid gender and return the Correct gender as a string  
     * if selected the wrong option it will ask again and again until getting the correct input.
     * </p>
     *
     * @return {@link String} return Gender
     *          Gender name as String.
     **/
    private String getValidGender() {
        String gender = null;
        logger.info(Constant.AVAILABLE_GENDER);
        do {
            String genderOption = userInput.nextLine();
            switch(genderOption) {
                case "1":
                    gender = Gender.MALE.type;
                    break;

                case "2":
                    gender = Gender.FEMALE.type;
                    break;

                case "3":
                    gender = Gender.OTHERS.type;
                    break;

                default:
                    logger.warn("Please Select valid Option");
            }
        } while (null == gender);
        return gender;
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
    private void updateTrainerDetailsById() {
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (listOfTrainers.size() != 0) {
            logger.info("Please enter Trainer Id to Update Details");
            int id = getValidNumber();
            try {
                Trainer oldTrainer = trainerService.getTrainerById(id);
                logger.info(oldTrainer);
                updateAllDetailsOfEmployee(EmployeeType.TRAINER.type, oldTrainer);
            } catch (EmployeeNotFound e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.info("No Trainer Details found to Update.");
        }
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
    private void updateTraineeDetailsById() {
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        if (listOfTrainees.size() != 0) {
            logger.info("Please enter Trainee Id to Update Details");
            int id = getValidNumber();
            try {
                Trainee oldTrainee = traineeService.getTraineeById(id);
                logger.info(oldTrainee);
                updateAllDetailsOfEmployee(EmployeeType.TRAINEE.type, oldTrainee);
            } catch (EmployeeNotFound e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.info("No Trainee Details found to Update.");
        }
    }

    /**
     * <p>
     * The method that asks for input from the user to update an object
     * and then sent the details to a service class for validation that method
     * returns an error as list. Check the list for the validation error. 
     * If errors in the list then get the particular invalid details from the 
     * user and sent back the value for validation in loop
     * once get all valid details, the loop will end. 
     * </p>
     *
     * @param {@link String} employee
     *        It defines the which object is to update trainer or trainee.
     * @param {@link T} object 
     *        It a generic type any object it accepted.
     * @return {@link void} return nothing 
     **/
    private <T> void updateAllDetailsOfEmployee(String employee, T object) throws EmployeeNotFound{
        int noOfTrainers = 0;
        List<Integer> validationErrorInUpdate = new ArrayList<>();
        List<String> trainerIdAsList = new ArrayList<>();
        logger.info("Update fields. (press enter button to skip)");
        userInput.nextLine();
        logger.info("Qualification :");
        String qualification = userInput.nextLine();
        logger.info("Address :");
        String address = userInput.nextLine();
        logger.info("Mobile Number :");
        String mobileNumber = userInput.nextLine();
        if (employee.equals(EmployeeType.TRAINEE.type)) {
            logger.info("No of trainer names to add :");
            do {
                String temp = userInput.nextLine();
                if (StringUtil.isvalidNumberInput(temp) && !temp.equals("")) {
                    noOfTrainers = Integer.parseInt(temp);
                } else {
                    logger.info("Please enter a valid input");
                    noOfTrainers = -1;
                }
            } while (noOfTrainers == -1);
            trainerIdAsList = addTrainersIdAsList(noOfTrainers);
        }
        do {
            validationErrorInUpdate.clear();
            try {
                if (employee.equals(EmployeeType.TRAINER.type)) {
                    Trainer trainer = (Trainer)object;
                    validationErrorInUpdate = trainerService.updateAllDetailsOfTrainerById(
                            qualification, address, mobileNumber,
                            trainer);
                } else {
                    Trainee trainee = (Trainee)object;
                    validationErrorInUpdate = traineeService.updateAllDetailsOfTraineeById(
                            qualification, address, mobileNumber,
                            trainerIdAsList, trainee);
                }
            } catch (BadRequest e) {
                logger.warn(e.getMessage());
                validationErrorInUpdate = e.errors;
            }
            if (validationErrorInUpdate.size() != 0) {
                for (Integer error : validationErrorInUpdate) {
                    switch (error) {
                        case 1:
                            logger.info("Mobile Number :");
                            mobileNumber = userInput.nextLine();
                            break;

                        case 2:
                            if (employee.equals(EmployeeType.TRAINEE.type)) {
                                trainerIdAsList.clear();
                                trainerIdAsList = addTrainersIdAsList(noOfTrainers);
                            }
                            break;
                    }
                }
            }
        } while (validationErrorInUpdate.size() != 0);
        logger.info("Details of " + employee + " Updated Successfully.");
    }

    /**
     * <p>
     * It get input from the user as string and check it is int or not.
     * ask input until get the correct input.
     * </p>
     *
     * @return {@link int} number only. 
     **/
    private int getValidNumber() {
        String string;
        do {
            string = userInput.next();
            logger.warn(StringUtil.isvalidNumberInput(string)
                    ? ""
                    : "Please Enter Number only");
        } while (!StringUtil.isvalidNumberInput(string));
        return Integer.parseInt(string);
    }

}