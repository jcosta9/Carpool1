<?php
$hostname_localhost ="localhost";
$database_localhost ="carpool";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
 
$email = $_POST['email'];
$senha = $_POST['senha'];
$query_search = "select * from user where email = '".$email."' AND senha = '".$senha. "'";
$query_exec = mysql_query($query_search) or die(mysql_error());
$rows = mysql_num_rows($query_exec);
//echo $rows;
 if($rows == 0) {
 echo "No Such User Found";
 }
 else  {
    echo "User Found";
}
?>