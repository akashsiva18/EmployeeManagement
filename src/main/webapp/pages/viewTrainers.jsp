<%@page import="com.ideas2it.employee.DTO.EmployeeDTO, com.ideas2it.employee.DTO.TrainerDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
           font-size: 40px;
           text-align: center;
        }
        .scrollTable {
          height: 300px;
          width: 36%;
          overflow-y: scroll;
          margin-left: 33%;
        }
        .buttons{
          margin-left:33%;
        }
        </style>
        <body style="margin-top : 100px;">
        <h4 style="text-align:center;">${message}</h4>
            <h2 >Trainer Portal</h2>
           <div class="scrollTable">
            <table>
                <tr style="position:sticky; top:0;">
                    <th>Id</th>
                    <th>Name</th>
                    <th></th>
                </tr>

		<%ArrayList<TrainerDTO> trainersDTOs = (ArrayList<TrainerDTO>)request.getAttribute("trainerDTOs");
		if (null != trainersDTOs) {
            for(TrainerDTO trainer: trainersDTOs) {%>
            <tr>
                <td style ="width:4%; text-align: center;"><%= trainer.getId() %></td>
                <td style="width:30%;"><%= trainer.getName() %></td>
                <td style="width:2%;"><a href=/viewSingleTrainer?id=<%=trainer.getId()%> ><input type="button" value="View Trainer"></a></td>
            </tr>
            <%}}%>
		</table></div><br>
        <div class="buttons">
		<c:if test="${authority == 'ROLE_ADMIN'}">
		<a href=/trainerForm?flag=addTrainer ><input type="button" value="Add Trainer"></a>
		</c:if>
        <a href=/><input type="button" value="Go back!" ></a>
        </div>
	</body>
</html>
