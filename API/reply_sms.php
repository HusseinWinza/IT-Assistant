 <?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
    
          
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   if (isset($_POST['to']) && isset($_POST['from']) && isset($_POST['message']) && isset($_POST['status'])
                    && isset($_POST['id_message']))
                   {

              $to = $_POST['to'];
              $from = $_POST['from'];
              $message = $_POST['message'];
              $status = $_POST['status'];
              $id_message = $_POST['id_message'];
               
  
                          $sql = "INSERT INTO chart VALUES ('',  '$to', '$from', '$message', '$status', '$id_message')";


                          if(mysqli_query($con, $sql)){
                
                             echo "sms sent";
                         }else{
                          echo "Error: " . $sql . "<br>" . mysqli_error($con);;
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