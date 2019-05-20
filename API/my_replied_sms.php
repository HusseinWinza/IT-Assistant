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
                              $response = array();

                              if(!$dupe_status_results){
                                    echo "*0*";
                              }else{
                                while ($row=mysqli_fetch_array($dupe_status_results)) {
                                array_push($response, array("from1"=>$row[2], "message"=>$row[3]));
                                }
                                  print(json_encode(array("server_response"=>$response)));
                              }

                    mysqli_close($con);
                     

               }else{
            echo "Improper Request Method";
              }

       }
   
?>