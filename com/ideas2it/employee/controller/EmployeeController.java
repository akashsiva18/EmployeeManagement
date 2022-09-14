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
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
public class EmployeeController {
    private static Scanner userInput = new Scanner(System.in);
    private TrainerServiceIntf trainerService = new TrainerServiceImpl();
    private TraineeServiceIntf traineeService = new TraineeServiceImpl();

    public static void main(String[] args) { 
        EmployeeController employeeControl = new EmployeeController();
        employeeControl.showUserMenu();
    }

    /**
     * <p>
     * It is used show menu to user and have option to select methods 
     * in switch to add, delete, update and remove of trainer and trainee objects.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void showUserMenu() {
        String userMenu = "run";
	do {
            int userChoice = 0;    
            System.out.print("\nSelect Option\n\n" +
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
                System.out.println("Exited");
                userMenu = "stop";
                break; 
                
            default:
                System.out.println("\nInvalid Option");
                break;          
            }
        } while (userMenu == "run");     
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
    private void addEmployees(String employee) {
        int noOfTrainers = 0;
        String trainingPeriod = "";
        List<Integer> validationError = new ArrayList<>();
        List<String> trainerIdAsList = new ArrayList<>();
        System.out.println((employee == EmployeeType.TRAINER.type) 
                           ? "Enter Trainer Details" 
                           : "Enter Trainee Details");
        userInput.nextLine();
        System.out.println("Name :");
        String name = userInput.nextLine();
        System.out.println("Date Of Birth (DD/MM/YYYY) : ");
        String dateOfBirth = userInput.nextLine();
        String gender = getValidGender();
        System.out.println("Qualification :");
        String qualification = userInput.nextLine();
        System.out.println("Address :");
        String address = userInput.nextLine();
        System.out.println("Mobile Number :");
        String mobileNumber = userInput.nextLine();
        System.out.println("Email Id");
        String emailId = userInput.nextLine();
        System.out.println("Date Of Joining(DD/MM/YYYY) :");
        String dateOfJoining = userInput.nextLine();
        if (employee.equals(EmployeeType.TRAINEE.type)) {
            System.out.println("No of trainer to add :");
            do {
                String temp = userInput.nextLine();
                if (StringUtil.isvalidNumberInput(temp) && temp != "") {
                    noOfTrainers = Integer.parseInt(temp);
                } else {
                    System.out.println("Plese enter a valid input");
                    noOfTrainers = -1;
                }
            } while (noOfTrainers == -1);
            trainerIdAsList = addTrainersIdAsList(noOfTrainers);
            System.out.println("Training Period in months");
            trainingPeriod = userInput.nextLine();
        }

        do {
            validationError.clear();
            try {
                if (employee.equals(EmployeeType.TRAINER.type)) {
                    validationError = trainerService.validateAndAddTrainerDetails(
                                      name, dateOfBirth, gender, qualification, 
                                      address, mobileNumber, emailId, dateOfJoining);
                } else {
                    validationError = traineeService.validateAndAddTraineeDetails(name,
                                      dateOfBirth,gender,qualification,address,
                                      mobileNumber, emailId, dateOfJoining,
                                      trainerIdAsList, trainingPeriod);     
                }
            } catch (BadRequest e) {
                System.out.println(e.getMessage());
                validationError = e.errors;
            } catch (EmployeeNotFound e1) {
                System.out.println(e1.getMessage());
            }  
            
            if (validationError.size() != 0) {
                for (Integer errors : validationError) {
                    switch (errors) {
                    case 1:
                        System.out.println("Name (Must in Capital Letters):");
                        name = userInput.nextLine();
                        break;
                 
                    case 2:
                        System.out.println("Date Of Birth (DD/MM/YYYY) : ");
                        dateOfBirth = userInput.nextLine();
                        break;

                    case 3:
                        System.out.println("Mobile Number :");
                        mobileNumber = userInput.nextLine();
                        break;
 
                    case 4:
                        System.out.println("Enter Company Email Id");
                        emailId = userInput.nextLine();
                        break;
 
                    case 5:
                        System.out.println("Date Of Joining(DD/MM/YYYY) :");
                        dateOfJoining = userInput.nextLine();
                        break;
                   
                    case 6: 
                        if (employee.equals(EmployeeType.TRAINEE.type)) {
                            trainerIdAsList.clear();
                            trainerIdAsList = addTrainersIdAsList(noOfTrainers);
                        }
                        break;

                    case 7:
                        if (employee.equals(EmployeeType.TRAINEE.type)) {
                            System.out.println("Training Period in months");
                            trainingPeriod = userInput.nextLine();
                            break;
                        }
                    }
                }
            }
        } while (validationError.size() != 0);
        System.out.println("Deatils Of " + employee + " Added Successfully.");
    }

    /**
     * <p>
     * Get id from user and store it in the list and then return.
     * </p>
     *
     * @param {@link int} noOfTrainers - no of trainer to add in the list.
     * @return {@link List<String>} trainerNames -  return List of trainer ID.
     **/
    private List<String> addTrainersIdAsList(int noOfTrainers) {
        List<String> trainersId = new ArrayList<>(); 
        for (int i = 1; i <= noOfTrainers; i++) {
            System.out.println("id of Trainer " + i + " :");
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
    private void showTrainerDetails() {
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (listOfTrainers.isEmpty()) {
            System.out.print("\nNo Trainer Data Found\n");
        } else {
            System.out.println("\nDetails of Trainer\n");
            listOfTrainers.forEach( (trainer) -> System.out.println(trainer));
            System.out.println("------------------------------");
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
    private void showTraineeDetails() {
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        if (listOfTrainees.isEmpty()) {
            System.out.print("\nNo Trainee Data Found\n");
        } else {
            System.out.println("\nDetails of Trainee\n");
            listOfTrainees.forEach( (trainee) -> System.out.println(trainee));
            System.out.println("------------------------------");
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
    private void deleteTrainerDetailsById() {
        String tempId;
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (listOfTrainers.isEmpty()) {
            System.out.print("\nNo Trainer Data Found\n");
        } else { 
            System.out.println("\nEnter Trainer Id to delete data\n"); 
            int id = getValidNumber();
            try {
                trainerService.removeTrainerById(id);
                System.out.println("Trainer " + id + " Successfully deleted.");
            } catch (EmployeeNotFound e) {
                System.out.println(e.getMessage());
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
    private void deleteTraineeDetailsById() {
        List<Trainee> listOfTrainees = traineeService.getTrainees();
        if (listOfTrainees.isEmpty()) {
            System.out.print("\nNo Trainee Data Found\n");
        } else { 
            System.out.println("\nEnter Trainee Id to delete data\n");
            int id = getValidNumber();
            try {
                traineeService.removeTraineeById(id);
                System.out.println("Trainee " + id + " Successfully deleted.");
            } catch (EmployeeNotFound e) {
                System.out.println(e.getMessage());
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
        System.out.println(Constant.AVAILABLE_GENDER);
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
                System.out.println("Please Select valid Option");           
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
            System.out.println("Please enter Trainer Id to Update Details");
            int id = getValidNumber();
            try {
                Trainer oldTrainer = trainerService.getTrainerById(id);
                System.out.println(oldTrainer);
                updateAllDetailsOfEmployee(EmployeeType.TRAINER.type, oldTrainer);              
            } catch (EmployeeNotFound e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No Trainer Details found to Update.");
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
            System.out.println("Please enter Trainee Id to Update Details");
            int id = getValidNumber();
            try {
                Trainee oldTrainee = traineeService.getTraineeById(id);
                System.out.println(oldTrainee);
                updateAllDetailsOfEmployee(EmployeeType.TRAINEE.type, oldTrainee);      
            } catch (EmployeeNotFound e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No Trainee Details found to Update.");
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
        String noOfTrainees = "";
        String trainerName = "";
        int noOfTrainers = 0;
        List<Integer> validationErrorInUpdate = new ArrayList<Integer>();
        List<String> trainerIdAsList = new ArrayList<>();
        System.out.println("Updation fields. (press enter button to skip)");
        userInput.nextLine();
        System.out.println("Qualification :");
        String qualification = userInput.nextLine();
        System.out.println("Address :");
        String address = userInput.nextLine();
        System.out.println("Mobile Number :");
        String mobileNumber = userInput.nextLine();
        if (employee.equals(EmployeeType.TRAINEE.type)) {
            boolean isNumber = false;
            System.out.println("No of trainer names to add :");
            do {
                String temp = userInput.nextLine();
                if (StringUtil.isvalidNumberInput(temp) && temp != "") {
                    noOfTrainers = Integer.parseInt(temp);
                } else {
                    System.out.println("Plese enter a valid input");
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
                System.out.println(e.getMessage());
                validationErrorInUpdate = e.errors;
            }
            if (validationErrorInUpdate.size() != 0) {
                for (Integer error : validationErrorInUpdate) {
                    switch (error) {
                    case 1:
                        System.out.println("Mobile Number :");
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
        System.out.println("Details of " + employee + " Updated Successfully.");
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
            System.out.println(StringUtil.isvalidNumberInput(string)
                               ? "" 
                               : "Please Enter Numnber only");
        } while (!StringUtil.isvalidNumberInput(string));
        return Integer.parseInt(string);
    }

}