<?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
  
     
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   
              $from1 = $_POST['from1'];
              $status = $_POST['status'];
                
          
                              $dupe_status = "SELECT * FROM chart WHERE from1 = '$from1' AND status = '$status'";
                              $dupe_status_results = mysqli_query($con, $dupe_status);

                              if(!$dupe_status_results){
                                    echo "*0*";
                              }else{ 

                                $row = mysqli_num_rows($dupe_status_results);
                
                                echo $row;
                              }

                    mysqli_close($con);
                     

               }else{
            echo "Improper Request Method";
              }

       }
   
?>