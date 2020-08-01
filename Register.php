<?php 
    $con = mysqli_connect("localhost", "USER_ID", "USER_PW", "USER_ID");
    mysqli_query($con,'SET NAMES utf8');

    $userName = $_POST["userName"];
    $userBirthDate = $_POST["userBirthDate"];
    $userPhoneNumber = $_POST["userPhoneNumber"];
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssss", $userName, $userBirthDate, $userPhoneNumber, $userID, $userPassword);
    mysqli_stmt_execute($statement);

    $response = array();

    $response["success"] = true;
    echo json_encode($response);

?>
