<%@page import ="com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

<h2><% String action = request.getParameter("flag");
       if (action.equals("addTrainer")) {
           %>Add Trainer Details<%;
       } else {
           %>Update Trainer Details<%;
       }%></h2>
<%     Trainer trainer = (Trainer)request.getAttribute("oldTrainer");
       Employee employee = null;
       String gender = "";
       if (null != trainer) {
           employee = trainer.getEmployee();
           gender = employee.getGender();
       }
       session.setAttribute("trainer",trainer);


%>

<form name = "login form" method = "get" action = "test">
  <input type="hidden" id="flag" name="flag" value="addTrainer" >
  <input type="hidden" id="flag" name="method" value=<%= ((action.equals("addTrainer")) ? "addTrainer" : "updateTrainer")%> >
  <% request.setAttribute("trainer",trainer); %>
  <label for="name">Name:</label><br>
  <input type="text" id="name" name="name" value ="<%=((action.equals("updateTrainer")) ? employee.getName() : "" )%>" required ><br><br>

  <label for="dateOfBirth">Date Of Birth (DD/MM/YYYY):</label><br>
  <input type="date" id="dateOfBirth" name="dateOFBirth" value ="<%=((action.equals("updateTrainer")) ? employee.getDateOfBirth() : "" )%>" required ><br>
 
  <label for="gender">Gender :</label><br>
  <input type="radio" id="Male" name="Gender" value="Male" <%=(gender.equals("male")) ? "checked=checked" : "" %> required>
  <label for="Male">Male</label><br>
  <input type="radio" id="Female" name="Gender" value="Female" <%=(gender.equals("female")) ? "checked=checked" : "" %>required>
  <label for="Female">Female</label><br>
  <input type="radio" id="Others" name="Gender" value="Others" <%=(gender.equals("others")) ? "checked=checked" : "" %>required>
  <label for="Others">Others</label><br><br>

  <label for="qualification">Qualification :</label><br>
  <input type="text" id="qualification" name="qualification" value ="<%=((action.equals("updateTrainer")) ? employee.getQualification() : "" )%>" required ><br>
  
  <label for="address">Address :</label><br>
  <input type="text" id="address" name="address" value ="<%=((action.equals("updateTrainer")) ? employee.getAddress() : "" )%>" required ><br>


  <label for="mobileNumber">Mobile Number :</label><br>
  <input type="number" id="mobileNumber" name="mobileNumber" value ="<%=((action.equals("updateTrainer")) ? employee.getMobileNumber() : "" )%>" required ><br>

  <label for="emailid">Email ID :</label><br>
  <input type="email" id="EmailId" name="EmailID" value ="<%=((action.equals("updateTrainer")) ? employee.getEmailId() : "" )%>" required ><br>

  <label for="dateOFJoining">Date of Joining (DD/MM/YYYY):</label><br>
  <input type="date" id="DateOfJoining" name="DateOfJoining" value ="<%=((action.equals("updateTrainer")) ? employee.getDateOfJoining() : "" )%>" required ><br>


  <input type="submit" value="Submit">
</form>

</body>
</html>


