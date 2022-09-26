<%@page import="com.ideas2it.employee.model.Trainee, com.ideas2it.employee.model.Employee, com.ideas2it.employee.model.Trainer"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student List</title>
</head>
<body>
        <style>table, th, td {border: 1px solid black;border-collapse: collapse; background-color:Lightgray;}
        th {background-color:black; color:white;}
        h2 {
           text-align: center;
           font-size: 40px;    
        }
        </style>
        <body>
            <h2>Details of Trainee</h2> 
            <table style='width:100%'>
                <tr>
                    <th>Employee Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Qualification</th>
                    <th>Address</th>
                    <th>MobileNumber</th>
                    <th>Email Id</th>
                    <th>Date Of Joining</th>
                    <th>Training Period</th>
                    <th>Trainer Id</th>
                    <th colspan=2>Action</th>
                </tr>
		
		<%ArrayList<Trainee> trainees = (ArrayList<Trainee>)request.getAttribute("Trainees");
		for(Trainee trainee: trainees) {
                    Employee employee = trainee.getEmployee();
                    List<Integer> trainerIds = new ArrayList<>();
                    for (Trainer trainer : trainee.getTrainers()) {
                        trainerIds.add(trainer.getEmployee().getId());
                    }%>
		<tr>
		    <td><%= employee.getId() %></td>
		    <td><%= employee.getName() %></td>
		    <td><%= employee.getGender() %></td>
		    <td><%= employee.getQualification() %></td>
		    <td><%= employee.getAddress() %></td>
		    <td><%= employee.getMobileNumber() %></td>
		    <td><%= employee.getEmailId() %></td>
		    <td><%= employee.getDateOfJoining() %></td>
		    <td><%= trainee.getTrainingPeriod() %></td>
		    <td><%= trainerIds.toString().replaceAll("[\\[\\]]","") %></td>
                    <td>
                        <a href=test?flag=updateTrainee&ID=<%=employee.getId()%> ><input type="button" value="update"></a>
                    </td>
                    <td>
                        <a href=test?flag=deleteTrainee&ID=<%=employee.getId()%>  ><input type="button" value="delete"></a>
                    </td>
		</tr>
		<%}%>
		</table>
                <a href=index.html><input type="button" value="Go back!" ></a>
	</body>
    
</html>
