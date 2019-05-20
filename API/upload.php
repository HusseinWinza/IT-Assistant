 <?php
  require_once "connect.php";

  if(!$con){
    echo "Database connected failed";
  }
  else{
  
     
               if($_SERVER['REQUEST_METHOD'] == "POST")
               {

                   if (isset($_POST['username']) && isset($_POST['password']))
                   {
 
              $username = $_POST['username'];
                $password = md5($_POST['password']);
          
                        $dupe = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";
                        $dupe_results = mysqli_query($con, $dupe);

                           if(mysqli_num_rows($dupe_results) > 0 )
                           {


                              $dupe_status = "SELECT name, department, room, block, status FROM users WHERE username = '$username'";
                              $dupe_status_results = mysqli_query($con, $dupe_status);
                              $response = array();

                              if(!$dupe_status_results){
                                    echo "status and couse has some issues";
                              }else{
                                while ($row=mysqli_fetch_array($dupe_status_results)) {
                                array_push($response, array("name"=>$row[0], "department"=>$row[1], "room"=>$row[2], "block"=>$row[3], "status"=>[5]));
                                }
                                  print(json_encode(array("server_response"=>$response)));
                              }
 
                           }else{
     
                              echo "0";
                          
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