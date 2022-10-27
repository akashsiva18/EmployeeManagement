<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ideas2it.employee.DTO.TrainerDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="traineeOfTrainerStyle.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Trainee Of the Trainer</title>
</head>
<body>

<div class="all">
    <div class="Trainer_details">
        <table class="design">
            <tr>
                <td class="photo_width">
                    <i class="fa fa-user-circle-o fa-5x" style="colour:black;" aria-hidden="true"></i>
                </td>
                <td class="left_float">
                    ${trainerDTO.name}<p class="mailid">${trainerDTO.emailId}</p>
                </td>
                <td class="right_float">
                    No.Of Trainees<p class="count">${trainerDTO.noOfTrainees}</p>
                </td>
                <td></td>
            </tr>
        </table>
    </div>


    <div class="trainee_details">
        <table>
            <tr>
                <th class="blank">
                </th>
                <th class="name">
                    Name
                </th>
                <th>
                    Qualification
                </th>
                <th>
                    Mail Id
                </th>
                <th>
                    Training period
                </th>
            </tr>
            <c:forEach items="${traineeDTOs}" var="trainee">
            <tr>
                <td class="trainee_photo_width">
                    <i class="fa fa-user-circle-o fa-2x" aria-hidden="true"></i>
                </td>
                <td class="name">
                    ${trainee.name}
                </td>
                <td class="center">
                    ${trainee.qualificationDTO.course}
                </td>
                <td class="center">
                    ${trainee.emailId}
                </td>
                <td class="center">
                    ${trainee.trainingPeriod}
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="back_button">
<a class="button" href="/viewSingleTrainer?id=${trainerDTO.id}">back</a>
</div>
</body>
</html>