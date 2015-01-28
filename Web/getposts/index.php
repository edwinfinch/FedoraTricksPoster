<?php
//Supports GET and POST
$username = "undefined";
$format = "json";
if(isset($_GET["username"])){
	$username = $_GET["username"];
	$format = $_GET["format"];
}
else{
	$username = $_POST["username"];
	$format = $_POST["format"];
}

$servername = "localhost";
$Susername = "root";
$Spassword = "password";
$dbname = "posts";

$conn = new mysqli($servername, $Susername, $Spassword, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//Gets the user's posts from the database
$sql = "SELECT * FROM {$username}";
$result = $conn->query($sql);
$postNum = 0;
if($format === "json"){
echo "{";
echo '"posts": [';
//Sets each post into its own part in the array
while($row = $result->fetch_assoc()){
  echo "{";
  echo '"postnum": "' . $postNum . '", ';
  echo '"type": "' . $row["Type"] . '", ';
  echo '"posted": "' . $row["Posted"] . '", ';
  echo '"content": "' . $row["Content"] . '", ';
  echo '"extra": "' . $row["Extra"] . '"';
  $postNum++;
  echo "},";
}
echo '{ }';
echo "]";
echo "}";
//done
}
else{
	die("unsupported_format");
}
?>
