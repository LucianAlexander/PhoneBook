<?php
    require "init.php";
    
    $tel = $_POST["tel"];
 
    
    $statement = mysqli_prepare($con, "DELETE FROM persone WHERE peer_tel = ?");
    mysqli_stmt_bind_param($statement, "s", $tel);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement);
    
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
