<?php
    require "init.php";
    
    $user = $_POST["user"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT user_name FROM users WHERE user_name = ? AND user_password = ?");
    mysqli_stmt_bind_param($statement, "ss", $user, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_name);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
         $response["success"] = true;   
    }

     if($response["success"] == true){
          $sql = "SELECT peer_nome, peer_cognome, peer_indirizzo, peer_tel, peer_eta FROM persone  ORDER BY persone.peer_id DESC";
          $result = mysqli_query($con,$sql);
          $response2 = array();
             while($row = mysqli_fetch_array($result)){

                    array_push($response2,array( 
                             
                                "nome"=>$row[0], 
                                "cognome"=>$row[1],
                                "indirizzo"=>$row[2], 
                                "telefono"=>$row[3],
                                "eta"=>$row[4]));

             }
                    echo json_encode(
                                     array(
                                           'success'=>'true',
                                           "server_response"=>$response2)
                                            ); 
                       
        }else{
                   echo json_encode($response);
                     }
   
