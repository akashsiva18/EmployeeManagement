<%@page import ="com.ideas2it.employee.model.Trainee, com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import ="java.util.List, java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<body style="background-color:LightGray; font-size:20px;">

<form:form method ="Post" modelAttribute="trainee" action = "/addTrainee">
<h2><%  String action = request.getParameter("flag");
        if (action.equals("addTrainee")) {
            %><h2>Add Trainee Details</h2><%;
        } else {
            %>Update Trainee Details<%;
        }%></h2>

  <input type="hidden" id="flag" name="flag" value="<%=action%>" >
  <input type="hidden" id="flag" name="method" value=<%= ((action.equals("addTrainee")) ? "addTrainee" : "updateTrainee")%> >

  <form:input type="hidden" path="employee.id" />
  <form:input type="hidden" path="traineeId" />


  Name:<br>
  <form:input path="employee.name" required="required"/><br><br>

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

  Training Period(in months)<br>
  <form:input path="trainingPeriod" required="required"/><br><br>

  Trainers:<br>
  <c:forEach items="${trainers}" var="trainer">
     <form:checkbox path="trainersId" value="${trainer.employee.id}"/>${trainer.employee.id} ${trainer.employee.name}
  </c:forEach>

<br>
  <input type="submit" value="Submit">
  <a href=/viewTrainee ><input type="button" value="back"></a>
</form:form>
</body>
</html>
