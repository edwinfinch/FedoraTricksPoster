<?php
/*
User creator
Creates a user with the username and password in the MySQL database and then
returns whether or not it is success
*/

ini_set('display_errors',1);
ini_set('display_startup_errors',1);
error_reporting(-1);

$username = $_POST["username"];
$password = $_POST["password"];

$SQLservername = "localhost";
$SQLusername = "root";
$SQLpassword = "password";
$SQLdatabase = "users";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to user database");
}

$result = $conn->query("INSERT INTO people (Username, Password, SessionToken) VALUES ('{$username}', '{$password}', 'notoken')");
if(!$result){
	echo "error creating user";
	exit();
}

$conn->close();

$SQLdatabase = "posts";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to post database");
}

$result = $conn->query("CREATE TABLE {$username} (Type varchar(20), Posted varchar(16), Content varchar(800), Extra varchar(400))");
if(!$result){
        echo "error creating post table";
	exit();
}
echo "success";

$conn->close();

?>
