<%@page import ="com.ideas2it.employee.model.Trainee, com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
<%@page import ="java.util.List, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<body style="background-color:LightGray; font-size:20px;">

<form:form name = "login_form" method = "Post" model="trainee" action = "/addTrainer">
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

  <form:input type="hidden" path="employee.id" />
  <form:input type="hidden" path="trainerId" />


  Name:<br>
  <form:input  path="employee.name" /><br><br>

  Date Of Birth:<br>
  <form:input type="date" path="employee.dateOfBirth"/><br><br>

  Gender :<br>
  <form:radiobutton path="employee.gender" value="male"/>Male<br>
  <form:radiobutton path="employee.gender" value="female"/>Female<br>
  <form:radiobutton path="employee.gender" value="others"/>Others<br><br>

  Qualification :<br>
  <form:input path="employee.qualification.course" /><br><br>

  Address :<br>
  <form:input path="employee.address" /><br><br>

  Mobile Number :<br>
  <form:input type="number" path="employee.mobileNumber" /><br><br>

  Email ID :<br>
  <form:input type="email" path="employee.emailId" /><br><br>

  Date of Joining:<br>
  <form:input type="date" path="employee.dateOfJoining"/><br><br>

  Training Period(in months)<br>
   <form:input type="date" path="trainingPeriod"/><br><br>
<!--
  Trainer IDs<br>
  <input type="text" id="TrainerIDs" name = "TrainerIDs" value ="<%=((action.equals("updateTrainee")) ? trainerIdsAsString : "" )%>" ><br><br>
-->
  <input type="submit" value="Submit">
  <a href=index.html ><input type="button" value="back"></a>
</form>
</body>
</html>


