<?php
$nom = $_POST['nom'];
$prenom = $_POST['prenom'];
$dateNaiss = $_POST['dateNaiss'];
$adresse = $_POST['adresse'];
$categorie = $_POST['categorie'];
$telephone = $_POST['telephone'];
$mail = $_POST['mail'];
$caracteristique = $_POST['caracteristique'];

if (!empty($nom) || !empty($prenom) || !empty($dateNaiss) || !empty($adresse) ||
!empty($categorie) || !empty($telephone) | (!empty($caracteristique)){
$host = "localhost:3306";
$dbname = "fa4a639w_clients";
$dbUsername = "root";
$dbPassword = "";
$conn = new mysqli($host, $dbUsername, $dbPassword, $dbname);
if (mysqli_connect_error()){
die('Connect Error('.mysqli_connect_errno().')'.mysqli_connect_error());
} else {
    $SELECT = "SELECT nom From clients Where nom = ? Limit 1";
    $INSERT = "INSERT Into clients (nom, prenom, dateNaiss, adresse, categorie,
        telephone, mail, caracteristique) values(?, ?, ?, ?, ?, ?, ?, ?)";

    $stmt = $conn->prepare($SELECT);
    $stmt->bind_param("s",$nom);
    $stmt->execute();
    $stmt->bind_result($nom);
    $stmt->store_result();
    $rnum = $stmt->num_rows;

    if ($rnum==0) {
        $stmt->close();
        $stmt->$conn->prepare($INSERT);
        $stmt->bind_param("sssssiss", $nom, $prenom, $dateNaiss, $adresse,
            $categorie, $telephone, $mail, $caracteristique);
        $stmt->execute();
        echo "Client enregistré !";
        } else {
                echo "Client déjà enregistré";}

}
        $stmt->close();
        $conn->close();


} else{
    echo "erreur";
    die();
    }
?>