 <?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
     
    
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   if (isset($_POST['username']) && isset($_POST['password_new'])){
                   
 
              $username = $_POST['username'];
              $password_new = $_POST['password_new'];

                       

                             $sql2 = "UPDATE users SET password = '$password_new' WHERE username = '$username'";

                          if(mysqli_query($con, $sql2)){
                
                             echo "Password changed succesfull";
                         }else{

                          echo "Error: " . $sql2 . "<br>" . mysqli_error($con);;
                         }
           
 

                    mysqli_close($con);
                     
                  }else{
          echo "Missing required field";

                 } 

               }else{
            echo "Improper Request Method";
              }
 
       }
   
?>