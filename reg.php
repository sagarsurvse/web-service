<?php
       
        define('HOST','localhost');
        define('USER','id8971802_patil');
       	define('PASS','admin');
	define('DB','id8971802_kt');
        $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
          $username = $_GET['username'];
	
	$password = $_GET['password'];
	
	$email = $_GET['email'];
		if($username == '' || $password == '' || $email == '')
		{
	
		echo 'please fill all values';
		}
		else{
		$sql = "SELECT * FROM web WHERE username='$username' OR email='$email'";
	        $check = mysqli_fetch_array(mysqli_query($con,$sql));
		if(isset($check)){
		echo 'username or email already exist';
		}else{
		$sql = "INSERT INTO web (username,password,email) VALUES('$username','$password','$email')";
		if(mysqli_query($con,$sql)){
			echo 'successfully registered';
	
	}
		else{
				
			echo 'oops! Please try again!';
		
		}
}
			
	        mysqli_close($con);
		}