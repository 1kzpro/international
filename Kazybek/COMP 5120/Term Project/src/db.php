<?php

$error = '';

function console_log($output, $with_script_tags = true) {
  $js_code = 'console.log(' . json_encode($output, JSON_HEX_TAG) . ');';

  if ($with_script_tags) {
      $js_code = '<script>' . $js_code . '</script>';
  }

  echo $js_code;
}

function createConnection() {
  $servername = "sysmysql8.auburn.edu";
  $username = "kzm0099";
  $password = "mkbekpro23";
  $db_name = "kzm0099db";

  // Create connection
  $conn = new mysqli($servername, $username, $password, $db_name);

  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  }

  console_log("Connected successfully");
  return $conn;
}

function runQuery($query) {
  global $error;
  $conn = createConnection();
  try {
    $result = $conn->query($query);
    if ($result) {
      return $result;
    }
    $error = $conn->error;
    return NULL;
  } catch (Throwable $e) {
    $conn->close();
    throw $e;
  }
}

function getDBError() {
  global $error;
  return $error;
}
?>