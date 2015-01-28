<?php

/*
Checks the database for any rows matching the username inputted via POST, outputs result
*/

$postedUsername = $_POST["username"];

ini_set('display_errors',1);
ini_set('display_startup_errors',1);
error_reporting(-1);

$servername = "localhost";
$username = "root";
$password = "password";
$dbname = "users";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT Username FROM people WHERE Username='{$postedUsername}'";
$result = $conn->query($sql);

if($result->num_rows < 1){
	echo "Username available";
}
else{
	echo "Username taken";
}

$conn->close();
?>
