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
$query=mysqli_query($conn,$sql);

$id=[];
$ip=[];
$pc_no=[];
$status=[];
$last_update=[];

if($query){
	while($row=mysqli_fetch_array($query,MYSQLI_ASSOC)){
	$r[]=$row;
	}
}
echo json_encode($r);
?>