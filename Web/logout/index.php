<?php

/*
Logout simply invalidates the current session token and results the user whether or not
they were successfully logged out
*/

$token = $_POST["sessionToken"];
$username = $_POST["username"];

$SQLservername = "localhost";
$SQLusername = "root";
$SQLpassword = "password";
$SQLdatabase = "users";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to server");
}

$sql = "SELECT * FROM people WHERE SessionToken='{$token}' AND Username='{$username}'";
$result = $conn->query($sql);

if($result->num_rows > 0){
        $sqlTokenWrite = "UPDATE people SET SessionToken='noToken' WHERE username='{$username}'";
        $resultOfWrite = $conn->query($sqlTokenWrite);
	echo "loggedout";
        exit();
}

echo "invalid_token";
?>
