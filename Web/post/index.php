<?php
//Get the post type, username, password, sessiontoken
$postType = $_POST["postType"];
$username = $_POST["username"];
$password = $_POST["password"];
$token = $_POST["sessionToken"];

//Sign into database
$SQLservername = "localhost";
$SQLusername = "root";
$SQLpassword = "password";
$SQLdatabase = "users";

$conn = new mysqli($SQLservername, $SQLusername, $SQLpassword, $SQLdatabase);

if ($conn->connect_error) {
    die("error connecting to server");
}

//Check that the credentials are legit
$sql = "SELECT * FROM people WHERE Username='{$username}' AND Password='{$password}' AND SessionToken='{$token}'";
$result = $conn->query($sql);

if($result->num_rows < 1){
        echo "invalid_credentials";
        exit();
}

//Posts to the database using the inputted data

function post($content, $extra, $u, $t){
	$sname = "localhost";
	$susername = "root";
	$spassword = "password";
	$sdatabase = "posts";

	$milliseconds = round(microtime(true) * 1000);

	$conn = new mysqli($sname, $susername, $spassword, $sdatabase);

	if ($conn->connect_error) {
    		die("mysql_connection_problem");
	}

	$sql = "INSERT INTO {$u} VALUES('{$t}', '{$milliseconds}', '{$content}', '{$extra}')";
	$result = $conn->query($sql);
}
//Gets the data depending on the post type
switch($postType){
	case "text":
		$content = $_POST["textContent"];
		post($content, "NO_EXTRA", $username, $postType);
		break;
	case "youtube":
		$content = $_POST["youtubeVideo"];
                post($content, "NO_EXTRA", $username, $postType);
		break;
	case "link":
		$content = $_POST["link"];
                $description  = $_POST["description"];
                post($content, $description, $username, $postType);
                break;
	case "photo":
		$content = $_POST["link"];
		$description  = $_POST["description"];
                post($content, $description, $username, $postType);
		break;
}

echo "success";

?>
