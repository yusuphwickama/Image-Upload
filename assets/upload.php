<?php
/**
 * Created by PhpStorm.
 * User: Wickerman
 * Date: 3/1/2017
 * Time: 11:43 PM
 */
ini_set('display_errors','off');
$target_dir = "uploads/";
$target_file = $target_dir . basename($_FILES["imageFile"]["name"]);
$uploadOk = 1;
$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
// Check if image file is a actual image or fake image
if(isset($_FILES["imageFile"])) {

    if(!is_dir($target_dir)){
        mkdir($target_dir);
    }

    if (move_uploaded_file($_FILES["imageFile"]["tmp_name"], $target_file)) {
        echo "The file has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file";
    }
}