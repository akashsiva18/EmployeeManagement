<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ideas2it.employee.DTO.TrainerDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="traineeOfTrainerStyle.css" type="text/css">
    <title>Trainee Of the Trainer</title>
</head>
<body>

<div class="all">
    <div class="Trainer_details">
        <table class="design">
            <tr>
                <td class="photo_widht">
                    <img class="profile_photo" src="profile.png" alt="profilePicture" >
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
                    mailId
                </th>
                <th>
                    Training period
                </th>
            </tr>
            <c:forEach items="${traineeDTOs}" var="trainee">
            <tr>
                <td class="trainee_photo_width">
                    <img class="profile_photo_trainee" src="profile.png" alt="profilePicture" >
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
        <a  href="/viewSingleTrainer?id=${trainerDTO.id}">back</a>
    </div>
</div>
</body>
</html>