[1mdiff --git a/com/ideas2it/employee/controller/EmployeeController.java b/com/ideas2it/employee/controller/EmployeeController.java[m
[1mindex 595a8fb..285841e 100644[m
[1m--- a/com/ideas2it/employee/controller/EmployeeController.java[m
[1m+++ b/com/ideas2it/employee/controller/EmployeeController.java[m
[36m@@ -105,11 +105,11 @@[m [mpublic class EmployeeController {[m
                 [m
             case 9:[m
                 logger.info("Exited");[m
[31m-                userMenu = "stop";[m
[32m+[m[32m                System. exit(0);[m
                 break; [m
                 [m
             default:[m
[31m-                logger.info("\nInvalid Option");[m
[32m+[m[32m                logger.warn("Invalid Option\n");[m
                 break;          [m
             }[m
         } while (userMenu == "run");     [m
[36m@@ -181,10 +181,10 @@[m [mpublic class EmployeeController {[m
                                       trainerIdAsList, trainingPeriod);     [m
                 }[m
             } catch (BadRequest e) {[m
[31m-                logger.info(e.getMessage());[m
[32m+[m[32m                logger.warn(e.getMessage());[m
                 validationError = e.errors;[m
             } catch (EmployeeNotFound e1) {[m
[31m-                logger.info(e1.getMessage());[m
[32m+[m[32m                logger.warn(e1.getMessage());[m
             }  [m
             [m
             if (validationError.size() != 0) {[m
[36m@@ -264,10 +264,10 @@[m [mpublic class EmployeeController {[m
     private void showTrainerDetails() {[m
         List<Trainer> listOfTrainers = trainerService.getTrainers();[m
         if (listOfTrainers.isEmpty()) {[m
[31m-            System.out.print("\nNo Trainer Data Found\n");[m
[32m+[m[32m            logger.info("\nNo Trainer Data Found\n");[m
         } else {[m
             logger.info("\nDetails of Trainer\n");[m
[31m-            listOfTrainers.forEach( (trainer) -> logger.info(trainer));[m
[32m+[m[32m            listOfTrainers.forEach( (trainer) -> logger.info(trainer + "\n"));[m
         }[m
     }[m
    [m
[36m@@ -283,10 +283,10 @@[m [mpublic class EmployeeController {[m
     private void showTraineeDetails() {[m
         List<Trainee> listOfTrainees = traineeService.getTrainees();[m
         if (listOfTrainees.isEmpty()) {[m
[31m-            System.out.print("\nNo Trainee Data Found\n");[m
[32m+[m[32m            logger.info("\nNo Trainee Data Found\n");[m
         } else {[m
             logger.info("\nDetails of Trainee\n");[m
[31m-            listOfTrainees.forEach( (trainee) -> logger.info(trainee));[m
[32m+[m[32m            listOfTrainees.forEach( (trainee) -> logger.info(trainee + "\n"));[m
         }[m
     }[m
     [m
[36m@@ -304,7 +304,7 @@[m [mpublic class EmployeeController {[m
         String tempId;[m
         List<Trainer> listOfTrainers = trainerService.getTrainers();[m
         if (listOfTrainers.isEmpty()) {[m
[31m-            System.out.print("\nNo Trainer Data Found\n");[m
[32m+[m[32m            logger.info("\nNo Trainer Data Found\n");[m
         } else { [m
             logger.info("\nEnter Trainer Id to delete data\n"); [m
             int id = getValidNumber();[m
[36m@@ -330,7 +330,7 @@[m [mpublic class EmployeeController {[m
     private void deleteTraineeDetailsById() {[m
         List<Trainee> listOfTrainees = traineeService.getTrainees();[m
         if (listOfTrainees.isEmpty()) {[m
[31m-            System.out.print("\nNo Trainee Data Found\n");[m
[32m+[m[32m            logger.info("\nNo Trainee Data Found\n");[m
         } else { [m
             logger.info("\nEnter Trainee Id to delete data\n");[m
             int id = getValidNumber();[m
[36m@@ -338,7 +338,7 @@[m [mpublic class EmployeeController {[m
                 traineeService.removeTraineeById(id);[m
                 logger.info("Trainee " + id + " Successfully deleted.");[m
             } catch (EmployeeNotFound e) {[m
[31m-                logger.info(e.getMessage());[m
[32m+[m[32m                logger.error(e.getMessage());[m
             }[m
         }[m
     }[m
[36m@@ -371,7 +371,7 @@[m [mpublic class EmployeeController {[m
                 break;[m
 [m
             default:[m
[31m-                logger.info("Please Select valid Option");           [m
[32m+[m[32m                logger.warn("Please Select valid Option");[m[41m           [m
             }[m
         } while (null == gender);[m
         return gender;[m
[36m@@ -398,7 +398,7 @@[m [mpublic class EmployeeController {[m
                 logger.info(oldTrainer);[m
                 updateAllDetailsOfEmployee(EmployeeType.TRAINER.type, oldTrainer);              [m
             } catch (EmployeeNotFound e) {[m
[31m-                logger.info(e.getMessage());[m
[32m+[m[32m                logger.error(e.getMessage());[m
             }[m
         } else {[m
             logger.info("No Trainer Details found to Update.");[m
[36m@@ -426,7 +426,7 @@[m [mpublic class EmployeeController {[m
                 logger.info(oldTrainee);[m
                 updateAllDetailsOfEmployee(EmployeeType.TRAINEE.type, oldTrainee);      [m
             } catch (EmployeeNotFound e) {[m
[31m-                logger.info(e.getMessage());[m
[32m+[m[32m                logger.error(e.getMessage());[m
             }[m
         } else {[m
             logger.info("No Trainee Details found to Update.");[m
[36m@@ -492,7 +492,7 @@[m [mpublic class EmployeeController {[m
                                               trainerIdAsList, trainee);[m
                }[m
             } catch (BadRequest e) {[m
[31m-                logger.info(e.getMessage());[m
[32m+[m[32m                logger.warn(e.getMessage());[m
                 validationErrorInUpdate = e.errors;[m
             }[m
             if (validationErrorInUpdate.size() != 0) {[m
[36m@@ -528,7 +528,7 @@[m [mpublic class EmployeeController {[m
         String string;[m
         do {[m
             string = userInput.next();[m
[31m-            logger.info(StringUtil.isvalidNumberInput(string)[m
[32m+[m[32m            logger.warn(StringUtil.isvalidNumberInput(string)[m
                                ? "" [m
                                : "Please Enter Numnber only");[m
         } while (!StringUtil.isvalidNumberInput(string));[m
