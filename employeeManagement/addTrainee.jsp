<%@page import ="com.ideas2it.employee.model.Trainee, com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
<%@page import ="java.util.List, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<body style="background-color:LightGray; font-size:20px;">

<form name = "login_form" method = "Post" action = "test">
<h2><%  String action = request.getParameter("flag");
        if (action.equals("addTrainee")) {
            %><h2>Add Trainee Details</h2><%;
        } else {
            %>Update Trainee Details<%;
        }%></h2>
<%      Trainee trainee = (Trainee)request.getAttribute("oldTrainee");
        Employee employee = null;
        String gender = "";
        List<Integer> trainerIds = new ArrayList<>();
        String trainerIdsAsString = "";
        if (null != trainee) {
            employee = trainee.getEmployee();
            gender = employee.getGender();
                for (Trainer trainer : trainee.getTrainers()) {
                    trainerIds.add(trainer.getEmployee().getId());
                }
            trainerIdsAsString  = trainerIds.toString().replace("[","").replace("]","").replace(" ","");
        }
        session.setAttribute("trainee",trainee);
%>
  <input type="hidden" id="flag" name="flag" value="addTrainee" >
  <input type="hidden" id="flag" name="method" value=<%= ((action.equals("addTrainee")) ? "addTrainee" : "updateTrainee")%> >
  
  <label for="name">Name:</label><br>
  <input type="text" id="name" name="name" value= "<%=((action.equals("updateTrainee")) ? employee.getName(): "" )%>" required ><br><br>

  <label for="dateOfBirth">Date Of Birth:</label><br>
  <input type="date" id="dateOfBirth" name="dateOFBirth" value = "<%=((action.equals("updateTrainee")) ? employee.getDateOfBirth() : "" )%>" required ><br><br>
 
  <label for="gender">Gender :</label><br>
  <input type="radio" id="Male" name="Gender" value="Male" <%=(gender.equals("male")) ? "checked=checked" : "" %>required>
  <label for="Male">Male</label><br>
  <input type="radio" id="Female" name="Gender" value="Female" <%=(gender.equals("female")) ? "checked=checked" : "" %>required>
  <label for="Female">Female</label><br>
  <input type="radio" id="Others" name="Gender" value="Others" <%=(gender.equals("others")) ? "checked=checked" : "" %>required>
  <label for="Others">Others</label><br><br><br>

  <label for="qualification">Qualification :</label><br>
  <input type="text" id="qualification" name="qualification" value = "<%=((action.equals("updateTrainee")) ? employee.getQualification() : "" )%>" required ><br><br>
  
  <label for="address">Address :</label><br>
  <input type="text" id="address" name="address" value = "<%=((action.equals("updateTrainee")) ? employee.getAddress() : "" )%>" required ><br><br>


  <label for="mobileNumber">Mobile Number :</label><br>
  <input type="number" id="mobileNumber" name="mobileNumber" value = "<%=((action.equals("updateTrainee")) ? employee.getMobileNumber() : "" )%>" required ><br><br>

  <label for="emailid">Email ID :</label><br>
  <input type="email" id="EmailId" name="EmailID" value = "<%=((action.equals("updateTrainee")) ? employee.getEmailId() : "" )%>" required ><br><br>

  <label for="dateOFJoining">Date of Joining (DD/MM/YYYY):</label><br>
  <input type="date" id="DateOfJoining" name="DateOfJoining" value = "<%=((action.equals("updateTrainee")) ? employee.getDateOfJoining() : "" )%>" required ><br><br>

  <label for= "TrainingPeriod">Training Period(in months)</label><br>
  <input type ="number" id="TrainingPeriod" name= "TrainingPeriod" value ="<%=((action.equals("updateTrainee")) ? trainee.getTrainingPeriod() : "" )%>" required><br><br>

  <label for="TrainerIDs"> Trainer IDs </label><br>
  <sub>(You can enter multiple Trainer ID with each seperated by comma)</sub><br>
  <input type="text" id="TrainerIDs" name = "TrainerIDs" value ="<%=((action.equals("updateTrainee")) ? trainerIdsAsString : "" )%>" ><br><br>

  <input type="submit" value="Submit">
  <a href=index.html ><input type="button" value="back"></a>
</form>

</body>

</div>
</html>


