<?php
require_once('php8_lib.php');

function isClean($query) {
    $query_lower = strtolower($query);
    $illegal_keywords = array("DROP");

    foreach ($illegal_keywords as $illegal_keyword) {
        $illegal_keyword_lower = strtolower($illegal_keyword);
        if(str_contains_($query_lower, $illegal_keyword_lower)) {
            return array(false, "Error: " . $illegal_keyword_lower . " is illegal keyword.");
        }
    }

    return array(true, "");
}
?>