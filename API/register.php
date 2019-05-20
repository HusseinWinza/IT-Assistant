 <?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
    
          
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   if (isset($_POST['name']) && isset($_POST['department']) && isset($_POST['room']) && isset($_POST['block'])
                    && isset($_POST['status']) && isset($_POST['username']) && isset($_POST['password']) && isset($_POST['recovery_qn']) && isset($_POST['recovery_ans']))
                   {

              $name = $_POST['name'];
              $department = $_POST['department'];
              $room = $_POST['room'];
              $block = $_POST['block'];
              $status = $_POST['status'];
              $username = $_POST['username'];
              $password = $_POST['password'];
              $recovery_qn = $_POST['recovery_qn'];
              $recovery_ans = $_POST['recovery_ans'];
              

                        $dupe = "SELECT * FROM users WHERE username = '$username'";
                        $dupe_results = mysqli_query($con, $dupe);
 

                           if(mysqli_num_rows($dupe_results) > 0 )
                           {

                              echo "Username already in use";

                           }else{
     

                          $sql = "INSERT INTO users VALUES ('',  '$name', '$department', '$room', '$block', '$status', '$username', '$password', '$recovery_qn', '$recovery_ans')";


                          if(mysqli_query($con, $sql)){
                
                             echo "user added";
                         }else{
                          echo "Error: " . $sql . "<br>" . mysqli_error($con);;
                         }
           
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