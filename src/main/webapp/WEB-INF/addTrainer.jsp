<%@page import ="com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body style="background-color:LightGray; font-size:20px;">

<form:form method = "Post" modelAttribute="trainer" action = "addTrainer">
<h2><% String action = request.getParameter("flag");
       if (action.equals("addTrainer")) {
           %>Add Trainer Details<%;
       } else {
           %>Update Trainer Details<%;
       }%></h2>

  <input type="hidden" name="method" value="<%= ((action.equals("addTrainer")) ? "addTrainer" : "updateTrainer")%>" >

  <form:input type="hidden" path="employee.id" />
  <form:input type="hidden" path="trainerId" />

  Name:<br>
  <form:input  path="employee.name" required="required"/><br><br>

  Date Of Birth:<br>
  <form:input type="date" path="employee.dateOfBirth" required="required"/><br><br>

  Gender :<br>
  <form:radiobutton path="employee.gender" value="male" required="required"/>Male<br>
  <form:radiobutton path="employee.gender" value="female" required="required"/>Female<br>
  <form:radiobutton path="employee.gender" value="others" required="required"/>Others<br><br>

  Qualification :<br>
  <form:input path="employee.qualification.course" required="required"/><br><br>

  Address :<br>
  <form:input path="employee.address" required="required"/><br><br>

  Mobile Number :<br>
  <form:input type="number" path="employee.mobileNumber" required="required"/><br><br>

  Email ID :<br>
  <form:input type="email" path="employee.emailId" required="required"/><br><br>

  Date of Joining:<br>
  <form:input type="date" path="employee.dateOfJoining" required="required"/><br><br>

  Training Experience:<br>
  <form:input type="number" path="experience" required="required"/><br><br>

  <input type="submit" value="Submit">
  <a href="/viewTrainer" ><input type="button" value="back"></a>
</form:form>

</body>
</html>


