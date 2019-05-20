<?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
  
     
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   
             
              $id_message = $_POST['id_message'];
                
          
                              $dupe_status = "SELECT * FROM chart WHERE id_message = '$id_message'";
                              $dupe_status_results = mysqli_query($con, $dupe_status);

                              if(!$dupe_status_results){
                                    echo "*0*";
                              }else{ 


                           if(mysqli_num_rows($dupe_status_results) > 0 )
                           {

                                $row = mysqli_num_rows($dupe_status_results);
                
                                 echo $row;
                                 }else{
                            	echo "zing";
                                 }
                              }
                            

                    mysqli_close($con);
                     

               }else{
            echo "Improper Request Method";
              }

       }
   
?>