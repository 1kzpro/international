<?php
    require_once('db.php');
    require_once('table_generator.php');
    require_once('sanitizer.php');

    $sql_query_text = '';
    $sql_query_result_html = '';
    $error_msg = '';


    function requestSQLquery($sql_query_text) {
        try {
            global $sql_query_result_html, $error_msg;
            $sql_query_result_html = '';
            $error_msg = '';
            $result = runQuery($sql_query_text);
            if ($result === TRUE) {
                if (str_contains(strtolower($sql_query_text), 'insert into')) {
                    $sql_query_result_html = '<p>New record created successfully.</p>';
                }
                else if (str_contains(strtolower($sql_query_text), 'update')) {
                    $sql_query_result_html = '<p>Record updated successfully.</p>';
                }
                else if (str_contains(strtolower($sql_query_text), 'delete')) {
                    $sql_query_result_html = '<p>Record deleted successfully.</p>';
                }
                else {
                    $sql_query_result_html = '<p>Operation completed successfully.</p>';
                }
            }
            else if ($result != NULL) {
                $sql_query_result_html = renderTable($result);
            }
            else {
                $sql_query_result_html = '<p>Check the error message.</p>';
                $error_msg = getDBError();
            }
        }
        catch (Throwable $e) {
            $sql_query_result_html = '<p>Check the error message.</p>';
            $error_msg = $e->getMessage();
        }
    }

    if ($_POST) // If sql query was submitted...
    {   
        if (array_key_exists('runButton', $_POST)) {
            console_log("Run Button Pressed!");

            $sql_query_text = $_POST["sqlQueryTextarea"]; // Get it into a variable
            console_log($sql_query_text);

            if ($sql_query_text != '') {
                // Check the input of the sql query
                $sanitize_array = isClean($sql_query_text);
                if (!$sanitize_array[0]) {
                    $error_msg = $sanitize_array[1];
                }
                else {
                    requestSQLquery($sql_query_text);
                }
                
            }
        }

        if (array_key_exists('clearButton', $_POST)) {
            $sql_query_text = '';
            console_log("Clear Button Pressed!");
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kazybek Auburn University</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row mt-3 mb-1">
            <div class="row">
                <div class="col">
                    <h1>Term Project</h1>
                    <h6>COMP-5120-001 • Spring 2023</h6>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <p class="text-center">Instructor: Dr. Wei-Shinn (Jeff) Ku</p>
                </div>
                <div class="col">
                    <p class="text-center">TA: Po-wei Harn</p>
                </div>
                <div class="col">
                    <p class="text-center">Student: Kazybek Mizam</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h6>SQL Query</h6>
                <form class="row mb-3" method="post">
                    <textarea class="form-control" name="sqlQueryTextarea" rows="10"><?= $sql_query_text ?></textarea>
                    <div class="col mt-2">
                        <button type="submit" class="btn btn-primary" name="runButton">Run</button>
                        <button type="submit" class="btn btn-secondary" name="clearButton">Clear</button>
                    </div>
                </form>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col">
                        <h6>Query Result</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <?=$sql_query_result_html?>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h6>Error Messages</h6>
                <p class="text-break"><?= $error_msg ?></p>
            </div>
        </div>
    </div>
    
    <!-- Local JS -->
    <script src="index.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
</body>
</html>