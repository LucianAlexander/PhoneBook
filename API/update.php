<?php
    
require "init.php";
    
    
    $nome = $_POST["nome"];
    $cognome = $_POST["cognome"];
    $indirizzo = $_POST["indir"];
    $tel = $_POST["tel"];  
    $eta = $_POST["eta"];
    $oldtel = $_POST["tel2"];
   
    
    $stmt = $con->prepare("UPDATE persone SET peer_nome = ?, peer_cognome = ? , peer_indirizzo = ?, peer_tel = ?, peer_eta = ?
                                                                          WHERE
                                                                          peer_tel = ?" );

    $stmt->bind_param("ssssss", $nome, $cognome, $indirizzo, $tel, $eta, $oldtel);

  
    $response["success"] = false; 
    $result = $stmt->execute();
    $response["success"]= $result;                                                                        



   // $nome = ["nome"];

if($response["success"] == true){
          $sql = "SELECT peer_nome, peer_cognome, peer_indirizzo, peer_tel, peer_eta FROM persone  ORDER BY persone.peer_id DESC";
          $result1 = mysqli_query($con,$sql);
          $response2 = array();
             while($row = mysqli_fetch_array($result1)){

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
   

?> 
