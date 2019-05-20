 <?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
  
     
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   
              $username = $_POST['username'];
                
          
                              $dupe_status = "SELECT * FROM users WHERE username = '$username'";
                              $dupe_status_results = mysqli_query($con, $dupe_status);
                              $response = array();

                              if(!$dupe_status_results){
                                    echo "*0*";
                              }else{
                                while ($row=mysqli_fetch_array($dupe_status_results)) {
                                array_push($response, array("recovery_qn"=>$row[8], "recovery_ans"=>$row[9]));
                                }
                                  print(json_encode(array("server_response"=>$response)));
                              }

                    mysqli_close($con);
                     

               }else{
            echo "Improper Request Method";
              }

       }
   
?>