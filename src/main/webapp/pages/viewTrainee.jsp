<%@page import="com.ideas2it.employee.DTO.TraineeDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <style>table, th, td {border: 1px solid black;border-collapse: collapse; background-color:Lightgray;}
        table {height: 20% page; overflow-y:scroll;}
        th {background-color:black; color:white;}
        h2 {
           text-align: center;
           font-size: 40px;    
        }
        .scrollTable {
             height: 300px;
             width: 100%;
             overflow-y: scroll;
        }
        </style>
        <body style="margin-top : 100px;">
         <h4>${message}</h4>
            <h2>Details of Trainee</h2>
            <div class="scrollTable">
            <table style='width:100%'>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Qualification</th>
                    <th>Address</th>
                    <th>MobileNumber</th>
                    <th>Email Id</th>
                    <th>Date Of Joining</th>
                    <th>Training Period</th>
                    <th>Trainer Name</th>
                    <th colspan=2>Action</th>
                </tr>
		<%List<TraineeDTO> trainees = (List<TraineeDTO>)request.getAttribute("trainees");
		if (null != trainees) {
            for(TraineeDTO trainee: trainees) {
                %>
            <tr>
                <td style ="width:4%; text-align: right;"><%= trainee.getId() %></td>
                <td><%= trainee.getName() %></td>
                <td style="width:5%; text-align:center;"><%= trainee.getGender() %></td>
                <td style="width:6%; text-align:center;"><%= trainee.getQualificationDTO() %></td>
                <td style="width:18%;"><%= trainee.getAddress() %></td>
                <td style="width:7%; text-align:center;"><%= trainee.getMobileNumber() %></td>
                <td style="width:15%;"><%= trainee.getEmailId() %></td>
                <td style="width:9%; text-align:center;"><%= trainee.getDateOfJoining() %></td>
                <td style="width:5%; text-align:center;"><%= trainee.getTrainingPeriod() %></td>
                <td style="width:5%;"><%= trainee.getTrainersName() %></td>
                <td style="width:2%;"><a href=/updateTraineeForm?flag=updateTrainee&ID=<%=trainee.getId()%> ><input type="button" value="update"></a></td>
                <td style="width:2%;"><a href=deleteTrainee?ID=<%=trainee.getId()%>  ><input type="button" value="delete"></a></td>
            </tr>
		<%}}%>
		</table><br></div>
		<a href="/traineeForm?flag=addTrainee"><input type="button" value="Add Trainee" ></a>
		<a href="/"><input type="button" value="Go back!" ></a>
	</body>
    
</html>
