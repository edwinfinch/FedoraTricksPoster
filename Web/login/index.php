<?php

/*
Login simply checks the database for valid credentials, and then if they are valid,
outputs a new session token which should be used by the program using the API
*/

$username = $_POST["username"];
$password = $_POST["password"];

$SQLservername = "localhost";
$SQLusername = "root";
$SQLpassword = "password";
$SQLdatabase = "users";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to server");
} 

$sql = "SELECT * FROM people WHERE Username='{$username}' AND Password='{$password}'";
$result = $conn->query($sql);

if($result->num_rows > 0){
	$newToken = rand();
	$sqlTokenWrite = "UPDATE people SET SessionToken='{$newToken}' WHERE username='{$username}'";
	$resultOfWrite = $conn->query($sqlTokenWrite);
	echo $newToken;
	exit();
}

echo "invalid";
?>
