<?php
    require "init.php";
    
    $tel = $_POST["tel"];
    $stmt = $con->prepare("DELETE FROM persone WHERE peer_tel = ?");
    $stmt->bind_param("s", $tel);

   
    $response["success"] = false; 
    $result = $stmt->execute();
    $response["success"]= $result;
    
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
