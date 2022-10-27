<%@page import="com.ideas2it.employee.DTO.TraineeDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <style>
        table, th, td {border: 1px solid black;border-collapse: collapse; background-color:Lightgray;}
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
            <h2>Details of Trainee</h2>
            <div class="scrollTable">
            <table style='width:100%'>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th></th>
                </tr>
		<%List<TraineeDTO> trainees = (List<TraineeDTO>)request.getAttribute("trainees");
		if (null != trainees) {
            for(TraineeDTO trainee: trainees) {
                %>
            <tr>
                <td style ="width:4%; text-align: center;"><%= trainee.getId() %></td>
                <td style="width:30%;"><%= trainee.getName() %></td>
                <td style="width:2%;"><a href=/viewSingleTrainee?id=<%=trainee.getId()%>><input type="button" value="view Details"></a></td>
            </tr>
		<%}}%>
		</table><br></div>
		<div class="buttons">
		<c:if test="${authority == 'ROLE_ADMIN'}">
		<a href="/traineeForm?flag=addTrainee"><input type="button" value="Add Trainee" ></a>
		</c:if>
		<a href="/"><input type="button" value="Go back!" ></a>
		</div>
	</body>
    
</html>
