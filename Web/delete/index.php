<?php

/*
Allows a user to delete their account, currently unsupported by Java application
but to be supported at a later time
*/

$timePosted = $_POST["timePosted"];
$username = $_POST["username"];
$password = $_POST["password"];
$token = $_POST["sessionToken"];

$SQLservername = "localhost";
$SQLusername = "root";
$SQLpassword = "password";
$SQLdatabase = "users";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to server");
}

$exists = "SELECT * FROM people WHERE Username='{}' AND Password='{}' AND SessionToken='{}'";
$resultExists = $conn->query($exists);
if($result->num_rows < 1){
	echo "invalid_credentials";
}
$conn->close();

$SQLdatabase = "posts";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to server");
}

$sql = "DELETE * FROM {$username} WHERE Posted='{$timePosted}'";
$result = $conn->query($sql);
$conn->close();

?>
