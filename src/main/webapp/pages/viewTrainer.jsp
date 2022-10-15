<%@page import="com.ideas2it.employee.DTO.EmployeeDTO, com.ideas2it.employee.DTO.TrainerDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <style>table, th, td {border: 1px solid black;border-collapse: collapse; background-color:Lightgray;}
        th {background-color:black; color:white;}
        table {width:100%;}
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
            <h2>Details of Trainer</h2>
           <div class="scrollTable">
            <table>
                <tr style="position:sticky; top:0;">
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Qualification</th>
                    <th>Address</th>
                    <th>Mobile Number</th>
                    <th>Email Id</th>
                    <th>Date Of Joining</th>
                    <th>Training Experience</th>
                    <th>No of Trainees</th>
                    <th colspan=2>Action</th>
                </tr>

		<%ArrayList<TrainerDTO> trainersDTOs = (ArrayList<TrainerDTO>)request.getAttribute("trainerDTOs");
		if (null != trainersDTOs) {
            for(TrainerDTO trainer: trainersDTOs) {%>
            <tr>
                <td style ="width:4%; text-align: right;"><%= trainer.getId() %></td>
                <td><%= trainer.getName() %></td>
                <td style="width:5%; text-align:center;"><%= trainer.getGender() %></td>
                <td style="width:6%; text-align:center;"><%= trainer.getQualificationDTO() %></td>
                <td style="width:18%;"><%= trainer.getAddress() %></td>
                <td style="width:7%; text-align:center;"><%= trainer.getMobileNumber() %></td>
                <td style="width:15%;"><%= trainer.getEmailId() %></td>
                <td style="width:9%; text-align:center;"><%= trainer.getDateOfJoining() %></td>
                <td style="width:8%; text-align:center;"><%= trainer.getExperience() %></td>
                <td style="width:6%; text-align:center;"><%= trainer.getNoOfTrainees() %></td>
                <td style="width:2%;"><a href=/updateTrainerForm?flag=updateTrainer&ID=<%=trainer.getId()%> ><input type="button" value="update"></a></td>
                <td style="width:2%;"><a href=deleteTrainer?ID=<%=trainer.getId()%>  ><input type="button" value="delete"></a></td>
            </tr>
            <%}}%>
		</table></div><br>
		<a href=/trainerForm?flag=addTrainer ><input type="button" value="Add Trainer"></a>
        <a href=/><input type="button" value="Go back!" ></a>
	</body>
</html>
