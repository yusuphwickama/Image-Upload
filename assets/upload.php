<?php

/**
 * Created by: WickerLabs.Inc
 * Project title: Image-Uploader
 * Time: 12:19 AM
 * Date: 2/13/2017
 * Website: http://www.wickerlabs.com
 */

// Turn display error off
ini_set('display_errors','off');

// the directory to save uploaded images
$target_dir = "uploads/";

if(isset($_FILES["imageFile"])) {

    $tmp_image_file = $_FILES["imageFile"]["tmp_name"];

    // Upload if file is a valid image
    if(getimagesize($tmp_image_file)) {

        $target_file = $target_dir . basename($_FILES["imageFile"]["name"]);  // path on where to save the image file


        // Create the 'target_dir' directory if it does not exists.
        if (!is_dir($target_dir)) {
            mkdir($target_dir);
        }

        // Move the uploaded image file to the 'target_dir'
        if (move_uploaded_file($tmp_image_file, $target_file)) {
            echo "The file has been uploaded.";
        } else {
            echo "Sorry, there was an error uploading your file";
        }
    } else {
        echo "Invalid image file";
    }
}