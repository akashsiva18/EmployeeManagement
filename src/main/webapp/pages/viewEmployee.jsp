<%@page import="com.ideas2it.employee.DTO.EmployeeDTO, com.ideas2it.employee.DTO.TrainerDTO"%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="styleview.css" type="text/css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="left">
  <p><div class="image_set">
   <i class="fa fa-user-circle-o fa-5x" style="colour:black;" aria-hidden="true"></i>
  <p class="profile_name" >${employee.name}</p>
  <p class="profile_name"></p>
  <p class="profile_role" >${employee.roleDTO.description}</p>
</div>
  <div class="profile_data">
    <table>
      <tr class="bottom_row">
        <td>
          <i class="fa fa-phone" aria-hidden="true"></i></td><td>
          <p class="profile_Details" > ${employee.mobileNumber}</p>
        </td>
      </tr>
      <tr class="bottom_row">
        <td>
          <i class="fa fa-calendar" aria-hidden="true"></i></td><td>
          <p class="profile_Details" > ${employee.dateOfBirth}</p>
        </td>
      </tr><tr class="bottom_row">
      <td>
        <i class="fa fa-graduation-cap" aria-hidden="true"></i></td><td>
        <p class="profile_Details">${employee.qualificationDTO.course}</p>
      </td>
    </tr><tr class="bottom_row">
      <td>
        <i class="fa fa-envelope" aria-hidden="true"></i></td><td>
        <p class="profile_Details" >${employee.emailId}</p>
      </td>
    </tr><tr class="bottom_row">
      <td>
        <i class="fa fa-map-marker" aria-hidden="true"></i></td><td>
        <p class="profile_Details">${employee.address}</p>
      </td>
    </tr>
    </table>

    <ul>
    <li>
    <c:if test="${employee.roleDTO.description == 'Trainer'}">
        <a class="button" href=/viewTraineesOfTrainer?id=${employee.id}>Trainees</a>
        </c:if>
    </li>
<c:if test="${authority == 'ROLE_ADMIN'}">
    <li>
       <a class="button" href=/update${employee.roleDTO.description}Form?flag=updateTrainer&ID=${employee.id}>Update</a>
    </li>

    <li>
    <a class="button" href=delete${employee.roleDTO.description}?ID=${employee.id}>Delete</a>
    </li>
</c:if>
    <li>
    <a class="button_back" href="/view${employee.roleDTO.description}" >back</a>
    </li>
    </ul>


  </div>
</div>

<div class="right">
  <div class="top">
    <p class="headings">Professional summary</p>
    <p>
      I recollect that my first exploit in squirrel-shooting was in a grove of tall walnut-trees that shades one side of
      the valley. I had wandered into it at noontime, when all nature is peculiarly quiet, and was startled by the roar
      of my own gun, as it broke the Sabbath stillness around and was prolonged and reverberated by the angry echoes.
    </p>
  </div>
  <div class="middle">
    <p class="headings">Work Experience</p>
    <table class="custom_table">
      <tr>
        <td colspan="4" class="top_data">
          Senior Front End Developer
        </td>
        <td  class="top_data_button">
          Full Time
        </td>
      </tr>
      <tr class="bottom_row">
        <td class="icon_style">
          <i class="fa fa-building" aria-hidden="true"></i>
        </td>
        <td class="bottom_data">
          Apple Inc.
        </td>
        <td class="icon_style">
          <i class="fa fa-map-marker" aria-hidden="true"></i>
        </td>
        <td class="bottom_data">
          Los Angeles.
        </td>
        <td class="right_float">
          <i class="fa fa-calendar" aria-hidden="true"></i> April 12,2020.
        </td>
      </tr>
      <tr>
        <td colspan="4" class="top_data">
          Junior Front End Developer
        </td>
        <td  class="top_data_button">
          Full Time
        </td>
      </tr>
      <tr class="bottom_row">
        <td class="icon_style">
          <i class="fa fa-building" aria-hidden="true"></i>
        </td>
        <td class="bottom_data">
          Figuma
        </td>
        <td class="icon_style">
          <i class="fa fa-map-marker" aria-hidden="true"></i>
        </td>
        <td class="bottom_data">
          Los Angeles.
        </td>
        <td class="right_float">
          <i class="fa fa-calendar" aria-hidden="true"></i> April 12,2018 - April 01,2020.
        </td>
      </tr>
      <tr>
        <td colspan="4" class="top_data">
          Intern Developer
        </td>
        <td  class="top_data_button">
          Full Time
        </td>
      </tr>
      <tr class="bottom_row">
        <td class="icon_style">
          <i class="fa fa-building" aria-hidden="true"></i>
        </td>
        <td class="bottom_data">
          Microsoft
        </td>
        <td class="icon_style">
          <i class="fa fa-map-marker" aria-hidden="true"></i>
        </td>
        <td class="bottom_data">
          New York City
        </td>
        <td class="right_float">
          <i class="fa fa-calendar" aria-hidden="true"></i> April 12,2017 - April 11,2018.
        </td>
      </tr>
    </table>
  </div>
  <div class="bottom">

  <p class="headings">Education</p>
  <table class="custom_table">
    <tr>
      <td colspan="2" class="top_data">
        Master in Software Engineering
      </td>
    </tr>
    <tr class="bottom_row">
      <td class="icon_style">
        <i class="fa fa-graduation-cap" aria-hidden="true"></i>
      </td>
      <td class="bottom_data">
        Massachusetts Institute of Technology
      </td>
      <td class="right_float">
        <i class="fa fa-calendar" aria-hidden="true"></i> Jun 12,2014 - Dec 12,2016
      </td>
    </tr>
    <tr>
      <td colspan="2" class="top_data">
        Bachelor in Software Engineering
      </td>
    </tr>
    <tr class="bottom_row">
      <td class="icon_style">
        <i class="fa fa-graduation-cap" aria-hidden="true"></i>
      </td>
      <td class="bottom_data">
        Massachusetts Institute of Technology
      </td>
      <td class="right_float">
        <i class="fa fa-calendar" aria-hidden="true"></i> Jun 12,2014 - Dec 12,2016
      </td>
    </tr>

  </table>
</div>

</div>

</body>
</html>