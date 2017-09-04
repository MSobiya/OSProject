<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "osproject";

// Create connection
$conn = new mysqli($servername, $username, $password,$dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$sql = "SELECT * FROM os_table";
$result=mysqli_query($conn,$sql);

$response=array();

while($row=mysqli_fetch_array($result)){
	array_push($response, array("kid"=>$row[0],"kip"=>$row[1],"kpc_no"=>$row[2],"kstatus"=>$row[3],"klast_update"=>$row[4]));
}
echo json_encode(array("server_response"=>$response));
mysqli_close($conn);
?>